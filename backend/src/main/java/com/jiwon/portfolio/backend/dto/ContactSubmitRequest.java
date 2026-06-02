package com.jiwon.portfolio.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactSubmitRequest {

    private String name;

    private String email;

    private String message;
}
