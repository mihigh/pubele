package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syswin.fences.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
