package com.icechallenge.artistdemoservice.util;

import com.icechallenge.artistdemoservice.api.v1.dtos.ArtistDto;
import com.icechallenge.artistdemoservice.domain.model.Artist;
import com.icechallenge.artistdemoservice.persist.dbos.ArtistDbo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(target = "tracks", ignore = true)
    @Mapping(target = "aliases", ignore = true)
    Artist toDomain(ArtistDbo dbo);

    ArtistDbo toDbo(Artist domain);

    @Mapping(target = "tracks", ignore = true)
    ArtistDto toDto(Artist domain);

    List<Artist> toDomain(List<ArtistDbo> dbos);
}
