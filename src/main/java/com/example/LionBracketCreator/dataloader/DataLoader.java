package com.example.LionBracketCreator.dataloader;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.domain.UserEntity;
import com.example.LionBracketCreator.repositories.BracketRepository;
import com.example.LionBracketCreator.repositories.TeamRepository;
import com.example.LionBracketCreator.repositories.UserRepository;
import com.example.LionBracketCreator.services.BracketService;
import com.example.LionBracketCreator.services.TeamService;
import com.example.LionBracketCreator.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${SUPER_USER_UUID}")
    private UUID superUserUUID;

    private final TeamService teamService;

    private final BracketService bracketService;

    private final UserService userService;

    private final TeamRepository teamRepository;

    private final BracketRepository bracketRepository;

    private final UserRepository userRepository;

    public DataLoader(TeamService teamService, BracketService bracketService, UserService userService,
                      TeamRepository teamRepository, BracketRepository bracketRepository, UserRepository userRepository) {
        this.teamService = teamService;
        this.bracketService = bracketService;
        this.userService = userService;
        this.teamRepository = teamRepository;
        this.bracketRepository = bracketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userService.userExistsById(this.superUserUUID)) {
            return;
        }

        UserEntity userEntity = new UserEntity("SuperUser", "SuperProvider", "SuperProviderId");
        userEntity.setId(this.superUserUUID);
        userRepository.save(userEntity);

        TeamEntity team1 = new TeamEntity("NRG", userEntity);
        TeamEntity team2 = new TeamEntity("G2", userEntity);
        TeamEntity team3 = new TeamEntity("C9", userEntity);
        TeamEntity team4 = new TeamEntity("SSG", userEntity);
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);

        BracketEntity bracketEntity = new BracketEntity("north_america_1", "North America Regional 1");
        bracketEntity.addTeam(team1, 1);
        bracketEntity.addTeam(team2, 2);
        bracketEntity.addTeam(team3, 3);
        bracketEntity.addTeam(team4, 4);

        userEntity.addBracket(bracketEntity);
        userEntity.addTeam(team1);
        bracketRepository.save(bracketEntity);

        System.out.println("Data inserted");
    }
}
