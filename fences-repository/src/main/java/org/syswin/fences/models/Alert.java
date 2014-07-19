package org.syswin.fences.models;

import org.syswin.fences.models.enums.AlertType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "type", nullable = false)
    private AlertType type;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

//    @ManyToMany(mappedBy = "alerts", fetch = FetchType.LAZY)
    @ManyToMany(mappedBy = "alerts", fetch = FetchType.EAGER)
    private Set<Objective> objectives;

    public Alert() {
    }

    public Alert(AlertType type, String phone, String email, Set<Objective> objectives) {
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.objectives = objectives;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AlertType getType() {
        return type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(Set<Objective> objectives) {
        this.objectives = objectives;
    }
}
