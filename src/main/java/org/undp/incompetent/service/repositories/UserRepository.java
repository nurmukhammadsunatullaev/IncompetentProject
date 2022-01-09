package org.undp.incompetent.service.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.undp.incompetent.models.UserEntity;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
}
