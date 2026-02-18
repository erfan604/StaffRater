package com.cmpt276.staff_rater.design;

import com.cmpt276.staff_rater.model.RoleType;

public class StaffProfileProvider {

    public static StaffMemberProfile getProfile(RoleType roleType, String name) {

        if (roleType == RoleType.TA) {
            return new TaProfile(name);
        }

        if (roleType == RoleType.PROF) {
            return new ProfProfile(name);
        }

        if (roleType == RoleType.STAFF) {
            return new StaffProfile(name);
        }

        return new StaffProfile(name);
    }
}
