package com.harsh.journalApp.repository;

import com.harsh.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {



}
