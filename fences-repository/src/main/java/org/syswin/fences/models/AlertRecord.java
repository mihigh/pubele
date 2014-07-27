package org.syswin.fences.models;

import org.syswin.fences.models.enums.AlertType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alerts")
public class AlertRecord {

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
    private Set<ObjectiveRecord> objectiveRecords;

    public AlertRecord() {
    }

    public AlertRecord(AlertType type, String phone, String email, Set<ObjectiveRecord> objectiveRecords) {
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.objectiveRecords = objectiveRecords;
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

    public Set<ObjectiveRecord> getObjectiveRecords() {
        return objectiveRecords;
    }

    public void setObjectiveRecords(Set<ObjectiveRecord> objectiveRecords) {
        this.objectiveRecords = objectiveRecords;
    }
}
