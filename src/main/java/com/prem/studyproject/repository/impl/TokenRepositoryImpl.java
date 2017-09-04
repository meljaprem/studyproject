package com.prem.studyproject.repository.impl;

import com.prem.studyproject.domain.model.MailConfirmationToken;
import com.prem.studyproject.repository.interfaces.custom.TokenRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class TokenRepositoryImpl implements TokenRepositoryCustom {

    private MongoOperations template;

    @Autowired
    public TokenRepositoryImpl(MongoOperations template) {
        this.template = template;
    }

    @Override
    public BigInteger updateTokenValue(MailConfirmationToken token) {
//        Query query = Query.query();
//        Update update = Update.update("value", token.getValue());
//        WriteResult result =  template.updateFirst(query, update, MailConfirmationToken.class);
//        result.
        return null;
    }
}
