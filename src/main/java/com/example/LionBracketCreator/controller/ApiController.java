package com.example.LionBracketCreator.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

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
}
