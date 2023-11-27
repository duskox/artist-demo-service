package com.icechallenge.artistdemoservice.domain.error;

import java.util.Map;

public class ArtistNotFoundException extends DomainException {

    public ArtistNotFoundException(Integer id) {
        super("Artist not found", Map.of("id", id.toString()));
    }
}
