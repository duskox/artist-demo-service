package com.icechallenge.artistdemoservice.util;

import com.icechallenge.artistdemoservice.api.v1.dtos.TrackDto;
import com.icechallenge.artistdemoservice.domain.model.Track;
import com.icechallenge.artistdemoservice.persist.dbos.TrackDbo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {

    @Mapping(target = "length",
            expression = "java(java.time.Duration.ofSeconds(trackDbo.getLengthInSeconds()))")
    Track toDomain(TrackDbo trackDbo);

    @Mapping(target = "lengthInSeconds",   expression = "java(Integer.valueOf(Math.toIntExact(track.getLength().getSeconds())))")
    TrackDbo toDbo(Track track);

    @Mapping(target = "lengthInSeconds", expression = "java(Integer.valueOf(Math.toIntExact(track.getLength().getSeconds())))")
    TrackDto toDto(Track track);

    List<Track> toDomain(List<TrackDbo> dboList);
}
