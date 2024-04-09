package rs.frm.officetimetracker.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.frm.officetimetracker.domain.enumeration.Role;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_office")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private boolean oAuth2User;

    private Instant dateCreated;
    private Role role;
    private Instant creationDate;
    private LocalDateTime lastLogin;
    private String username;
    private String fullName;
    private LocalDateTime dateOfBirth;
    private String profilePictureUrl;

    @Column(length = 500)
    private String bio;

    private String location;
    private String phone;

}
