package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.FenceRecord;

public interface FenceRepository extends JpaRepository<FenceRecord, Long> {
}
