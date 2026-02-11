package com.cmpt276.staff_rater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cmpt276.staff_rater.model.StaffRatingRepository;

import jakarta.validation.Valid;

import com.cmpt276.staff_rater.design.StaffMemberProfile;
import com.cmpt276.staff_rater.design.StaffProfileProvider;
import com.cmpt276.staff_rater.model.StaffRating;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class StaffRatingController {

    
    @Autowired
    private StaffRatingRepository repo;

    @GetMapping("/")
    public String index(Model model) {
        List<StaffRating> ratings = repo.findAll();
        model.addAttribute("ratings", ratings);
        return "index";
    }

    @GetMapping("/ratings/create")
    public String showCreateForm(Model model){
        model.addAttribute("rating", new StaffRating());
        return "create";
    }

    @PostMapping("/ratings/create")
    public String createRating (@Valid StaffRating rating, BindingResult result){
        if (result.hasErrors()) {
            return "create";
        }
        repo.save(rating);
        return "redirect:/";
    }

    @GetMapping("/ratings/{id}")
    public String details(@PathVariable int id, Model model){

        StaffRating rating = repo.findById(id).orElseThrow();
        StaffMemberProfile profile = StaffProfileProvider.getProfile(rating.getRoleType(), rating.getName());

        model.addAttribute("rating", rating);
        model.addAttribute("displayTitle", profile.displayTitle());
        
        return "details";
    }


    @GetMapping("/ratings/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("rating", repo.findById(id).orElseThrow());
        return "edit";
    }


    @PostMapping("/ratings/{id}/edit")
    public String update(@PathVariable int id, @Valid StaffRating rating, BindingResult result) {
        if (result.hasErrors()) return "edit";
        rating.setId(id);
        repo.save(rating);
        return "redirect:/";
    }

    @PostMapping("/ratings/{id}/delete")
    public String delete(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/";
    }
    

}
