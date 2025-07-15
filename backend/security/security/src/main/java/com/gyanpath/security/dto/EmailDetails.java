package com.gyanpath.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String senderName;
    private String message;
    private String senderEmail;
    private String attachment;

}
