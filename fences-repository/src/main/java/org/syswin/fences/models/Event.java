package org.syswin.fences.models;


import org.syswin.fences.models.enums.EventSeverity;
import org.syswin.fences.models.enums.EventType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    // TODO: To clean-up the database when it gets filled too much

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "severity", nullable = false)
    private EventSeverity severity;

    @Column(name = "type", nullable = false)
    private EventType type;

    @Column(name = "arguments", nullable = false)
    private String arguments;

    public Event() {
    }

    public Event(Date date, EventSeverity severity, EventType type, String arguments) {
        this.date = date;
        this.severity = severity;
        this.type = type;
        this.arguments = arguments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(EventSeverity severity) {
        this.severity = severity;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}

