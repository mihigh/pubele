package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.ObjectiveFencesHistory;

public interface ObjectiveFencesHistoryRepository extends JpaRepository<ObjectiveFencesHistory, Long> {
}
