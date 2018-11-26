package ua.pp.darknsoft.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APP_USER", uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UC", columnNames = "user_name")})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", length = 36, nullable = false)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{2,36}$")
    private String userName;
    @Column(name = "encrypted_password", length = 128, nullable = false)
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private String encryptedPassword;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
    @NotNull
    private LocalDateTime created = LocalDateTime.now();
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Version
    private Long version;
    @OneToMany(mappedBy = "appUser")
    private Set<RoledUser> roledUsers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Set<RoledUser> getRoledUsers() {
        return roledUsers;
    }

    public void setRoledUsers(Set<RoledUser> roledUsers) {
        this.roledUsers = roledUsers;
    }
}
