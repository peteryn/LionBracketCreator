package com.example.LionBracketCreator;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.BracketTeams.BracketTeams;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.repositories.BracketRepository;
import com.example.LionBracketCreator.repositories.TeamRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CreateBracketAndTeamsTests {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private BracketRepository bracketRepository;

    @Test
    @Transactional
    public void test1() {
        TeamEntity team1 = new TeamEntity();
        team1.setName("NRG");

        teamRepository.save(team1);

        BracketEntity bracket = BracketEntity.builder()
                .id("0")
                .name("Regional 0")
                .teams(Set.of())
                .build();

        BracketTeams bt1 = new BracketTeams(bracket, team1, 1);
        bracket.setTeams(Set.of(bt1));

        bracketRepository.save(bracket);

        var result = bracketRepository.findById("0");
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(bracket);
        if (!result.isEmpty()) {
            var a = result.get();
            var bracketTeamsSet = a.getTeams();
            System.out.println("PRINTING RESULTS");
            bracketTeamsSet.iterator().forEachRemaining(item -> System.out.println(item.getId()));
        }
        System.out.println("TEST 1 COMPLETE");
    }
}
