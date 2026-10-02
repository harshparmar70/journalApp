package com.harsh.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection= "journal_entries")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    @NotEmpty
    private String title;
    private  String content;
    private LocalDateTime data;




}
