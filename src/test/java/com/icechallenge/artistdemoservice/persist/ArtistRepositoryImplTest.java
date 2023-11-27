package com.icechallenge.artistdemoservice.persist;

import com.icechallenge.artistdemoservice.domain.model.Artist;
import com.icechallenge.artistdemoservice.persist.dbos.ArtistDbo;
import com.icechallenge.artistdemoservice.util.AliasMapper;
import com.icechallenge.artistdemoservice.util.ArtistMapper;
import com.icechallenge.artistdemoservice.util.TrackMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistRepositoryImplTest {

    public static final String ARTIST_NAME = "name";
    public static final String NEW_ARTIST_NAME = "new name";
    @Mock
    private ArtistDboRepository artistDboRepository;
    @Mock
    private TrackDboRepository trackDboRepository;
    @Mock
    private ArtistAliasDboRepository artistAliasDboRepository;
    @Mock
    private ArtistMapper artistMapper;
    @Mock
    private TrackMapper trackMapper;
    @Mock
    private AliasMapper aliasMapper;

    private ArtistRepositoryImpl artistRepositoryImpl;

    @BeforeEach
    void setUp() {
        artistRepositoryImpl = new ArtistRepositoryImpl(artistDboRepository,
                trackDboRepository,
                artistAliasDboRepository,
                artistMapper,
                trackMapper,
                aliasMapper);
    }

    @Test
    void givenValidData_whenSavingArtist_thenValidatePopulatedArtistId() {
        // given
        var artistToSave = Artist.builder()
                .name(ARTIST_NAME)
                .build();

        var expected = Artist.builder()
                .name(ARTIST_NAME)
                .id(1)
                .build();

        var artistDbo = new ArtistDbo();
        artistDbo.setName(ARTIST_NAME);

        var savedArtistDbo = new ArtistDbo();
        savedArtistDbo.setName(ARTIST_NAME);
        savedArtistDbo.setId(1);

        when(artistMapper.toDbo(artistToSave)).thenReturn(artistDbo);
        when(artistDboRepository.save(artistDbo)).thenReturn(savedArtistDbo);

        // when
        var actual = artistRepositoryImpl.saveArtist(artistToSave);

        // then
        assertEquals(expected, actual);
    }

}