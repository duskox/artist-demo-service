package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.domain.ArtistAliasRepository;
import com.icechallenge.artistdemoservice.domain.model.ArtistAlias;
import com.icechallenge.artistdemoservice.util.AliasMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistAliasRepositoryImpl implements ArtistAliasRepository {

    private final ArtistAliasDboRepository artistAliasDboRepository;
    private final AliasMapper aliasMapper;

    public ArtistAliasRepositoryImpl(ArtistAliasDboRepository artistAliasDboRepository,
                                     AliasMapper aliasMapper) {
        this.artistAliasDboRepository = artistAliasDboRepository;
        this.aliasMapper = aliasMapper;
    }

    @Override
    public void saveArtistAlias(ArtistAlias artistAlias) {
        var dbo = aliasMapper.toDbo(artistAlias);
        artistAliasDboRepository.save(dbo);
    }

    @Override
    public List<ArtistAlias> findByArtistId(Integer artistId) {
        var artistAliasDbos = artistAliasDboRepository.findByArtistId(artistId);
        List<ArtistAlias> artistAliases = new ArrayList<>();
        artistAliasDbos.forEach(artistAliasDbo -> {
            var artistAlias = aliasMapper.toDomain(artistAliasDbo);
            artistAliases.add(artistAlias);
        });
        return artistAliases;
    }

    @Override
    public List<Long> findArtistIdsByAlias(String alias) {
        return artistAliasDboRepository.findArtistIdsByAlias(alias);
    }
}
