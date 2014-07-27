package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.AlertRecord;

public interface AlertRepository extends JpaRepository<AlertRecord, Long> {
}
