package com.arcuman.borto.rest;

import com.arcuman.borto.dto.AuthResponceDTO;
import com.arcuman.borto.dto.AuthenticationRequestDto;
import com.arcuman.borto.dto.RegisterDTO;
import com.arcuman.borto.models.User;
import com.arcuman.borto.security.jwt.JwtTokenProvider;
import com.arcuman.borto.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final UserService userService;

  @Autowired
  public AuthenticationRestControllerV1(
      AuthenticationManager authenticationManager,
      JwtTokenProvider jwtTokenProvider,
      UserService userService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.userService = userService;
  }

  @PostMapping("login")
  public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
    try {
      String username = requestDto.getUsername();
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
      User user = userService.findByUsername(username);

      if (user == null) {
        throw new UsernameNotFoundException("User with username: " + username + " not found");
      }

      String token = jwtTokenProvider.createToken(username, user.getRoles());
      AuthResponceDTO response = new AuthResponceDTO(username,token,user.getRoles().toString());

      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterDTO signupRequest) {
    if (!userService.isExistUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new Exception("Username is already taken"));
    }
    if (!userService.isExistEmail(signupRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new Exception("Email is already taken"));
    }
    User newUser = new User();
    newUser.setUsername(signupRequest.getUsername());
    newUser.setEmail(signupRequest.getEmail());
    newUser.setFirstName(signupRequest.getFirstName());
    newUser.setLastName(signupRequest.getLastName());
    newUser.setPassword(signupRequest.getPassword());
    userService.register(newUser);
    log.info("IN register - user with username: {} successfully registered", newUser.getUsername());
    return ResponseEntity.ok("User registered successfully");
  }
}
