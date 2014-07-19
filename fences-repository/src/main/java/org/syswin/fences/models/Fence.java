package org.syswin.fences.models;

import org.syswin.fences.models.enums.FenceStatus;
import org.syswin.fences.models.enums.FenceType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fences")
public class Fence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "code", nullable = false)
    private long code;

    @Column(name = "type", nullable = false)
    private FenceType type;

    @Column(name = "status", nullable = false)
    private FenceStatus status;

    @Column(name = "gps_enabled", nullable = false)
    private boolean gpsEnabled;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "last_coordinates_date")
    private Date lastCoordinatesDate;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fence")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fence")
    @OrderBy("date desc")
    private List<FenceCoordinatesHistory> coordinatesHistoryList;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "objective_id", referencedColumnName = "id")
    private Objective objective;

    @Column(name = "added_to_objective_date")
    private Date addedToObjectiveDate;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

    public Fence() {
    }

    public Fence(long code, FenceType type, FenceStatus status, boolean gpsEnabled, String latitude, String longitude, Date lastCoordinatesDate, List<FenceCoordinatesHistory> coordinatesHistoryList, Objective objective, Date addedToObjectiveDate, boolean deleted, Date createdDate, Date updatedDate, Date deletedDate) {
        this.code = code;
        this.type = type;
        this.status = status;
        this.gpsEnabled = gpsEnabled;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastCoordinatesDate = lastCoordinatesDate;
        this.coordinatesHistoryList = coordinatesHistoryList;
        this.objective = objective;
        this.addedToObjectiveDate = addedToObjectiveDate;
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

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public FenceType getType() {
        return type;
    }

    public void setType(FenceType type) {
        this.type = type;
    }

    public FenceStatus getStatus() {
        return status;
    }

    public void setStatus(FenceStatus status) {
        this.status = status;
    }

    public boolean getGpsEnabled() {
        return gpsEnabled;
    }

    public void setGpsEnabled(boolean gpsEnabled) {
        this.gpsEnabled = gpsEnabled;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getLastCoordinatesDate() {
        return lastCoordinatesDate;
    }

    public void setLastCoordinatesDate(Date lastCoordinatesDate) {
        this.lastCoordinatesDate = lastCoordinatesDate;
    }

    public List<FenceCoordinatesHistory> getCoordinatesHistoryList() {
        return coordinatesHistoryList;
    }

    public void setCoordinatesHistoryList(List<FenceCoordinatesHistory> coordinatesHistoryList) {
        this.coordinatesHistoryList = coordinatesHistoryList;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Date getAddedToObjectiveDate() {
        return addedToObjectiveDate;
    }

    public void setAddedToObjectiveDate(Date addedToObjectiveDate) {
        this.addedToObjectiveDate = addedToObjectiveDate;
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
