package com.example.LionBracketCreator.mappers.impl;

import com.example.LionBracketCreator.domain.TeamDTO;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.domain.UserTeamKey;
import com.example.LionBracketCreator.mappers.Mapper;
import com.example.LionBracketCreator.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements Mapper<TeamEntity, TeamDTO> {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public TeamMapper(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public TeamDTO mapTo(TeamEntity teamEntity) {
        return TeamDTO.builder()
                .teamId(teamEntity.getId().getTeamId())
                .ownerId(teamEntity.getId().getUserId())
                .displayName(teamEntity.getDisplayName())
                .abbreviatedName(teamEntity.getAbbreviatedName())
                .imagePath(teamEntity.getImagePath())
                .accentColor(teamEntity.getAccentColor())
                .players(teamEntity.getPlayers())
                .build();
    }

    @Override
    public TeamEntity mapFrom(TeamDTO teamDTO) {
        var teamEntity = modelMapper.map(teamDTO, TeamEntity.class);
        teamEntity.setId(new UserTeamKey(teamDTO.getOwnerId(), teamDTO.getTeamId()));
        var userEntityOptional = userRepository.findById(teamDTO.getOwnerId());
        return userEntityOptional.map(userEntity -> {
            teamEntity.setUserEntity(userEntity);
            return teamEntity;
        }).orElse(null);
    }
}
