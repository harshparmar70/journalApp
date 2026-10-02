//package com.harsh.journalApp.controller;
//
//import com.harsh.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryControllerv2 {
//
//    private Map<Long,JournalEntry> journalEntry = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntry.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry  ){
//        journalEntry.put(myEntry.getId(),myEntry);
//        return true;
//    }
//    @GetMapping("id/{myid}")
//    public JournalEntry findById(@PathVariable Long myid){
//        return journalEntry.get(myid);
//    }
//
//    @DeleteMapping("id/{myid}")
//    public JournalEntry deleteEntryById(@PathVariable Long myid){
//        return journalEntry.remove(myid);
//    }
//
//    @PutMapping("id/{myid}")
//    public JournalEntry updateByid(@PathVariable Long myid,@RequestBody JournalEntry myEntry){
//        return journalEntry.put(myid,myEntry);
//    }
//
//
//
//}
