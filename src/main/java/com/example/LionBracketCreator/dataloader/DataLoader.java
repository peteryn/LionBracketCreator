package com.example.LionBracketCreator.dataloader;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.domain.UserEntity;
import com.example.LionBracketCreator.repositories.BracketRepository;
import com.example.LionBracketCreator.repositories.TeamRepository;
import com.example.LionBracketCreator.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final TeamRepository teamRepository;

    private final BracketRepository bracketRepository;

    private final UserRepository userRepository;

    public DataLoader(TeamRepository teamRepository, BracketRepository bracketRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.bracketRepository = bracketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setId("test_id");
        userEntity.setBrackets(new HashSet<>());
        userEntity.setTeams(new HashSet<>());

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
