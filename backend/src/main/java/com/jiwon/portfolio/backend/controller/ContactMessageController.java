package com.jiwon.portfolio.backend.controller;

import com.jiwon.portfolio.backend.dto.ContactSubmitRequest;
import com.jiwon.portfolio.backend.entity.ContactMessage;
import com.jiwon.portfolio.backend.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> submit(
            @RequestBody ContactSubmitRequest request) {

        try {
            service.create(request);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        return Map.of(
                "message",
                "문의가 접수되었습니다."
        );
    }

    @GetMapping
    public List<ContactMessage> findAll() {

        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        service.delete(id);
    }
}
