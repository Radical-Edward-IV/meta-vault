package org.metavault.userservice.user.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.metavault.userservice.role.model.entity.Role;
import org.metavault.userservice.user.model.UserCommon;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
