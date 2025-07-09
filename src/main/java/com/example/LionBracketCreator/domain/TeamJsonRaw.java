package com.example.LionBracketCreator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamJsonRaw { // Renamed from TeamRawJsonData for clarity
    private String name;
    private String path;
    private List<String> players; // Jackson will parse this as List<String> by default
    private String abbreviatedName;
    private String color;
}