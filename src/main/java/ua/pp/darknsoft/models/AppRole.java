package ua.pp.darknsoft.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APP_ROLE", uniqueConstraints = {@UniqueConstraint(name = "APP_ROLE_UC", columnNames = "role_name")})
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name", length = 36, nullable = false)
    @Pattern.List({
            @Pattern(regexp = "^ROLE_[A-Z0-9]{2,36}$"),
            @Pattern(regexp = "^[A-Z]")
    })
    private String roleName;
    @NotNull
    private LocalDateTime created = LocalDateTime.now();
    @Version
    private Long version;
    @OneToMany(mappedBy = "appRole")
    private Set<RoledUser> roledUsers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<RoledUser> getRoledUsers() {
        return roledUsers;
    }

    public void setRoledUsers(Set<RoledUser> roledUsers) {
        this.roledUsers = roledUsers;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
