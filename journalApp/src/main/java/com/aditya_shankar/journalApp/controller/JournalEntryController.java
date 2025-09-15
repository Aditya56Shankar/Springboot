package com.aditya_shankar.journalApp.controller;

import com.aditya_shankar.journalApp.Entity.JournalEntry;
import com.aditya_shankar.journalApp.Entity.User;
import com.aditya_shankar.journalApp.services.JournalEntryService;
import com.aditya_shankar.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userEntityService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntryOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userEntityService.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(journalEntry,userName);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(journalEntry,HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        journalEntryService.deleteById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("id/{id}")
    public ResponseEntity<?> updateid(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if (journalEntry.isPresent()) {
                JournalEntry old=journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
                old.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(newEntry,HttpStatus.OK);
            }
        }
//        JournalEntry old =journalEntryService.findById(id).orElse(null);
//        if(old!=null){
//            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
//            old.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
//            journalEntryService.saveEntry(old);
//            return new ResponseEntity<>(newEntry,HttpStatus.OK);
//        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
