package com.icechallenge.artistdemoservice.domain.service;

import com.icechallenge.artistdemoservice.domain.ArtistRepository;
import com.icechallenge.artistdemoservice.domain.model.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    public static final Integer ARTIST_ID = 1;
    public static final String TRACK_ID = "trackId";
    public static final String ARTIST_NAME = "artist name";
    public static final String ANOTHER_ARTIST_NAME = "another artist name";

    @Mock
    private ArtistRepository mockArtistRepository;

    @Mock
    private TrackService mockTrackService;

    ArtistService artistService;

    @BeforeEach
    void setUp() {
        artistService = new ArtistService(mockArtistRepository, mockTrackService);
    }

    @Test
    void whenCreatingArtist_givenCorrectParameters_thenArtistIsCreated() {
        // given
        var expectedArtist = Artist.builder()
                .id(ARTIST_ID)
                .name(ARTIST_NAME)
                .build();
        when(mockArtistRepository.saveArtist(any(Artist.class))).thenReturn(expectedArtist);

        // when
        var result = artistService.createArtist(ARTIST_NAME);

        // then
        assertEquals(expectedArtist, result);
    }

    @Test
    void whenUpdatingArtist_givenCorrectParameters_thenArtistIsUpdated() {
        // given
        var initialArtist = Artist.builder()
                .id(ARTIST_ID)
                .name(ARTIST_NAME)
                .build();

        var expectedArtist = Artist.builder()
                .id(ARTIST_ID)
                .name(ANOTHER_ARTIST_NAME)
                .build();

        when(mockArtistRepository.getArtist(ARTIST_ID)).thenReturn(initialArtist);
        when(mockArtistRepository.saveArtist(expectedArtist)).thenReturn(expectedArtist);

        // when
        var result = artistService.updateArtist(ARTIST_ID, ANOTHER_ARTIST_NAME);

        // then
        assertEquals(expectedArtist, result);
    }
}