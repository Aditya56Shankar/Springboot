package com.aditya_shankar.journalApp.controller;

import com.aditya_shankar.journalApp.Entity.JournalEntry;
import com.aditya_shankar.journalApp.Entity.User;
import com.aditya_shankar.journalApp.services.JournalEntryService;
import com.aditya_shankar.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userEntityService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String userName){
        User user=userEntityService.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping("{UserName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry,@PathVariable String UserName){
        try {

            journalEntryService.saveEntry(journalEntry,UserName);
            return new ResponseEntity<>(journalEntry,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(journalEntry,HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry= journalEntryService.findById(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



    @DeleteMapping("id/{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id,@PathVariable String userName){
        journalEntryService.deleteById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("id/{UserName}/{id}")
    public ResponseEntity<?> updateid(
            @PathVariable ObjectId id,
            @PathVariable String UserName,
            @RequestBody JournalEntry newEntry
    ){
        JournalEntry old =journalEntryService.findById(id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(newEntry,HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
