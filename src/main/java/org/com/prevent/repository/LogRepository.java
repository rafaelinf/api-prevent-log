package org.com.prevent.repository;

import org.com.prevent.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LogRepository extends JpaRepository<Log, Long> {
    
    @Query("SELECT l FROM Log l WHERE l.ip = :ip")     // Spring JPA In cause using @Query
    Log findByIp(@Param("ip") String ip);    

}
