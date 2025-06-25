package com.example.LionBracketCreator.controller;

import com.example.LionBracketCreator.domain.BracketDTO;
import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.mappers.Mapper;
import com.example.LionBracketCreator.repositories.BracketRepository;
import com.example.LionBracketCreator.services.BracketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private Mapper<BracketEntity, BracketDTO> bracketMapper;

    private BracketService bracketService;

    public ApiController(Mapper<BracketEntity, BracketDTO> bracketMapper, BracketService bracketService) {
        this.bracketMapper = bracketMapper;
        this.bracketService = bracketService;
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

//    @GetMapping("/teams/{team_name}")
//    public TeamDTO getTeam(@PathVariable String team_name) {
//
//    }
}
