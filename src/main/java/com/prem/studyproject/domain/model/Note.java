package com.prem.studyproject.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notes")
@ToString
public class Note {
    @Id
    private BigInteger Id;
    private BigInteger userId;
    private LocalDateTime creationDate;
    private LocalDateTime completeDate;
    private String description;
    private boolean completed;
    private boolean notification;
    private long minutesBeforeNotification;
}
