package com.example.LionBracketCreator.mappers.impl;

import com.example.LionBracketCreator.domain.PlayerDTO;
import com.example.LionBracketCreator.domain.PlayerEntity;
import com.example.LionBracketCreator.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class PlayerMapper implements Mapper<PlayerEntity, PlayerDTO> {

    private final ModelMapper modelMapper;

    public PlayerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerDTO mapTo(PlayerEntity playerEntity) {
        return this.modelMapper.map(playerEntity, PlayerDTO.class);
    }

    @Override
    public PlayerEntity mapFrom(PlayerDTO playerDTO) {
        return this.modelMapper.map(playerDTO, PlayerEntity.class);
    }
}
