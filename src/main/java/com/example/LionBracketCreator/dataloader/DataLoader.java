package com.example.LionBracketCreator.dataloader;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.repositories.BracketRepository;
import com.example.LionBracketCreator.repositories.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TeamRepository teamRepository;

    private final BracketRepository bracketRepository;

    public DataLoader(TeamRepository teamRepository, BracketRepository bracketRepository) {
        this.teamRepository = teamRepository;
        this.bracketRepository = bracketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        TeamEntity team1 = new TeamEntity("NRG");
        TeamEntity team2 = new TeamEntity("G2");
        TeamEntity team3 = new TeamEntity("C9");
        TeamEntity team4 = new TeamEntity("SSG");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);

        BracketEntity bracketEntity = new BracketEntity("north_america_1", "North America Regional 1");
        bracketEntity.addTeam(team1, 1);
        bracketEntity.addTeam(team2, 2);
        bracketEntity.addTeam(team3, 3);
        bracketEntity.addTeam(team4, 4);

        bracketRepository.save(bracketEntity);
        System.out.println("Data inserted");
    }
}
