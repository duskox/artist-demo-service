package com.icechallenge.artistdemoservice.domain.service;

import com.icechallenge.artistdemoservice.domain.ArtistAliasRepository;
import com.icechallenge.artistdemoservice.domain.model.ArtistAlias;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistAliasService {

    private final ArtistAliasRepository artistAliasRepository;

    public ArtistAliasService(ArtistAliasRepository artistAliasRepository) {
        this.artistAliasRepository = artistAliasRepository;
    }

    public List<ArtistAlias> getArtistAliases(Integer id) {
        return artistAliasRepository.findByArtistId(id);
    }

}
