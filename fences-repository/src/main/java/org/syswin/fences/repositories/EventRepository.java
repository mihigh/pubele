package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
