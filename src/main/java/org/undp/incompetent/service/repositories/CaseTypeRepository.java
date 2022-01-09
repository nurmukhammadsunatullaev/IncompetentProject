package org.undp.incompetent.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.undp.incompetent.models.CaseTypeEntity;

import java.util.Optional;

@Repository
@Transactional
public interface CaseTypeRepository  extends CrudRepository<CaseTypeEntity,Integer> {
    Optional<CaseTypeEntity> findById(Integer id);
}
