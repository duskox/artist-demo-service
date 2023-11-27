package com.icechallenge.artistdemoservice.domain;

import com.icechallenge.artistdemoservice.domain.model.ArtistAlias;

import java.util.List;

public interface ArtistAliasRepository {

        void saveArtistAlias(ArtistAlias artistAlias);

        List<ArtistAlias> findByArtistId(Integer artistId);
        List<Long> findArtistIdsByAlias(String alias);
}
