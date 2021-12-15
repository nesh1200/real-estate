package com.example.quatro_to.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "estate_features")
@Builder
public class EstateFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3,max = 30,message = "Length should be between 3 and 30")
    @NotNull
    @Column(nullable = false)
    private String feature;

    //@ManyToMany(mappedBy = "estateFeatures")

}
