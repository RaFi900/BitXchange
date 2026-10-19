package com.example.bitxchange.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logins")
public class UserLogin
{
    @Id
    private String id;

    @NotBlank(message = "Please provide an email address")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Please provide a password")
    private String password;

   public UserLogin() {
    }

    public UserLogin(String email, String password) {
       this.email = email;
       this.password = password;
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
