package fr.orleans.m1s1miage.group4.backend.model.dto.auth;

public class JwtDTO {
    private String token;
    private JwtDTO() {
    }
    public JwtDTO(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
