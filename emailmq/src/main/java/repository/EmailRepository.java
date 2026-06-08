package com.puc.emailmq.repository;

import com.puc.emailmq.model.EmailDestinatario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailDestinatario, Long> {
}
