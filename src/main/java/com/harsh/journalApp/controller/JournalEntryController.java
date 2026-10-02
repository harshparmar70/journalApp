package com.harsh.journalApp.controller;

import com.harsh.journalApp.entity.JournalEntry;
import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.service.JournalEntryService;
import com.harsh.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByuserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return ResponseEntity.ok(all);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> createEntry(@Valid @RequestBody JournalEntry myEntry ,@PathVariable String userName ){
        try {
            journalEntryService.saveEntry(myEntry,userName);
            return ResponseEntity.status(HttpStatus.CREATED).body(myEntry);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage().toString());
        }

    }
    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId myid){
        Optional<JournalEntry> journalEntry = journalEntryService.getByID(myid);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("id/{userName}/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid ,@PathVariable String userName){
        journalEntryService.deleteById(myid,userName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("id/{userName}/{myid}")
    public ResponseEntity<?> updateByid(@PathVariable ObjectId myid,
                                        @RequestBody JournalEntry newEntity,
                                        @PathVariable String userName ) {
        JournalEntry old = journalEntryService.getByID(myid).orElse(null);
        if (old != null) {
            old.setTitle(
                    newEntity.getTitle() != null && !newEntity.getTitle().isEmpty()
                            ? newEntity.getTitle()
                            : old.getTitle()
            );
            old.setContent(
                    newEntity.getContent() != null && !newEntity.getContent().isEmpty()
                            ? newEntity.getContent()
                            : old.getContent()
            );
            old.setData(LocalDateTime.now());
            journalEntryService.saveEntry(old);
            return ResponseEntity.ok(old);
        }
        return  ResponseEntity.notFound().build();
    }



}
