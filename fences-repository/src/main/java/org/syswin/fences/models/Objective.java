package org.syswin.fences.models;

import org.syswin.fences.models.enums.ObjectiveStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "objectives")
public class Objective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "status", nullable = false)
    private ObjectiveStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objective")
    private List<Fence> fenceList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objective")
    @OrderBy("date desc")
    private List<ObjectiveFencesHistory> fencesHistoryList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "objective_alerts",
            joinColumns = @JoinColumn(name="objective_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="alert_id", referencedColumnName="id"))
    private Set<Alert> alerts;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

    public Objective() {
    }

    public Objective(ObjectiveStatus status, User owner, List<Fence> fenceList, List<ObjectiveFencesHistory> fencesHistoryList, Set<Alert> alerts, boolean deleted, Date createdDate, Date updatedDate, Date deletedDate) {
        this.status = status;
        this.owner = owner;
        this.fenceList = fenceList;
        this.fencesHistoryList = fencesHistoryList;
        this.alerts = alerts;
        this.deleted = deleted;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ObjectiveStatus getStatus() {
        return status;
    }

    public void setStatus(ObjectiveStatus status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Fence> getFenceList() {
        return fenceList;
    }

    public void setFenceList(List<Fence> fenceList) {
        this.fenceList = fenceList;
    }

    public List<ObjectiveFencesHistory> getFencesHistoryList() {
        return fencesHistoryList;
    }

    public void setFencesHistoryList(List<ObjectiveFencesHistory> fencesHistoryList) {
        this.fencesHistoryList = fencesHistoryList;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(Set<Alert> alerts) {
        this.alerts = alerts;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
