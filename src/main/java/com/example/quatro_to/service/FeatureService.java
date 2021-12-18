package com.example.quatro_to.service;
import com.example.quatro_to.model.Feature;

import java.util.Set;

public interface FeatureService {

    Feature save(Feature feature);

    Feature findById(Long id);

    Feature findByName(String name);

    Feature update(Feature feature, Long id);

    void delete(Long id);

    Set<Feature> findAll();

}
