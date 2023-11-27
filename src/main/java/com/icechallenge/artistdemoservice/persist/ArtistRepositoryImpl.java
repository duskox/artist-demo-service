package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.domain.ArtistRepository;
import com.icechallenge.artistdemoservice.domain.error.ArtistNotFoundException;
import com.icechallenge.artistdemoservice.domain.model.Artist;
import com.icechallenge.artistdemoservice.persist.dbos.ArtistDbo;
import com.icechallenge.artistdemoservice.util.AliasMapper;
import com.icechallenge.artistdemoservice.util.ArtistMapper;
import com.icechallenge.artistdemoservice.util.TrackMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistRepositoryImpl implements ArtistRepository {

    private final ArtistDboRepository artistDboRepository;
    private final TrackDboRepository trackDboRepository;
    private final ArtistAliasDboRepository artistAliasDboRepository;
    private final ArtistMapper artistMapper;
    private final TrackMapper trackMapper;
    private final AliasMapper aliasMapper;

    public ArtistRepositoryImpl(ArtistDboRepository artistDboRepository,
                                TrackDboRepository trackDboRepository,
                                ArtistAliasDboRepository artistAliasDboRepository,
                                ArtistMapper artistMapper,
                                TrackMapper trackMapper,
                                AliasMapper aliasMapper) {
        this.artistDboRepository = artistDboRepository;
        this.trackDboRepository = trackDboRepository;
        this.artistAliasDboRepository = artistAliasDboRepository;
        this.artistMapper = artistMapper;
        this.trackMapper = trackMapper;
        this.aliasMapper = aliasMapper;
    }

    @Override
    public Artist getArtist(Integer id) {
        var artistDbo = artistDboRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));
        var trackDbos = trackDboRepository.findByArtistId(id);
        var artistAliasDbos = artistAliasDboRepository.findByArtistId(id);
        var tracks = trackMapper.toDomain(trackDbos);
        var aliases = aliasMapper.toDomain(artistAliasDbos);
        var incompleteArtist = artistMapper.toDomain(artistDbo);

        return incompleteArtist.toBuilder()
                .tracks(tracks)
                .aliases(aliases)
                .build();
    }

    @Override
    public Artist saveArtist(Artist artist) {
        var artistDbo = artistMapper.toDbo(artist);
        var savedArtistDbo = artistDboRepository.save(artistDbo);
        return artist.toBuilder()
                .id(savedArtistDbo.getId())
                .build();
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistMapper.toDomain(artistDboRepository.findAll());
    }

    @Override
    public List<Artist> getAllArtistsAsc() {
        return artistMapper.toDomain(artistDboRepository.findAllByOrderByIdAsc());
    }
}
