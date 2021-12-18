package com.example.quatro_to.service.impl;

import com.example.quatro_to.exception.ResourceNotFoundException;
import com.example.quatro_to.model.Feature;
import com.example.quatro_to.repository.FeatureRepository;
import com.example.quatro_to.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Autowired
    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public Feature save(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public Feature findById(Long id) {
        return featureRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("There is no feature with this id: %d",id)));
    }

    @Override
    public Feature findByName(String name) {
        return featureRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException(String.format("There is no feature with nam %s",name)));
    }


    @Override
    public Feature update(Feature feature, Long id) {
        Feature foundFeature = findById(id);
        Feature toUpdateFeature = Feature.builder().id(foundFeature.getId()).name(feature.getName()).build();
        return featureRepository.save(toUpdateFeature);
    }

    @Override
    public void delete(Long id) {
        Feature foundFeature = findById(id);
        featureRepository.delete(foundFeature);
    }

    @Override
    public Set<Feature> findAll() {
        return new HashSet<>(featureRepository.findAll());
    }
}
