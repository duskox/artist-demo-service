package com.icechallenge.artistdemoservice.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.Duration;

@Value
@Builder(toBuilder = true)
public class Track {

    Integer id;
    Integer artistId;
    String genre;
    String name;
    Duration length;
}
