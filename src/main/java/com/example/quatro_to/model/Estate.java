package com.example.quatro_to.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column
    @ManyToOne
    @JoinColumn(
            name = "floor_id", foreignKey = @ForeignKey(name = "fk_floor_id")
    )
    private Floor floor;

    @ManyToMany
    @JoinTable(
            name = "estate_",
            joinColumns = @JoinColumn(name = "estate_id", referencedColumnName ="id" ),
            inverseJoinColumns = @JoinColumn(name = "estate_feature_id",referencedColumnName = "id")
    )
    private Set<EstateFeature> estateFeatures;

    @Column
    private String description;

}
