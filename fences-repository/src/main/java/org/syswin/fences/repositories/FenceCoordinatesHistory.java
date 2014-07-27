package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.FenceCoordinatesHistoryRecord;

public interface FenceCoordinatesHistory extends JpaRepository<FenceCoordinatesHistoryRecord, Long> {
}
