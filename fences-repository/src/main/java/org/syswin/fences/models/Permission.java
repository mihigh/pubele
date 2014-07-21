package org.syswin.fences.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserRecord owner;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

    /*  RIGHTS  */
    @Column(name = "fences_R")
    private boolean fencesRead;
    @Column(name = "fences_RW")
    private boolean fencesReadWrite;

    @Column(name = "objectives_R")
    private boolean objectivesRead;
    @Column(name = "objectives_RW")
    private boolean objectivesReadWrite;

    @Column(name = "users_R")
    private boolean usersRead;
    @Column(name = "users_RUpdate")
    private boolean usersReadUpdate;
    @Column(name = "users_RCreate")
    private boolean usersReadCreate;

    @Column(name = "alert_R")
    private boolean alertRead;
    @Column(name = "alert_RW")
    private boolean alertReadWrite;

    @Column(name = "logs_R")
    private boolean logsRead;
    @Column(name = "logs_RW")
    private boolean logsReadWrite;

    @Column(name = "statistics_R")
    private boolean statisticsRead;

    public Permission() {
    }

    public Permission (String name, UserRecord owner, boolean deleted, Date createdDate, Date updatedDate,
                       Date deletedDate, boolean fencesRead, boolean fencesReadWrite, boolean objectivesRead, boolean objectivesReadWrite, boolean usersRead, boolean usersReadUpdate, boolean usersReadCreate, boolean alertRead, boolean alertReadWrite, boolean logsRead, boolean logsReadWrite, boolean statisticsRead) {
        this.name = name;
        this.owner = owner;
        this.deleted = deleted;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.fencesRead = fencesRead;
        this.fencesReadWrite = fencesReadWrite;
        this.objectivesRead = objectivesRead;
        this.objectivesReadWrite = objectivesReadWrite;
        this.usersRead = usersRead;
        this.usersReadUpdate = usersReadUpdate;
        this.usersReadCreate = usersReadCreate;
        this.alertRead = alertRead;
        this.alertReadWrite = alertReadWrite;
        this.logsRead = logsRead;
        this.logsReadWrite = logsReadWrite;
        this.statisticsRead = statisticsRead;
    }

    /**
     * Creates an Permission with all the rights.
     * @return Permission
     */
    public static Permission createAdminGroup(){
        return new Permission("admin", null, false, new Date(), new Date(), null, true, true, true, true, true, true, true, true, true, true, true, true);
    }

    public boolean isUsersRead () {
        return usersRead;
    }

    public void setUsersRead (boolean usersRead) {
        this.usersRead = usersRead;
    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public UserRecord getOwner () {
        return owner;
    }

    public void setOwner (UserRecord owner) {
        this.owner = owner;
    }

    public boolean isDeleted () {
        return deleted;
    }

    public void setDeleted (boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedDate () {
        return createdDate;
    }

    public void setCreatedDate (Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate () {
        return updatedDate;
    }

    public void setUpdatedDate (Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getDeletedDate () {
        return deletedDate;
    }

    public void setDeletedDate (Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public boolean isFencesRead () {
        return fencesRead;
    }

    public void setFencesRead (boolean fencesRead) {
        this.fencesRead = fencesRead;
    }

    public boolean isFencesReadWrite () {
        return fencesReadWrite;
    }

    public void setFencesReadWrite (boolean fencesReadWrite) {
        this.fencesReadWrite = fencesReadWrite;
    }

    public boolean isObjectivesRead () {
        return objectivesRead;
    }

    public void setObjectivesRead (boolean objectivesRead) {
        this.objectivesRead = objectivesRead;
    }

    public boolean isObjectivesReadWrite () {
        return objectivesReadWrite;
    }

    public void setObjectivesReadWrite (boolean objectivesReadWrite) {
        this.objectivesReadWrite = objectivesReadWrite;
    }

    public boolean isUsersReadUpdate () {
        return usersReadUpdate;
    }

    public void setUsersReadUpdate (boolean usersReadUpdate) {
        this.usersReadUpdate = usersReadUpdate;
    }

    public boolean isUsersReadCreate () {
        return usersReadCreate;
    }

    public void setUsersReadCreate (boolean usersReadCreate) {
        this.usersReadCreate = usersReadCreate;
    }

    public boolean isAlertRead () {
        return alertRead;
    }

    public void setAlertRead (boolean alertRead) {
        this.alertRead = alertRead;
    }

    public boolean isAlertReadWrite () {
        return alertReadWrite;
    }

    public void setAlertReadWrite (boolean alertReadWrite) {
        this.alertReadWrite = alertReadWrite;
    }

    public boolean isLogsRead () {
        return logsRead;
    }

    public void setLogsRead (boolean logsRead) {
        this.logsRead = logsRead;
    }

    public boolean isLogsReadWrite () {
        return logsReadWrite;
    }

    public void setLogsReadWrite (boolean logsReadWrite) {
        this.logsReadWrite = logsReadWrite;
    }

    public boolean isStatisticsRead () {
        return statisticsRead;
    }

    public void setStatisticsRead (boolean statisticsRead) {
        this.statisticsRead = statisticsRead;
    }
}
