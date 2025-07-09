package com.example.LionBracketCreator.dataloader;

import com.example.LionBracketCreator.domain.*;
import com.example.LionBracketCreator.mappers.Mapper;
import com.example.LionBracketCreator.mappers.impl.TeamMapper;
import com.example.LionBracketCreator.repositories.BracketRepository;
import com.example.LionBracketCreator.repositories.TeamRepository;
import com.example.LionBracketCreator.repositories.UserRepository;
import com.example.LionBracketCreator.services.BracketService;
import com.example.LionBracketCreator.services.TeamService;
import com.example.LionBracketCreator.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${SUPER_USER_UUID}")
    private UUID superUserUUID;

    private UserEntity superUser;

    private final TeamService teamService;

    private final BracketService bracketService;

    private final UserService userService;

    private final TeamRepository teamRepository;

    private final BracketRepository bracketRepository;

    private final UserRepository userRepository;

    private final Mapper<TeamEntity, TeamDTO> teamMapper;

    public DataLoader(TeamService teamService, BracketService bracketService, UserService userService,
                      TeamRepository teamRepository, BracketRepository bracketRepository, UserRepository userRepository,
                        TeamMapper teamMapper) {
        this.teamService = teamService;
        this.bracketService = bracketService;
        this.userService = userService;
        this.teamRepository = teamRepository;
        this.bracketRepository = bracketRepository;
        this.userRepository = userRepository;
        this.teamMapper = teamMapper;
    }

    @PostConstruct
    public void superUserLoader() {
        var superUserOptional = this.userService.getUserById(superUserUUID);

        if (superUserOptional.isEmpty()) {
            UserEntity userEntity = new UserEntity("SuperUser", "SuperProvider", "SuperProviderId");
            userEntity.setId(this.superUserUUID);
            userRepository.save(userEntity);
            this.superUser = userEntity;
        } else {
            this.superUser = superUserOptional.get();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    // AI generated code to work with jackson
    public void loadData() {
        String jsonFilePath = "src/main/resources/teams.json";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, TeamJsonRaw> rawTeamsDataMap = objectMapper.readValue(
                new File(jsonFilePath),
                new TypeReference<Map<String, TeamJsonRaw>>() {}
            );

            Map<String, TeamDTO> finalTeamDTOMap = new java.util.HashMap<>();

            rawTeamsDataMap.forEach((teamJsonKey, rawData) -> {
                TeamDTO teamDTO = TeamDTO.builder()
                    .teamId(teamJsonKey)
                    .displayName(rawData.getName())
                    .abbreviatedName(rawData.getAbbreviatedName())
                    .accentColor(rawData.getColor())
                    .players(objectMapper.convertValue(rawData.getPlayers(), new TypeReference<Set<PlayerEntity>>() {}))
                    .ownerId(superUserUUID)
                    .imagePath(rawData.getPath())
                    .build();

                finalTeamDTOMap.put(teamJsonKey, teamDTO);
            });

            ArrayList<TeamDTO> teamDTOList = new ArrayList<>(finalTeamDTOMap.values());

            teamDTOList.forEach(teamDTO -> {
                var teamEntity = this.teamMapper.mapFrom(teamDTO);
                teamEntity.setUserEntity(this.superUser);
                this.teamRepository.save(teamEntity);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
