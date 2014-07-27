package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.EventRecord;

public interface EventRepository extends JpaRepository<EventRecord, Long> {
}
