package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.PermissionRecord;

public interface PermissionRepository extends JpaRepository<PermissionRecord, Long> {
}
