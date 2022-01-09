package org.undp.incompetent.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.undp.incompetent.models.CourtEntity;

import java.util.List;

@Repository
@Transactional
public interface CourtRepository extends CrudRepository<CourtEntity,Integer> {
    List<CourtEntity> findByTypeOrderByType(Integer value);
}