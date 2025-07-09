package com.example.LionBracketCreator.domain;

import com.example.LionBracketCreator.domain.BracketTeams.BracketTeams;
import com.example.LionBracketCreator.util.Utility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamEntity {

    public TeamEntity(String displayName, UserEntity userEntity) {
        this.userEntity = userEntity;
        String name = Utility.removeNonAlphabeticUsingStreams(displayName).toLowerCase();
        this.id = new UserTeamKey(this.userEntity.getId(), name);
        this.brackets = new HashSet<>();
        this.displayName = displayName;
        this.players = new HashSet<>();
    }

    @Id
    @EmbeddedId
    @EqualsAndHashCode.Include
    private UserTeamKey id;

    private String displayName;

    private String abbreviatedName;

    private String imagePath;

    private String accentColor;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PlayerEntity> players;

    public void addPlayer(PlayerEntity playerEntity) {
        this.players.add(playerEntity);
    }

    // this one-to-many mappedBy must exist as a field in BracketTeams
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<BracketTeams> brackets;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
