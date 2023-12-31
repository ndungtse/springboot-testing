package rw.ac.rca.gradesclassb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import rw.ac.rca.gradesclassb.dtos.CreateUserDTO;
import rw.ac.rca.gradesclassb.enumerations.EGender;
import rw.ac.rca.gradesclassb.enumerations.EStatus;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"phone_number"})})
@OnDelete(action = OnDeleteAction.CASCADE)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column (name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @JsonIgnore
    @Column(name = "password")
    @NotBlank (message = "Password is required")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "verified", columnDefinition = "boolean default false")
    private boolean verified;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "activation_code")
    private String activationCode;

    private String fullName;

    private boolean deletedFlag;

    private String credentialsExpiryDate;

    private boolean isAccountExpired;

    private boolean isCredentialsExpired;

    private boolean isAccountEnabled;

    private boolean isAccountLocked;

    // Define the many-to-many relationship with roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @JsonIgnore
    @Transient
    private Collection<GrantedAuthority> authorities;

    public User(CreateUserDTO dto) {
        this.email = dto.getEmailAddress();
        this.phoneNumber = dto.getPhoneNumber();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.gender = dto.getGender();
        this.fullName = dto.getFirstName()+" "+dto.getLastName();
    }
}
