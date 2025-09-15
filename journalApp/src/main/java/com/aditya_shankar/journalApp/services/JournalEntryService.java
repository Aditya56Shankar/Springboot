package com.aditya_shankar.journalApp.services;

import com.aditya_shankar.journalApp.Entity.JournalEntry;
import com.aditya_shankar.journalApp.Entity.User;
import com.aditya_shankar.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    public JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){

        try {
            User user=userService.findByUserName(userName);
            JournalEntry save=journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(save);
//            user.setUsername(null);
            userService.saveUser(user);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("error in save entry in @Transaction waala"+e);
        }

    }



    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepo.save(journalEntry);

    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

<<<<<<< HEAD
    public void deleteById(ObjectId id, String userName){
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepo.deleteById(id);
=======
    @Transactional
    public void deleteById(ObjectId id, String userName){
        try {
            User user=userService.findByUserName(userName);
            boolean removed=user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("error in journal entry service delete method ",e);
        }
>>>>>>> c210c02 (added authentication to journal entry)
    }

//    public JournalEntry savePdfEntry(JournalEntry entry, String userName) {
//        return journalEntryRepo.save(entry); // behaves same as normal entry
//    }
//
//
//    public byte[] getPdf(ObjectId id) {
//        return journalEntryRepo.findById(id)
//                .map(JournalEntry::getPdfData)
//                .orElse(null);
//    }
<<<<<<< HEAD
=======
//    public List<JournalEntry> findByUserName(String username){
//        return journalEntryRepo.findByUser_Username(username);
//    }
>>>>>>> c210c02 (added authentication to journal entry)
}
