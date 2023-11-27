package com.icechallenge.artistdemoservice.api.v1.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ArtistAliasDto {
    Long id;
    String alias;
    Long artistId;
}
