package org.syswin.fences.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fence_coordinates_history")
public class FenceCoordinatesHistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fence_id", referencedColumnName = "id")
    private FenceRecord fenceRecord;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "date", nullable = false)
    private Date date;

    public FenceCoordinatesHistoryRecord() {
    }

    public FenceCoordinatesHistoryRecord(FenceRecord fenceRecord, String latitude, String longitude, Date date) {
        this.fenceRecord = fenceRecord;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FenceRecord getFenceRecord() {
        return fenceRecord;
    }

    public void setFenceRecord(FenceRecord fenceRecord) {
        this.fenceRecord = fenceRecord;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
