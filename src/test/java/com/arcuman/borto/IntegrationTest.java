package com.arcuman.borto;

import com.arcuman.borto.models.Role;
import com.arcuman.borto.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class IntegrationTest {
  @Autowired
  WebApplicationContext webApplicationContext;

  @Autowired
  JwtTokenProvider jwtTokenProvider;
  private MockMvc mockMvc;

  protected void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }
  @Test
  void testSetJwtTokenProviderUser() throws Exception {
    setUp();
    List<Role> roles = new ArrayList<>();
    Role role = new Role();
    role.setName("ROLE_USER");
    roles.add(role);
    String token = jwtTokenProvider.createToken("w", roles);
    Authentication authentication = jwtTokenProvider.getAuthentication(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/documents").header("Authorization", "Bearer_" + token))
        .andExpect(status().isOk());
  }

  @Test
  void testNotFoundPagesComments() throws Exception {
    setUp();
    List<Role> roles = new ArrayList<>();
    Role role = new Role();
    role.setName("ROLE_USER");
    roles.add(role);
    String token = jwtTokenProvider.createToken("w", roles);
    Authentication authentication = jwtTokenProvider.getAuthentication(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/documents/1/comments").header("Authorization", "Bearer_" + token))
        .andExpect(status().is4xxClientError());
  }


}
