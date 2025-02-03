package com.microservices.resourceserver.repository;

import com.microservices.resourceserver.model.Mp3Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mp3DataRepository extends JpaRepository<Mp3Data, Long> {
    
}
