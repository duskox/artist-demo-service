package com.icechallenge.artistdemoservice.domain.service;

import com.icechallenge.artistdemoservice.domain.TrackRepository;
import com.icechallenge.artistdemoservice.domain.error.ArtistNotFoundException;
import com.icechallenge.artistdemoservice.domain.model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackServiceTest {

    private static final Integer ARTIST_ID = 1;
    private static final String TRACK_NAME = "track name";
    private static final String GENRE = "genre";
    private static final Integer LENGTH_IN_SECONDS = 10;
    public static final int TRACK_ID = 1;

    private TrackService trackService;

    @Mock
    TrackRepository trackRepository;


    @BeforeEach
    void setUp() {
        trackService = new TrackService(trackRepository);
    }

    @Test
    void givenValidParameters_whenCreatingTrack_thenTrackIsCreated() {
        // given
        var track = Track.builder()
                .artistId(ARTIST_ID)
                .name(TRACK_NAME)
                .genre(GENRE)
                .length(Duration.ofSeconds(LENGTH_IN_SECONDS))
                .build();

        var expected = Track.builder()
                .artistId(ARTIST_ID)
                .name(TRACK_NAME)
                .genre(GENRE)
                .length(Duration.ofSeconds(LENGTH_IN_SECONDS))
                .id(TRACK_ID)
                .build();
        when(trackRepository.saveTrack(track)).thenReturn(expected);

        // when
        var result = trackService.createTrack(ARTIST_ID, TRACK_NAME, GENRE, LENGTH_IN_SECONDS);

        // then
        assertEquals(expected, result);
    }

    @Test
    void givenInvalidArtistId_whenCreatingTrack_thenValidateThrownException() {
        // given
        var exception = new RuntimeException("Some message containing 23506 error code");
        var track = Track.builder()
                .artistId(ARTIST_ID)
                .name(TRACK_NAME)
                .genre(GENRE)
                .length(Duration.ofSeconds(LENGTH_IN_SECONDS))
                .build();

        when(trackRepository.saveTrack(track)).thenThrow(exception);

        // when
        // then
        assertThrows(ArtistNotFoundException.class, () ->
                trackService.createTrack(ARTIST_ID, TRACK_NAME, GENRE, LENGTH_IN_SECONDS));
    }

    @Test
    void givenExistingArtist_whenFetchingTracks_thenValidateTracks() {
        // given
        var trackOne = Track.builder()
                .artistId(ARTIST_ID)
                .name(TRACK_NAME)
                .genre(GENRE)
                .length(Duration.ofSeconds(LENGTH_IN_SECONDS))
                .build();

        var trackTwo = Track.builder()
                .artistId(ARTIST_ID)
                .name(TRACK_NAME)
                .genre(GENRE)
                .length(Duration.ofSeconds(LENGTH_IN_SECONDS))
                .build();

        var expected = List.of(trackOne, trackTwo);

        when(trackRepository.findByArtistId(ARTIST_ID)).thenReturn(List.of(trackOne, trackTwo));

        // when
        var result = trackService.getTracksByArtist(ARTIST_ID);

        // then
        assertEquals(expected, result);
    }

    @Test
    void givenWrongArtistId_whenFetchingTracks_thenValidateEmptyList() {
        // given
        var expected = List.of();

        when(trackRepository.findByArtistId(ARTIST_ID)).thenReturn(List.of());

        // when
        var result = trackService.getTracksByArtist(ARTIST_ID);

        // then
        assertEquals(expected, result);
    }
}