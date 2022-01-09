package org.undp.incompetent.service.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.undp.incompetent.models.CourtEntity;
import org.undp.incompetent.models.IncompetentEntity;
import java.util.List;

@Repository
@Transactional
public interface IncompetentRepository extends JpaRepository<IncompetentEntity,Long> {
     public IncompetentEntity findByIncompetentId(Long id);
     public Page<IncompetentEntity> findByCourtEntity(CourtEntity courtEntity, Pageable pageable);
     public List<IncompetentEntity> findByIncompetentPassport(String passport);
     public List<IncompetentEntity> findByIncompetentFirstnameAndIncompetentSurnameAndIncompetentPatronymic(String firstname,String surname,String patronymic);

}