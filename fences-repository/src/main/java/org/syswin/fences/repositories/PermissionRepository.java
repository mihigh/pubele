package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
