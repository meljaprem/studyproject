package com.prem.studyproject.repository.interfaces;

import com.prem.studyproject.domain.model.EmailConfirmationToken;
import com.prem.studyproject.repository.interfaces.custom.TokenRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface TokenRepository extends MongoRepository<EmailConfirmationToken, String>, TokenRepositoryCustom {

    EmailConfirmationToken findByUserId(BigInteger userId);

    EmailConfirmationToken findByValue(String  value);

}
