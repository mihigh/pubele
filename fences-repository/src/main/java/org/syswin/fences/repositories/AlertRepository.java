package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
