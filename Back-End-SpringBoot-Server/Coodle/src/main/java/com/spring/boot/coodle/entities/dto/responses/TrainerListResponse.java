package com.spring.boot.coodle.entities.dto.responses;

public class TrainerListResponse {

    private int id;

    private String email;

    private String password;

    private String fistName;

    private String lastName;

    public TrainerListResponse() {
    }

    public TrainerListResponse(int id, String email, String password, String fistName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fistName = fistName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrainerListResponse{");
        sb.append("id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", fistName=").append(fistName);
        sb.append(", lastName=").append(lastName);
        sb.append('}');
        return sb.toString();
    }
}
