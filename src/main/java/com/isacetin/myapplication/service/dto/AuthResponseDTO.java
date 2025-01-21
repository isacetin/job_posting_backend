package com.isacetin.myapplication.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseDTO {

    @JsonProperty("id_token")
    private String idToken;

    private String email;
    private String firstName;
    private String lastName;
    private String login;
    private String imageUrl;
    private String langKey;

    public AuthResponseDTO(String idToken, String email, String firstName, String lastName, String login, String imageUrl, String langKey) {
        this.idToken = idToken;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.imageUrl = imageUrl;
        this.langKey = langKey;
    }

    // Getters and Setters
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }
}
