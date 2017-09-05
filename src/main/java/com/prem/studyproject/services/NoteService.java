package com.prem.studyproject.services;

import com.prem.studyproject.domain.enums.Role;
import com.prem.studyproject.domain.model.Note;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.repository.interfaces.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Melnyk_Dmytro
 * @version 1.0
 * @since 05.09.2017
 */
@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note, User user) {
        note.setUserId(user.getId());
        note.setUserId(user.getId());
        note.setCreationDate(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public Note updateNote(User user, Note note) {
        Note noteFromDB = noteRepository.findByUserIdAndId(user.getId(), note.getId());
        if(noteFromDB!=null) {
            if(!noteFromDB.equals(note)){
                return noteRepository.save(note);
            } else {
                return note;
            }
        } else {
            return null;
        }
    }

    public boolean deleteNote(User user, BigInteger id) {
        if (user.getAuthorities().contains(Role.ADMIN)) {
            noteRepository.delete(id.toString());
            return true;
        } else {
            Note note = getById(user, id);
            if (note != null) {
                noteRepository.delete(note);
                return true;
            } else {
                return false;
            }
        }
    }

    public Long getCountByUserId(User user, BigInteger id) {
        if (user.getAuthorities().contains(Role.ADMIN)) {
            return noteRepository.countAllByUserId(id);
        } else {
            return noteRepository.countAllByUserId(user.getId());
        }

    }

    public List<Note> getAllByUserId(User user, BigInteger userId) {
        if (user.getAuthorities().contains(Role.ADMIN)) {
            return noteRepository.getAllByUserId(userId);
        } else {
            return noteRepository.getAllByUserId(user.getId());
        }
    }

    public Note getById(User user, BigInteger id) {
        if (user.getAuthorities().contains(Role.ADMIN)) {
            return noteRepository.findById(id);
        } else {
            return noteRepository.findByUserIdAndId(user.getId(), id);
        }
    }

}
