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

    @ManyToMany(mappedBy = "alerts", fetch = FetchType.LAZY)
    private Set<Objective> objectives;

    public Alert() {
    }

    public Alert(AlertType type, Set<Objective> objectives) {
        this.type = type;
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

    public Set<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(Set<Objective> objectives) {
        this.objectives = objectives;
    }
}
