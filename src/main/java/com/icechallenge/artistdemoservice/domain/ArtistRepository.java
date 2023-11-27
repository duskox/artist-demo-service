package com.icechallenge.artistdemoservice.domain;

import com.icechallenge.artistdemoservice.domain.model.Artist;

import java.util.List;

public interface ArtistRepository {

        Artist getArtist(Integer id);

        Artist saveArtist(Artist artist);

        List<Artist> getAllArtists();

        List<Artist> getAllArtistsAsc();
}
