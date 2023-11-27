package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.persist.dbos.TrackDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackDboRepository extends JpaRepository<TrackDbo, Integer> {

    public List<TrackDbo> findByArtistId(Integer artistId);
}
