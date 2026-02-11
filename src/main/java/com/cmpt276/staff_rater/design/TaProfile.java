package com.cmpt276.staff_rater.design;

public class TaProfile implements StaffMemberProfile {

    private final String name;

    public TaProfile(String name) {
        this.name = name;
    }

    @Override
    public String displayTitle() {
        return "TA: " + name;
    }
}
