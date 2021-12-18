package com.example.quatro_to.service;
import com.example.quatro_to.model.Estate;

import java.util.Set;

public interface EstateService {

    Estate save(Estate city);

    Estate findById(Long id);

    Estate update(Estate estate, Long id);

    void delete(Long id);

    Set<Estate> findAll();
}
