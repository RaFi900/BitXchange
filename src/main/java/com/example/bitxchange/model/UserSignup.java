package com.example.bitxchange.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "users")
public class UserSignup
{
    @Id
    private String id;

    @NotBlank(message = "Please provide a valid UserName")
    private String name;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Please provide an email address")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public UserSignup() {
    }

    public UserSignup(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
