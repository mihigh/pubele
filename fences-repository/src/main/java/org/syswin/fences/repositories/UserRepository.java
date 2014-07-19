package org.syswin.fences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.syswin.fences.models.UserRecord;

public interface UserRepository extends JpaRepository<UserRecord, Long> {

    @Query("select u from UserRecord u where u.username  = ?1")
    UserRecord findByUsername(String username);
}
