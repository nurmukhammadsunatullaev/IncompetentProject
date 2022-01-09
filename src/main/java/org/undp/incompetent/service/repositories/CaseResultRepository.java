package org.undp.incompetent.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.undp.incompetent.models.CaseResultEntity;

@Repository
@Transactional
public interface CaseResultRepository extends CrudRepository<CaseResultEntity,Integer> {
}
