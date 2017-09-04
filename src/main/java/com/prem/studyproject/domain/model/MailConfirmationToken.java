package com.prem.studyproject.domain.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tokens")
@ToString
public class MailConfirmationToken {
    @Id
    private BigInteger id;
    @Indexed(unique = true)
    private BigInteger userId;
    @Indexed(unique = true)
    private String value;
    private LocalDateTime creationTime;
    private boolean active;
}
