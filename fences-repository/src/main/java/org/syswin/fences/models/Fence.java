package org.syswin.fences.models;

import org.syswin.fences.models.enums.FenceStatus;
import org.syswin.fences.models.enums.FenceType;

import javax.persistence.*;

@Entity

@Table(name = "fences")
public class Fence {

    //TODO: @Codruta use nullable: @Column(name = "last_name", nullable = false)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long code;

    private FenceType type;

    private FenceStatus status;

    @Column(name = "gps_status")
    private String gpsStatus;

    public Fence() {
    }

    public Fence(long code, FenceType type, FenceStatus status, String gpsStatus) {
        this.code = code;
        this.type = type;
        this.status = status;
        this.gpsStatus = gpsStatus;
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

    public String getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(String gpsStatus) {
        this.gpsStatus = gpsStatus;
    }
}
