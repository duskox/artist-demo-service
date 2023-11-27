package com.icechallenge.artistdemoservice.api.v1;

import com.icechallenge.artistdemoservice.api.v1.dtos.NewTrackDto;
import com.icechallenge.artistdemoservice.api.v1.dtos.TrackDto;
import com.icechallenge.artistdemoservice.domain.service.TrackService;
import com.icechallenge.artistdemoservice.util.TrackMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tracks")
public class TracksController {

    private final TrackService trackService;
    private final TrackMapper trackMapper;

    public TracksController(TrackService trackService,
                            TrackMapper trackMapper) {
        this.trackService = trackService;
        this.trackMapper = trackMapper;
    }

    @GetMapping("/{artistId}")
    public Mono<List<TrackDto>> getTracks(@PathVariable String artistId) {
        return Mono.just(Integer.parseInt(artistId))
                .map(trackService::getTracksByArtist)
                .flatMapMany(Flux::fromIterable)
                .map(trackMapper::toDto)
                .collectList();
    }

    @PostMapping
    public Mono<TrackDto> createTrack(@RequestBody NewTrackDto newTrackDto) {
        return Mono.just(newTrackDto)
                .map(dto -> {
                    return trackService.createTrack(dto.getArtistId(),
                            dto.getName(),
                            dto.getGenre(),
                            dto.getLengthInSeconds());
                })
                .map(trackMapper::toDto);
    }
}
