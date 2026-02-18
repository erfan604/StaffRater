package com.cmpt276.staff_rater.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;



@Entity
@Table (name = "staffRatings")
public class StaffRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    
    @Column (unique = true)
    @NotBlank
    @Email
    private String email;

    @Min(1)
    @Max(10)
    private Integer clarity, niceness, knowledge;

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

    public void setClarity(Integer clarity) {
        this.clarity = clarity;
    }

    public void setNiceness(Integer niceness) {
        this.niceness = niceness;
    }

    public void setKnowledge(Integer knowledge) {
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

    public Integer getClarity() {
        return clarity;
    }

    public Integer getNiceness() {
        return niceness;
    }

    public Integer getKnowledge() {
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

    public double getOverallScore() {
        int c = clarity != null ? clarity : 0;
        int n = niceness != null ? niceness : 0;
        int k = knowledge != null ? knowledge : 0;
        return (c + n + k) / 3.0;
    }
}
