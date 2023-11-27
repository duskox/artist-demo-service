package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.persist.dbos.ArtistDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistDboRepository extends JpaRepository<ArtistDbo, Integer> {
    List<ArtistDbo> findAllByOrderByIdAsc();
}
