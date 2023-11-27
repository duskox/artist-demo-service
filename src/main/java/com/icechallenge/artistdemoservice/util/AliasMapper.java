package com.icechallenge.artistdemoservice.util;

import com.icechallenge.artistdemoservice.api.v1.dtos.ArtistAliasDto;
import com.icechallenge.artistdemoservice.domain.model.ArtistAlias;
import com.icechallenge.artistdemoservice.persist.dbos.ArtistAliasDbo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AliasMapper {

    ArtistAlias toDomain(ArtistAliasDbo dbo);

    ArtistAlias toDomain(ArtistAliasDto dto);

    ArtistAliasDbo toDbo(ArtistAlias domain);

    ArtistAliasDto toDto(ArtistAlias domain);

    List<ArtistAlias> toDomain(List<ArtistAliasDbo> dboList);
}
