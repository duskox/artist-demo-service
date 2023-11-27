package com.icechallenge.artistdemoservice.api.v1;

import com.icechallenge.artistdemoservice.api.v1.dtos.ArtistDto;
import com.icechallenge.artistdemoservice.api.v1.dtos.UpdateArtistDto;
import com.icechallenge.artistdemoservice.domain.service.ArtistAliasService;
import com.icechallenge.artistdemoservice.domain.service.ArtistOfTheMomentService;
import com.icechallenge.artistdemoservice.domain.service.ArtistService;
import com.icechallenge.artistdemoservice.domain.service.TrackService;
import com.icechallenge.artistdemoservice.util.AliasMapper;
import com.icechallenge.artistdemoservice.util.ArtistMapper;
import com.icechallenge.artistdemoservice.util.TrackMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistsController {

    private final ArtistService artistService;
    private final TrackService trackService;
    private final ArtistOfTheMomentService artistOfTheMomentService;
    private final ArtistAliasService artistAliasService;

    private final ArtistMapper artistMapper;
    private final TrackMapper trackMapper;
    private final AliasMapper aliasMapper;

    public ArtistsController(ArtistService artistService,
                             ArtistMapper artistMapper,
                             TrackService trackService,
                             TrackMapper trackMapper,
                             ArtistAliasService artistAliasService,
                             AliasMapper artistAliasMapper,
                             ArtistOfTheMomentService artistOfTheMomentService) {
        this.artistService = artistService;
        this.artistMapper = artistMapper;
        this.trackService = trackService;
        this.trackMapper = trackMapper;
        this.artistAliasService = artistAliasService;
        this.aliasMapper = artistAliasMapper;
        this.artistOfTheMomentService = artistOfTheMomentService;
    }

    @GetMapping
    public Mono<List<ArtistDto>> getArtists() {
        return Mono.just(artistService.getAllArtists())
                .flatMapMany(Flux::fromIterable)
                .map(artistMapper::toDto)
                .map(this::populateArtistDto)
                .collectList();
    }

    @GetMapping("/{artistId}")
    public Mono<ArtistDto> getArtist(@PathVariable String artistId) {
        return Mono.just(artistId)
                .map(Integer::parseInt)
                .map(artistService::getArtist)
                .map(artistMapper::toDto)
                .map(this::populateArtistDto);
    }

    @PutMapping("/{artistId}")
    public Mono<ArtistDto> updateArtist(@PathVariable String artistId,
                                        @RequestBody UpdateArtistDto updateArtistDto) {
        return Mono.zip(Mono.just(artistId), Mono.just(updateArtistDto))
                .map(tuple -> artistService.updateArtist(
                        Integer.parseInt(tuple.getT1()),
                        tuple.getT2().getNewName()))
                .map(artistMapper::toDto);
    }

    @GetMapping("/artist-of-the-three-minutes")
    public Mono<ArtistDto> getArtistOfTheThreeMinutes() {
        return Mono.just(artistOfTheMomentService.getArtistOfTheMoment())
                .map(artistMapper::toDto)
                .map(this::populateArtistDto);
    }

    /**********************************************************************************************/

    private ArtistDto populateArtistDto(ArtistDto artistDto) {
        var tracks = trackService.getTracksByArtist(artistDto.getId())
                .stream()
                .map(trackMapper::toDto)
                .toList();
        var aliases = artistAliasService.getArtistAliases(artistDto.getId())
                .stream()
                .map(aliasMapper::toDto)
                .toList();
        return new ArtistDto(artistDto.getId(), artistDto.getName(), tracks, aliases);
    }
}
