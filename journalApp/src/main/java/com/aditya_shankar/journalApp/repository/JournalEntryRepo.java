package com.aditya_shankar.journalApp.repository;

import com.aditya_shankar.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;


public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}
