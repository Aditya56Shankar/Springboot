package com.aditya_shankar.journalApp.Entity;



import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "user_journal")
@Data

public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    private String pass;
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> roles;
}
