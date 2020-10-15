package com.arcuman.borto.forms;

import com.arcuman.borto.models.Role;
import com.arcuman.borto.models.User;

import java.util.Set;

public class UserForm {

    private Integer idUser;

    private String username;

    private String password;

    private Set<Role> roles;

    private boolean active;

    public UserForm() {
    }

    public UserForm(User user) {
        this.idUser = user.getIdUser();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.active = user.isActive();
    }
    public UserForm(Integer idUser, String username, String password, Set<Role> roles, boolean active) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
