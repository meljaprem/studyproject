package com.prem.studyproject.repository.interfaces;

import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.repository.interfaces.custom.UserRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;


public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

    User findFirstById(BigInteger id);

    User findFirstByUsername(String username);


}
