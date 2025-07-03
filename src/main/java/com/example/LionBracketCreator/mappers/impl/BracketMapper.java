//package com.example.LionBracketCreator.mappers.impl;
//
//import com.example.LionBracketCreator.domain.BracketEntity;
//import com.example.LionBracketCreator.domain.BracketDTO;
//import com.example.LionBracketCreator.mappers.Mapper;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BracketMapper implements Mapper<BracketEntity, BracketDTO> {
//
//    private final ModelMapper modelMapper;
//
//    private final BracketTeamsMapperImpl bracketTeamsMapper;
//
//    public BracketMapper(ModelMapper modelMapper, BracketTeamsMapperImpl bracketTeamsMapper) {
//        this.modelMapper = modelMapper;
//        this.bracketTeamsMapper = bracketTeamsMapper;
//    }
//
//    @Override
//    public BracketDTO mapTo(BracketEntity bracketEntity) {
//        BracketDTO bracketDTO = new BracketDTO();
//        bracketDTO.setId(bracketEntity.getId());
//        bracketDTO.setName(bracketEntity.getName());
//        var bracketTeamsDTOS = bracketEntity.getTeams()
//                .stream()
//                .sorted()
//                .map(this.bracketTeamsMapper::mapTo)
//                .toList();
//        bracketDTO.setTeams(bracketTeamsDTOS);
//        return bracketDTO;
//    }
//
//    @Override
//    public BracketEntity mapFrom(BracketDTO bracketDTO) {
//        return modelMapper.map(bracketDTO, BracketEntity.class);
//    }
//}
