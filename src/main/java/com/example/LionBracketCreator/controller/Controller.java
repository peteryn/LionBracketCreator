package com.example.LionBracketCreator.controller;

import com.example.LionBracketCreator.domain.BracketDTO;
import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamDTO;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.mappers.Mapper;
import com.example.LionBracketCreator.mappers.impl.BracketMapper;
import com.example.LionBracketCreator.mappers.impl.TeamMapper;
import com.example.LionBracketCreator.services.BracketService;
import com.example.LionBracketCreator.services.TeamService;
import com.example.LionBracketCreator.services.UserService;
import com.example.LionBracketCreator.util.AuthenticationUtility;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    private final TeamService teamService;

    private final Mapper<TeamEntity, TeamDTO> teamMapper;

    private final Mapper<BracketEntity, BracketDTO> bracketMapper;

    private final BracketService bracketService;

    public Controller(TeamService teamService, Mapper<TeamEntity, TeamDTO> teamMapper, BracketMapper bracketMapper, BracketService bracketService) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
        this.bracketMapper = bracketMapper;
        this.bracketService = bracketService;
    }

    @GetMapping("/login/oauth2")
    public String login() {
        return "login";
    }

    @GetMapping("/userinfo")
    @ResponseBody
    public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User oauth2User) {
        return oauth2User.getAttributes();
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal OAuth2User oAuth2User, OAuth2AuthenticationToken authentication) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String provider = authentication.getAuthorizedClientRegistrationId();
        System.out.println(provider);
        System.out.println(authentication.getName());
        model.addAttribute("name", attributes.get("name"));
        return "dashboard";
    }

    @GetMapping("/create_team")
    public String createTeamForm(Model model) {
        model.addAttribute("teamDTO", new TeamDTO());
        return "createTeam";
    }

    @PostMapping("/create_team")
    public String createTeamSubmit(@ModelAttribute TeamDTO teamDTO, Model model) {
        TeamEntity teamEntity = teamMapper.mapFrom(teamDTO);
        this.teamService.createTeam(teamEntity);
        return "result";
    }

    @GetMapping("/create_bracket")
    public String createBracketForm(Model model) {
        model.addAttribute("bracketDTO", new BracketDTO());
        return "createBracket";
    }

    @PostMapping("/create_bracket")
    public String createBracketSubmit(@ModelAttribute BracketDTO bracketDTO, Model model) {
        BracketEntity bracketEntity = bracketMapper.mapFrom(bracketDTO);
        this.bracketService.createBracket(bracketEntity);
        return "result";
    }
}
