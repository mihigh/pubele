package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.PermissionGroup;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
}
