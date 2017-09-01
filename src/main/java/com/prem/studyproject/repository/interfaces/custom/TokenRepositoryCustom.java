package com.prem.studyproject.repository.interfaces.custom;

import com.prem.studyproject.domain.model.EmailConfirmationToken;

import java.math.BigInteger;

public interface TokenRepositoryCustom {
    BigInteger updateTokenValue(EmailConfirmationToken token);
}
