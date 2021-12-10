package com.example.quatro_to.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "buildings")
@Builder
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = false,nullable = true)
    private String name;

    @NotNull
    @Column(unique = false,nullable = false)
    private BuildingType buildingType ;

    @NotNull
    @Column(name = "yard", nullable = false)
    private boolean has_yard;

}
