package com.icechallenge.artistdemoservice.domain;

import com.icechallenge.artistdemoservice.domain.model.Track;

import java.util.List;

public interface TrackRepository {

    List<Track> findByArtistId(Integer artistId);
    Track saveTrack(Track track);
}
