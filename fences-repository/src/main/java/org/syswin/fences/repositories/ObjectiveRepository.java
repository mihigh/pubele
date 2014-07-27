package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.ObjectiveRecord;

public interface ObjectiveRepository extends JpaRepository<ObjectiveRecord, Long> {
}
