package ma.artisanat.user_service.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ma.artisanat.user_service.enums.UserStatus;


import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),

        })
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;



    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @NotBlank
    @Size(max = 100)
    @Email
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @NotBlank
    @Size(max = 225)
    private String password;
    @NotBlank
    @Size(max = 100)
    private String profilLogoUrl; // if marchand ==> profilLogoUrl == marchandLogoUrl

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password,String status,String profilLogoUrl ) {
        this.username = username;

        this.email = email;
        this.password = password;
        this.status=UserStatus.valueOf(status);
        this.profilLogoUrl=profilLogoUrl;
    }

    public User(String username, String firstName, String lastName, UserStatus status, String email, String password, String profilLogoUrl, Set<Role> roles) {
        this.username = username;

        this.status = status;
        this.email = email;
        this.password = password;
        this.profilLogoUrl = profilLogoUrl;
        this.roles = roles;
    }

    public User(Long id, Set<Role> roles, String profilLogoUrl, String password, String email, UserStatus status, String lastName, String firstName, String username) {
        this.id = id;
        this.roles = roles;
        this.profilLogoUrl = profilLogoUrl;
        this.password = password;
        this.email = email;
        this.status = status;

        this.username = username;
    }

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;

        this.password = password;

    }
    /*public User(String username, String email, String password) {
      this.username = username;
      this.email = email;
      this.password = password;
    }*/
    // Getter for status
    public UserStatus getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}



