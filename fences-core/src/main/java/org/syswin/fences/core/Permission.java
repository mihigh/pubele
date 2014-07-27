package org.syswin.fences.core;

import java.util.Date;

public class Permission {

    private long id;

    private String name;

    private User owner;

    private boolean deleted;

    private Date createdDate;

    private Date updatedDate;

    private Date deletedDate;

    /*  RIGHTS  */
    private boolean fencesRead;
    private boolean fencesReadWrite;

    private boolean objectivesRead;
    private boolean objectivesReadWrite;

    private boolean usersRead;
    private boolean usersReadUpdate;
    private boolean usersReadCreate;

    private boolean alertRead;
    private boolean alertReadWrite;

    private boolean logsRead;
    private boolean logsReadWrite;

    private boolean statisticsRead;

    public Permission() {
    }

    public Permission(long id, String name, User owner, boolean deleted, Date createdDate, Date updatedDate, Date deletedDate, boolean fencesRead, boolean fencesReadWrite, boolean objectivesRead, boolean objectivesReadWrite, boolean usersRead, boolean usersReadUpdate, boolean usersReadCreate, boolean alertRead, boolean alertReadWrite, boolean logsRead, boolean logsReadWrite, boolean statisticsRead) {
        this.id = id;
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

    public static Permission createAdminGroup(){
        return new Permission(1, "admin", null, false, new Date(), new Date(), null, true, true, true, true, true, true, true, true, true, true, true, true);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public boolean isFencesRead() {
        return fencesRead;
    }

    public void setFencesRead(boolean fencesRead) {
        this.fencesRead = fencesRead;
    }

    public boolean isFencesReadWrite() {
        return fencesReadWrite;
    }

    public void setFencesReadWrite(boolean fencesReadWrite) {
        this.fencesReadWrite = fencesReadWrite;
    }

    public boolean isObjectivesRead() {
        return objectivesRead;
    }

    public void setObjectivesRead(boolean objectivesRead) {
        this.objectivesRead = objectivesRead;
    }

    public boolean isObjectivesReadWrite() {
        return objectivesReadWrite;
    }

    public void setObjectivesReadWrite(boolean objectivesReadWrite) {
        this.objectivesReadWrite = objectivesReadWrite;
    }

    public boolean isUsersRead() {
        return usersRead;
    }

    public void setUsersRead(boolean usersRead) {
        this.usersRead = usersRead;
    }

    public boolean isUsersReadUpdate() {
        return usersReadUpdate;
    }

    public void setUsersReadUpdate(boolean usersReadUpdate) {
        this.usersReadUpdate = usersReadUpdate;
    }

    public boolean isUsersReadCreate() {
        return usersReadCreate;
    }

    public void setUsersReadCreate(boolean usersReadCreate) {
        this.usersReadCreate = usersReadCreate;
    }

    public boolean isAlertRead() {
        return alertRead;
    }

    public void setAlertRead(boolean alertRead) {
        this.alertRead = alertRead;
    }

    public boolean isAlertReadWrite() {
        return alertReadWrite;
    }

    public void setAlertReadWrite(boolean alertReadWrite) {
        this.alertReadWrite = alertReadWrite;
    }

    public boolean isLogsRead() {
        return logsRead;
    }

    public void setLogsRead(boolean logsRead) {
        this.logsRead = logsRead;
    }

    public boolean isLogsReadWrite() {
        return logsReadWrite;
    }

    public void setLogsReadWrite(boolean logsReadWrite) {
        this.logsReadWrite = logsReadWrite;
    }

    public boolean isStatisticsRead() {
        return statisticsRead;
    }

    public void setStatisticsRead(boolean statisticsRead) {
        this.statisticsRead = statisticsRead;
    }
}
