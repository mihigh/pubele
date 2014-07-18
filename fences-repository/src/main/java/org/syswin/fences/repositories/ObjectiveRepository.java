package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.Objective;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
}
