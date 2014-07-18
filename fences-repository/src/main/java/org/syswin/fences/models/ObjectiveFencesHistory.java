package org.syswin.fences.models;

import org.syswin.fences.models.enums.FenceEvent;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "objective_fences_history")
public class ObjectiveFencesHistory {

    //TODO: check if this is still needed after adding the 'events' table

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fence_id", referencedColumnName = "id")
    private Fence fence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objective_id", referencedColumnName = "id")
    private Objective objective;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "event", nullable = false)
    private FenceEvent event;

    public ObjectiveFencesHistory() {
    }

    public ObjectiveFencesHistory(Fence fence, Objective objective, Date date, FenceEvent event) {
        this.fence = fence;
        this.objective = objective;
        this.date = date;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Fence getFence() {
        return fence;
    }

    public void setFence(Fence fence) {
        this.fence = fence;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FenceEvent getEvent() {
        return event;
    }

    public void setEvent(FenceEvent event) {
        this.event = event;
    }
}

