package com.cmpt276.staff_rater.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRatingRepository extends JpaRepository<StaffRating, Integer>{
    
}
