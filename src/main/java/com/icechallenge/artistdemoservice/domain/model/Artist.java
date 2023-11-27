package com.icechallenge.artistdemoservice.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
public class Artist {

    Integer id;
    String name;
    List<Track> tracks;
    List<ArtistAlias> aliases;
}
