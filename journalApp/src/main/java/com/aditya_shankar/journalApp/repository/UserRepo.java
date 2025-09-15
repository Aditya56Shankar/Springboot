package com.aditya_shankar.journalApp.repository;

import com.aditya_shankar.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//public interface UserRepo extends MongoRepository<User, ObjectId> {
//    User findByUserName(String username);
//}
public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    void deleteByUsername(String username);
}

