package org.metavault.userservice.user.model.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.metavault.userservice.role.model.dto.RoleDto;
import org.metavault.userservice.user.model.UserCommon;

@Data
public class SignUpRequest implements UserCommon {

    @JsonIgnore
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
             message =
                     "Password must be at least 8 characters long, " +
                     "contain at least one uppercase letter, " +
                     "one lowercase letter, one number, and one special character.")
    private String password;

    @NotBlank
    @Size(min = 1)
    private String firstName;

    @NotBlank
    @Size(min = 1)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$",
             message = "Phone number should be valid")
    private String phone;

    @JsonIgnore
    private RoleDto role;

    public void setId(Long id) {}

    public Long getId() { return null; }

    public void setRole(RoleDto role) {}

    public RoleDto getRole() { return null; }
}
