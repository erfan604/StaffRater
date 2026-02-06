package com.cmpt276.staff_rater.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;



@Entity
@Table (name = "staffRatings")
public class StaffRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    
    @Column (unique = true)
    @NotBlank
    @Email
    private String email;

    @Min(1)
    @Max(10)
    private int clarity, niceness, knowledge;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Size(max=300)
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoleType(RoleType roleType) {
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoleType getRoleType() {
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

    @PrePersist
    public void recordCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void recordUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

}
