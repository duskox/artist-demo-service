package com.icechallenge.artistdemoservice.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ArtistAlias {

    Long id;
    String artistId;
    String alias;
}
