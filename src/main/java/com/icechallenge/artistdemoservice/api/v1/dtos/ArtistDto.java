package com.icechallenge.artistdemoservice.api.v1.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ArtistDto {
    Integer id;
    String name;
    List<TrackDto> tracks;
    List<ArtistAliasDto> aliases;
}
