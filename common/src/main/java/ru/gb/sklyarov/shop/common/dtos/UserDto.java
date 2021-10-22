package ru.gb.sklyarov.shop.common.dtos;

public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private String confirmation;
    private String email;
    private String authorities;

    public UserDto() {
    }

    public UserDto(Long userId, String username, String password, String confirmation, String email, String authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.confirmation = confirmation;
        this.email = email;
        this.authorities = authorities;
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

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
