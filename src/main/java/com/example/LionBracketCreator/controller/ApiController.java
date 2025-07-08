package com.example.LionBracketCreator.controller;

import com.example.LionBracketCreator.domain.BracketDTO;
import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamDTO;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.mappers.Mapper;
import com.example.LionBracketCreator.mappers.impl.TeamMapper;
import com.example.LionBracketCreator.services.BracketService;
import com.example.LionBracketCreator.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final Mapper<BracketEntity, BracketDTO> bracketMapper;

    private final Mapper<TeamEntity, TeamDTO> teamMapper;

    private final BracketService bracketService;

    private final TeamService teamService;

    public ApiController(Mapper<BracketEntity, BracketDTO> bracketMapper, BracketService bracketService, TeamService teamService, TeamMapper teamMapper) {
        this.bracketMapper = bracketMapper;
        this.bracketService = bracketService;
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/test")
    public String test() {
        return "This is a test";
    }

    @GetMapping("/tournament_data")
    public HashMap<String, String> getTournamentData(@RequestParam(name="tournament_id") String tournamentId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tournament_id_string", tournamentId);
        return map;
    }

    @GetMapping("/brackets/{bracket_id}")
    public ResponseEntity<BracketDTO> getBracket(@PathVariable("bracket_id") String bracket_id) {
        Optional<BracketEntity> foundBracket = bracketService.findOne(bracket_id);
        return foundBracket.map(bracketEntity -> {
            BracketDTO bracketDTO = bracketMapper.mapTo(bracketEntity);
            return new ResponseEntity<>(bracketDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/teams/{user_id}/{team_name}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable("team_name") String teamName, @PathVariable("user_id") UUID uuid) {
        Optional<TeamEntity> foundTeam = teamService.findOne(uuid, teamName);
        return foundTeam.map(teamEntity -> {
            TeamDTO teamDTO = teamMapper.mapTo(teamEntity);
            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/teams/{user_id}")
    public ResponseEntity<List<TeamDTO>> getMyTeams(@PathVariable("user_id") UUID uuid) {
        List<TeamEntity> foundTeams = teamService.findAll(uuid);
        List<TeamDTO> teamDTOs = foundTeams.stream().map(teamMapper::mapTo).toList();
        return new ResponseEntity<>(teamDTOs, HttpStatus.OK);
    }
}
