package com.icechallenge.artistdemoservice.domain.error;

import java.util.Map;

public class TrackNotFoundException extends DomainException {
    public TrackNotFoundException(Long id) {
        super("Track not found", Map.of("id", id.toString()));
    }
}
