package org.undp.incompetent.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.undp.incompetent.models.IncompetentCaseEntity;
import org.undp.incompetent.models.IncompetentEntity;

@Repository
@Transactional
public interface IncompetentCaseRepository extends CrudRepository<IncompetentCaseEntity,Long> {
    public IncompetentCaseEntity findByIncompetentCaseId(Long id);
}
