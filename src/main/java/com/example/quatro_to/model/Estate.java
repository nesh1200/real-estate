package com.example.quatro_to.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "estates")
@Builder
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "floor_id",
            foreignKey = @ForeignKey(name = "fk_floor_id")
    )
    private Floor floor;

    @ManyToMany
    @JoinTable(
            name = "estate_features",
            joinColumns = @JoinColumn(name = "estate_id", referencedColumnName ="id" ),
            inverseJoinColumns = @JoinColumn(name = "feature_id",referencedColumnName = "id")
    )
    private Set<Feature> features;

    @Column
    private String description;

}
