package com.harsh.journalApp.service;

import com.harsh.journalApp.entity.JournalEntry;
import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByuserName(userName);
            journalEntry.setData(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
//            user.setUserName(null);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error ocuur during run time");
        }

    }
    public void saveEntry(JournalEntry journalEntry){
        try {
            journalEntry.setData(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);

        }catch (Exception e){
            log.error("Exception ",e);
        }

    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getByID(ObjectId id){

        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id,String userName){
        User user = userService.findByuserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }

}
