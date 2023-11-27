package com.icechallenge.artistdemoservice.domain.error;

import java.util.Map;

public class NoArtistsFoundException extends DomainException {
    public NoArtistsFoundException() {
        super("No artists found, we are dooooomed!!!!1", Map.of());
    }
}
