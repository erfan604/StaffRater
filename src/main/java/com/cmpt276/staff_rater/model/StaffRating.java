package com.cmpt276.staff_rater.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity
public class StaffRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name, roleType;
    
    @Email
    private String email;

    @Min(1)
    @Max(10)
    private int clarity, niceness, knowledge;

    private String comment;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public void setNiceness(int niceness) {
        this.niceness = niceness;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getEmail() {
        return email;
    }

    public int getClarity() {
        return clarity;
    }

    public int getNiceness() {
        return niceness;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public String getComment() {
        return comment;
    }

    public StaffRating(){
        
    }
}
