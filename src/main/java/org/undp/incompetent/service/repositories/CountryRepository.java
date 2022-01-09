package org.undp.incompetent.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.undp.incompetent.models.CountryEntity;

import java.util.List;

public interface CountryRepository extends CrudRepository<CountryEntity,Integer> {
    List<CountryEntity> findByValue(Integer value);
}
