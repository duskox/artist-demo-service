package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.domain.model.Track;
import com.icechallenge.artistdemoservice.persist.dbos.TrackDbo;
import com.icechallenge.artistdemoservice.util.TrackMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackRepositoryImplTest {

    public static final String GENRE = "genre";
    public static final String NAME = "name";
    public static final int ARTIST_ID = 1;
    public static final int TRACK_ID = 2;

    @Mock
    private TrackDboRepository trackDboRepository;

    @Mock
    private TrackMapper trackMapper;

    private TrackRepositoryImpl trackRepositoryImpl;

    @BeforeEach
    void setUp() {
        trackRepositoryImpl = new TrackRepositoryImpl(trackDboRepository, trackMapper);
    }

    @Test
    void givenValidData_whenSavingTrack_thenValidatePopulatedTrackId() {
        // given
        var track = Track.builder()
                .artistId(ARTIST_ID)
                .genre(GENRE)
                .length(Duration.ofSeconds(10))
                .name(NAME)
                .build();

        var expected = Track.builder()
                .artistId(ARTIST_ID)
                .genre(GENRE)
                .length(Duration.ofSeconds(10))
                .name(NAME)
                .id(TRACK_ID)
                .build();

        var trackDbo = new TrackDbo();
        trackDbo.setArtistId(track.getArtistId());
        trackDbo.setGenre(track.getGenre());
        trackDbo.setLengthInSeconds(Math.toIntExact(track.getLength().getSeconds()));
        trackDbo.setName(track.getName());

        var savedTrackDbo = new TrackDbo();
        savedTrackDbo.setId(TRACK_ID);
        savedTrackDbo.setArtistId(track.getArtistId());
        savedTrackDbo.setGenre(track.getGenre());
        savedTrackDbo.setLengthInSeconds(Math.toIntExact(track.getLength().getSeconds()));
        savedTrackDbo.setName(track.getName());

        when(trackMapper.toDbo(track)).thenReturn(trackDbo);
        when(trackDboRepository.save(trackDbo)).thenReturn(savedTrackDbo);

        // when
        var result = trackRepositoryImpl.saveTrack(track);

        // then
        assertEquals(expected, result);
    }

    @Test
    void givenValidArtistId_whenFetchingTracks_validateResult() {
        // given
        var trackDbo = new TrackDbo();
        trackDbo.setArtistId(ARTIST_ID);
        trackDbo.setGenre(GENRE);
        trackDbo.setLengthInSeconds(10);
        trackDbo.setName(NAME);

        var expected = Track.builder()
                .artistId(ARTIST_ID)
                .genre(GENRE)
                .length(Duration.ofSeconds(10))
                .name(NAME)
                .build();

        when(trackDboRepository.findByArtistId(ARTIST_ID)).thenReturn(List.of(trackDbo));
        when(trackMapper.toDomain(List.of(trackDbo))).thenReturn(List.of(expected));

        // when
        var result = trackRepositoryImpl.findByArtistId(ARTIST_ID);

        // then
        assertEquals(List.of(expected), result);
    }
}