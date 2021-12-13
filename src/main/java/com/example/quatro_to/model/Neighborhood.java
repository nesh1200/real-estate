package com.example.quatro_to.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "neighborhoods")
@Builder
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = true,unique = true)
    private String name;


    @ManyToMany(mappedBy = "neighborhoods")
    private Set<City> cities;

    @OneToMany(mappedBy = "neighborhood")
    private List<Building> buildings;
}
