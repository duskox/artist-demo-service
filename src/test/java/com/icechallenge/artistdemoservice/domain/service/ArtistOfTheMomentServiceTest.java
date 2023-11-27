package com.icechallenge.artistdemoservice.domain.service;

import com.icechallenge.artistdemoservice.domain.ArtistRepository;
import com.icechallenge.artistdemoservice.domain.model.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistOfTheMomentServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    ArtistOfTheMomentService artistOfTheMomentService;

    @BeforeEach
    void setUp() {
        artistOfTheMomentService = new ArtistOfTheMomentService(artistRepository);
    }

    @Test
    void givenTestData_whenCyclingArtists_thenVerifyExpectedArtistIsChosen() {
        // given
        var artistOne = Artist.builder().id(1).name("Artist One").build();
        var artistTwo = Artist.builder().id(2).name("Artist Two").build();
        var artistThree = Artist.builder().id(3).name("Artist Three").build();
        List<Artist> artists = List.of(artistOne, artistTwo, artistThree);

        // when
        when(artistRepository.getAllArtistsAsc()).thenReturn(artists);
        when(artistRepository.getArtist(1)).thenReturn(artistOne);
        when(artistRepository.getArtist(2)).thenReturn(artistTwo);
        when(artistRepository.getArtist(3)).thenReturn(artistThree);


        // then
        assertEquals(artistOne, artistOfTheMomentService.getArtistOfTheMoment());

        // cycle first time
        artistOfTheMomentService.cycleArtistOfTheThreeMinutes();
        assertEquals(artistTwo, artistOfTheMomentService.getArtistOfTheMoment());

        // cycle second time
        artistOfTheMomentService.cycleArtistOfTheThreeMinutes();
        assertEquals(artistThree, artistOfTheMomentService.getArtistOfTheMoment());

        // cycle second time
        artistOfTheMomentService.cycleArtistOfTheThreeMinutes();
        assertEquals(artistOne, artistOfTheMomentService.getArtistOfTheMoment());

    }
}