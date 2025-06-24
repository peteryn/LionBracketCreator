package com.example.LionBracketCreator.domain;

import com.example.LionBracketCreator.domain.BracketTeams.BracketTeams;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="teams")
@ToString(exclude = "brackets")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TeamEntity {

    public TeamEntity(String name) {
        this.name = name;
        this.brackets = new HashSet<>();
    }

    @Id
    @EqualsAndHashCode.Include
    private String name;

    private String abbreviatedName;

    private String imagePath;

    private String accentColor;

    private String players;

    // this one-to-many mappedBy must exist as a field in BracketTeams
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<BracketTeams> brackets;
}
