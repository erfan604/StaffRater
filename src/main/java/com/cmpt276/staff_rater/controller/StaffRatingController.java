package com.cmpt276.staff_rater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cmpt276.staff_rater.model.StaffRatingRepository;
import com.cmpt276.staff_rater.model.StaffRating;



@Controller
public class StaffRatingController {

    
    @Autowired
    private StaffRatingRepository repo;

    @GetMapping("/")
    public String getAllInfo(Model model) {
        List<StaffRating> ratings = repo.findAll();
        model.addAttribute("ratings", ratings);
        return "getAllInfo";
    }
    
    
}
