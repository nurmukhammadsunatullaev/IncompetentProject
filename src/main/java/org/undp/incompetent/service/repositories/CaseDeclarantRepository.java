package org.undp.incompetent.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.undp.incompetent.models.CaseDeclarantEntity;

@Repository
@Transactional
public interface CaseDeclarantRepository extends CrudRepository<CaseDeclarantEntity,Integer> {
}
