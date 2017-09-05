package com.prem.studyproject.repository.interfaces;

import com.prem.studyproject.domain.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Melnyk_Dmytro
 * @version 1.0
 * @since 05.09.2017
 */
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> getAllByUserId(BigInteger userId);

    long countAllByUserId(BigInteger userId);

    Note findByUserIdAndId(BigInteger userId, BigInteger id);

    Note findById(BigInteger id);
}
