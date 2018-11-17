package com.secure.peaas.repository;

import com.secure.peaas.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by grahul on 17-11-2018.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
