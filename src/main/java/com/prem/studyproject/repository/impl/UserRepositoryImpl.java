package com.prem.studyproject.repository.impl;

import com.prem.studyproject.repository.interfaces.custom.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private  MongoOperations operations;

    @Autowired
    public UserRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }
}
