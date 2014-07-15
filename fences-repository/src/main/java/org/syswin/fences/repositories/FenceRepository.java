package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.Fence;

public interface FenceRepository extends JpaRepository<Fence, Long> {
}
