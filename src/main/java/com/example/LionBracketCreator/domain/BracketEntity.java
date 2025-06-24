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
@Table(name="brackets")
public class BracketEntity {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "bracket")
    private Set<BracketTeams> teams;
}
