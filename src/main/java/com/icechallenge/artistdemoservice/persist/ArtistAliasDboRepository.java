package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.persist.dbos.ArtistAliasDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistAliasDboRepository extends JpaRepository<ArtistAliasDbo, Integer> {
    List<ArtistAliasDbo> findByArtistId(Integer artistId);
    List<Long> findArtistIdsByAlias(String alias);
}
