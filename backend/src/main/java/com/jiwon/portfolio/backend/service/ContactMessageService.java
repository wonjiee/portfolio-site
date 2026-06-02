package com.jiwon.portfolio.backend.service;

import com.jiwon.portfolio.backend.dto.ContactSubmitRequest;
import com.jiwon.portfolio.backend.entity.ContactMessage;
import com.jiwon.portfolio.backend.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository repository;

    public ContactMessage create(ContactSubmitRequest request) {

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("이메일을 입력해 주세요.");
        }

        if (request.getMessage() == null || request.getMessage().isBlank()) {
            throw new IllegalArgumentException("메시지를 입력해 주세요.");
        }

        ContactMessage contact = new ContactMessage();
        contact.setName(
                request.getName() != null
                        ? request.getName().trim()
                        : null
        );
        contact.setEmail(request.getEmail().trim());
        contact.setMessage(request.getMessage().trim());

        return repository.save(contact);
    }

    public List<ContactMessage> findAll() {

        return repository.findAllByOrderByCreatedAtDesc();
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
