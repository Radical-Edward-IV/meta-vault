package org.metavault.userservice.user.model;

import org.metavault.userservice.role.model.RoleCommon;

public interface UserCommon {

    Long getId();

    String getEmail();

    String getPassword();

    String getFirstName();

    String getLastName();

    String getPhone();

    <T extends RoleCommon> T getRole();
}
