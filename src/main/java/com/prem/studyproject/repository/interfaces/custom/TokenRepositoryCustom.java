package com.prem.studyproject.repository.interfaces.custom;

import com.prem.studyproject.domain.model.MailConfirmationToken;

import java.math.BigInteger;

public interface TokenRepositoryCustom {
    BigInteger updateTokenValue(MailConfirmationToken token);
}
