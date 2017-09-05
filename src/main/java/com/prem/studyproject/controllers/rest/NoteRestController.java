package com.prem.studyproject.controllers.rest;

import com.prem.studyproject.domain.model.Note;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author Melnyk_Dmytro
 * @version 1.0
 * @since 05.09.2017
 */

@RestController
public class NoteRestController {

    private NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "/notes/count/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Long> getCount(Map<String, Object> model, Authentication authentication,
                                        @PathVariable(required = true) String userId) {
        User user = (User) authentication.getPrincipal();
        Long count = noteService.getCountByUserId(user, new BigInteger(userId));
        if (count != null) {
            return new ResponseEntity<>(count, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/notes/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Note>> getNotesOfUser(Map<String, Object> model, Authentication authentication,
                                                     @PathVariable(required = true) String userId) {
        User user = (User) authentication.getPrincipal();
        List<Note> notes = noteService.getAllByUserId(user, new BigInteger(userId));
        if (notes != null) {
            return new ResponseEntity<>(notes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Note> getNote(Map<String, Object> model, Authentication authentication,
                               @PathVariable(required = true) String id) {
        User user = (User) authentication.getPrincipal();
        Note note = noteService.getById(user, new BigInteger(id));
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/note/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Note> updateNote(Map<String, Object> model, Authentication authentication,
                                        @RequestParam(required = true) Note note) {
        User user = (User) authentication.getPrincipal();
        Note savedNote = noteService.updateNote(user, note);
        if (note != null) {
            return new ResponseEntity<>(savedNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Note> deleteNote(Map<String, Object> model, Authentication authentication,
                                           @PathVariable(required = true) String id) {
        User user = (User) authentication.getPrincipal();
       boolean result = noteService.deleteNote(user, new BigInteger(id));
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/note", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Note> createNote(Map<String, Object> model, Authentication authentication,
                                           @RequestParam(required = true) Note note) {
        User user = (User) authentication.getPrincipal();
        Note createdNote = noteService.createNote(note, user);
        if (createdNote != null) {
            return new ResponseEntity<>(createdNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
