package org.syswin.fences.models;

import javax.persistence.*;
import java.util.Date;

@Entity

@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //TODO: @codruta this will be a foreign key
    private String owner;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

    public Mission(String owner, Date createdDate, Date deletedDate) {
        this.owner = owner;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
