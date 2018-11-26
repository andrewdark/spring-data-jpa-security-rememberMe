package ua.pp.darknsoft.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

    @Column(name = "username")
    private String userName;
    @Id
    @Column(name = "series")
    private String series;
    @Column(name = "token")
    private String token;
    @Column(name = "last_used")
    private LocalDateTime lastUsed;

}
