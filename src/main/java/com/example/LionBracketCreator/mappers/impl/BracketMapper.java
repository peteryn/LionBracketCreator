package com.example.LionBracketCreator.mappers.impl;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.BracketDTO;
import com.example.LionBracketCreator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BracketMapper implements Mapper<BracketEntity, BracketDTO> {

    private final ModelMapper modelMapper;

    public BracketMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BracketDTO mapTo(BracketEntity bracketEntity) {
        return modelMapper.map(bracketEntity, BracketDTO.class);
    }

    @Override
    public BracketEntity mapFrom(BracketDTO bracketDTO) {
        return modelMapper.map(bracketDTO, BracketEntity.class);
    }
}
