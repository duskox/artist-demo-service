package com.icechallenge.artistdemoservice.domain.service;

import com.icechallenge.artistdemoservice.domain.ArtistRepository;
import com.icechallenge.artistdemoservice.domain.error.NoArtistsFoundException;
import com.icechallenge.artistdemoservice.domain.model.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ArtistOfTheMomentService {

    Logger logger = LoggerFactory.getLogger(ArtistOfTheMomentService.class);

    private final ArtistRepository artistRepository;

    private Integer artistOfTheMomentId;

    public ArtistOfTheMomentService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist getArtistOfTheMoment() {
        if (artistOfTheMomentId == null) {
            cycleArtistOfTheThreeMinutes();
        }
        return artistRepository.getArtist(artistOfTheMomentId);
    }

    @Scheduled(fixedRate = 180000)
    public void cycleArtistOfTheThreeMinutes() {
        var artists = artistRepository.getAllArtistsAsc();

        if (artistOfTheMomentId == null) {
            if (artists.isEmpty()) {
                throw new NoArtistsFoundException();
            }
            artistOfTheMomentId = artists.get(0).getId();
        } else {
            var index = artists.indexOf(artistRepository.getArtist(artistOfTheMomentId));
            if (index == artists.size() - 1) {
                artistOfTheMomentId = artists.get(0).getId();
            } else {
                artistOfTheMomentId = artists.get(index + 1).getId();
            }
        }
    }
}
