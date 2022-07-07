package com.spring.boot.coodle.entities.dto.responses;

import com.spring.boot.coodle.entities.Trainer;
import com.spring.boot.coodle.entities.User;
import java.util.List;

public class TrainerListResponse {

    private List<Trainer> trainer;

    private List<User> user;

    public TrainerListResponse() {
    }

    public TrainerListResponse(List<Trainer> trainer, List<User> user) {
        this.trainer = trainer;
        this.user = user;
    }

    public List<Trainer> getTrainer() {
        return trainer;
    }

    public void setTrainer(List<Trainer> trainer) {
        this.trainer = trainer;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrainerListResponse{");
        sb.append("trainer=").append(trainer);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }

}
