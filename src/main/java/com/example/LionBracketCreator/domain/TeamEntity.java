package com.example.LionBracketCreator.domain;

import com.example.LionBracketCreator.domain.BracketTeams.BracketTeams;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="teams")
public class TeamEntity {

    @Id
    private String name;

    private String abbreviatedName;

    private String imagePath;

    private String accentColor;

    private String players;

    // this one-to-many mappedBy must exist as a field in BracketTeams
    @OneToMany(mappedBy = "team")
    private Set<BracketTeams> brackets;
}
