package com.prem.studyproject.repository.interfaces;

import com.prem.studyproject.domain.model.MailConfirmationToken;
import com.prem.studyproject.repository.interfaces.custom.TokenRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface TokenRepository extends MongoRepository<MailConfirmationToken, String>, TokenRepositoryCustom {

    MailConfirmationToken findByUserId(BigInteger userId);

    MailConfirmationToken findByValue(String  value);

}
