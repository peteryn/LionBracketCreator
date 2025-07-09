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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
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
        System.out.println("superUserLoader started running");
        var superUserOptional = this.userService.getUserById(superUserUUID);

        if (superUserOptional.isEmpty()) {
            UserEntity userEntity = new UserEntity("SuperUser", "SuperProvider", "SuperProviderId");
            userEntity.setId(this.superUserUUID);
            userRepository.save(userEntity);
            this.superUser = userEntity;
        } else {
            this.superUser = superUserOptional.get();
        }
        System.out.println("superUserLoader finished running");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run function started running");
        readCustomData();

//        PlayerEntity p1 = new PlayerEntity("Beastmode");
//        PlayerEntity p2 = new PlayerEntity("Atomic");
//        PlayerEntity p3 = new PlayerEntity("Daniel");
//
//        TeamEntity team1 = new TeamEntity("NRG", this.superUser);
//        team1.addPlayer(p1);
//        team1.addPlayer(p2);
//        team1.addPlayer(p3);
//
//        TeamEntity team2 = new TeamEntity("G2", this.superUser);
//        TeamEntity team3 = new TeamEntity("C9", this.superUser);
//        TeamEntity team4 = new TeamEntity("SSG", this.superUser);
//        teamRepository.save(team1);
//        teamRepository.save(team2);
//        teamRepository.save(team3);
//        teamRepository.save(team4);
//
//        BracketEntity bracketEntity = new BracketEntity("north_america_1", "North America Regional 1");
//        bracketEntity.addTeam(team1, 1);
//        bracketEntity.addTeam(team2, 2);
//        bracketEntity.addTeam(team3, 3);
//        bracketEntity.addTeam(team4, 4);
//
//        this.superUser.addBracket(bracketEntity);
//        this.superUser.addTeam(team1);
//        bracketRepository.save(bracketEntity);
//
        System.out.println("Data inserted");
    }

    public void readCustomData() {
        String jsonFilePath = "src/main/resources/teams.json";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Step 1: Deserialize the entire JSON file into a Map of String keys to TeamJsonRaw objects
            // This is because the top-level keys are dynamic team names.
            Map<String, TeamJsonRaw> rawTeamsDataMap = objectMapper.readValue(
                new File(jsonFilePath),
                new TypeReference<Map<String, TeamJsonRaw>>() {}
            );

            // Step 2: Manually map each TeamJsonRaw object to a TeamDTO,
            // performing your custom field transformations.
            Map<String, TeamDTO> finalTeamDTOMap = new java.util.HashMap<>();

            rawTeamsDataMap.forEach((teamJsonKey, rawData) -> {
                TeamDTO teamDTO = TeamDTO.builder()
                    .teamId(teamJsonKey) // Manual mapping: 'path' -> 'teamId'
                    .displayName(rawData.getName()) // Manual mapping: 'name' -> 'displayName'
                    .abbreviatedName(rawData.getAbbreviatedName()) // Direct
                    .accentColor(rawData.getColor()) // Manual mapping: 'color' -> 'accentColor'
                    // For 'players', the @JsonDeserialize on TeamDTO's 'players' field
                    // will automatically trigger PlayerSetDeserializer when we attempt
                    // to set the players. We just pass the raw List<String> here.
                    // THIS IS THE CRUCIAL PART: Jackson's deserialization pipeline will handle it.
                    .players(objectMapper.convertValue(rawData.getPlayers(), new TypeReference<Set<PlayerEntity>>() {}))
                    // Set default/generated values for fields not in JSON:
                    .ownerId(superUserUUID)
                    .imagePath(rawData.getPath()) // Example: Set a default image path
                    .build();

                finalTeamDTOMap.put(teamJsonKey, teamDTO);
            });

            ArrayList<TeamDTO> teamDTOList = new ArrayList<>(finalTeamDTOMap.values());

            teamDTOList.forEach(teamDTO -> {
                var teamEntity = this.teamMapper.mapFrom(teamDTO);
                teamEntity.setUserEntity(this.superUser);
                this.teamRepository.save(teamEntity);
            });


            System.out.println("Parsed Team DTOs:");
            finalTeamDTOMap.forEach((key, dto) -> {
                System.out.println("--- Team: " + key + " ---");
                System.out.println("Team ID: " + dto.getTeamId());
                System.out.println("Display Name: " + dto.getDisplayName());
                System.out.println("Abbreviated Name: " + dto.getAbbreviatedName());
                System.out.println("Accent Color: " + dto.getAccentColor());
                System.out.println("Owner ID: " + dto.getOwnerId());
                System.out.println("Image Path: " + dto.getImagePath());
                System.out.println("Players:");
                if (dto.getPlayers() != null) {
                    dto.getPlayers().forEach(player -> System.out.println("  - " + player.getName()));
                }
                System.out.println();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
