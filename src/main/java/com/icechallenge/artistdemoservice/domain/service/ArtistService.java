package com.icechallenge.artistdemoservice.domain.service;

import com.icechallenge.artistdemoservice.domain.ArtistRepository;
import com.icechallenge.artistdemoservice.domain.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final TrackService trackService;

    public ArtistService(ArtistRepository artistRepository,
                         TrackService trackService) {
        this.artistRepository = artistRepository;
        this.trackService = trackService;
    }

    public Artist getArtist(Integer id) {
        return artistRepository.getArtist(id);
    }

    public Artist createArtist(String name) {
        var artist = Artist
                .builder()
                .name(name)
                .build();
        return artistRepository.saveArtist(artist);
    }

    public Artist updateArtist(Integer artistId, String name) {
        var artistToUpdate = artistRepository.getArtist(artistId);
        var updatedArtist = artistToUpdate.toBuilder()
                .name(name)
                .build();
        return artistRepository.saveArtist(updatedArtist);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.getAllArtists();
    }

    public Artist cycleArtistOfTheThreeMinutes() {
        return null;
    }
}
