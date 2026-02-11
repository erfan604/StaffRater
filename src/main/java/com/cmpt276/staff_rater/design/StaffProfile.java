package com.cmpt276.staff_rater.design;

public class StaffProfile implements StaffMemberProfile {

    private final String name;

    public StaffProfile(String name) {
        this.name = name;
    }

    @Override
    public String displayTitle() {
        return "Staff: " + name;
    }
}
