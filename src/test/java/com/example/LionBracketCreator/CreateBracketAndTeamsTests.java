package com.example.LionBracketCreator;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.repositories.BracketRepository;
import com.example.LionBracketCreator.repositories.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CreateBracketAndTeamsTests {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private BracketRepository bracketRepository;

    @Test
    public void test1() {
        BracketEntity bracket = new BracketEntity();
        bracket.setId("0");
        bracket.setName("Regional 0");

        bracketRepository.save(bracket);

        TeamEntity team1 = new TeamEntity();
        team1.setName("G2 Esports");

        teamRepository.save(team1);
    }
}
