package com.icechallenge.artistdemoservice.domain.service;

import com.icechallenge.artistdemoservice.domain.TrackRepository;
import com.icechallenge.artistdemoservice.domain.error.ArtistNotFoundException;
import com.icechallenge.artistdemoservice.domain.error.UnknownException;
import com.icechallenge.artistdemoservice.domain.model.Track;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class TrackService {

    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track createTrack(Integer artistId,
                             String name,
                             String genre,
                             Integer lengthInSeconds) {

        var track = Track
                .builder()
                .artistId(artistId)
                .name(name)
                .genre(genre)
                .length(Duration.ofSeconds(lengthInSeconds))
                .build();
        try {
            return trackRepository.saveTrack(track);
        } catch (Exception e) {
            if (e.getMessage().contains("23506")) {
                throw new ArtistNotFoundException(artistId);
            }
            throw new UnknownException(e.getMessage(), null);
        }
    }

    public List<Track> getTracksByArtist(Integer artistId) {
        return trackRepository.findByArtistId(artistId);
    }


}
