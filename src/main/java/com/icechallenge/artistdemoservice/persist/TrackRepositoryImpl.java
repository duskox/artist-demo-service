package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.domain.TrackRepository;
import com.icechallenge.artistdemoservice.domain.model.Track;
import com.icechallenge.artistdemoservice.util.TrackMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackRepositoryImpl implements TrackRepository {

    private final TrackDboRepository trackDboRepository;
    private final TrackMapper trackMapper;

    public TrackRepositoryImpl(TrackDboRepository trackDboRepository,
                               TrackMapper trackMapper) {
        this.trackDboRepository = trackDboRepository;
        this.trackMapper = trackMapper;
    }

    @Override
    public List<Track> findByArtistId(Integer artistId) {
        return trackMapper.toDomain(trackDboRepository.findByArtistId(artistId));
    }

    @Override
    public Track saveTrack(Track track) {
        var trackDbo = trackMapper.toDbo(track);
        var savedTrackDbo = trackDboRepository.save(trackDbo);
        return track.toBuilder()
                .id(savedTrackDbo.getId())
                .build();
    }
}
