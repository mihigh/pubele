package org.syswin.fences.models;

import org.syswin.fences.models.enums.PermissionType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "permission", nullable = false)
    private PermissionType permission;

//    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    private Set<PermissionGroup> groups;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

    public Permission() {
    }

    public Permission(PermissionType permission, Set<PermissionGroup> groups, boolean deleted, Date createdDate, Date updatedDate, Date deletedDate) {
        this.permission = permission;
        this.groups = groups;
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

    public PermissionType getPermission() {
        return permission;
    }

    public void setPermission(PermissionType permission) {
        this.permission = permission;
    }

    public Set<PermissionGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<PermissionGroup> groups) {
        this.groups = groups;
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
