package com.microservices.songserver.repository;

import com.microservices.songserver.model.SongMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongMetadataRepository extends JpaRepository<SongMetadata, Long> {
}
