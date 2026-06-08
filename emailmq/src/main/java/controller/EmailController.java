package com.puc.emailmq.controller;

import com.puc.emailmq.dto.SolicitacaoEnvioDto;
import com.puc.emailmq.model.EmailDestinatario;
import com.puc.emailmq.repository.EmailRepository;
import com.puc.emailmq.service.EmailProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailProducerService producerService;

    @PostMapping("/cadastrar")
    public ResponseEntity<EmailDestinatario> cadastrarEmail(@RequestBody EmailDestinatario destinatario) {
        EmailDestinatario salvo = emailRepository.save(destinatario);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmailDestinatario>> listarEmails() {
        return ResponseEntity.ok(emailRepository.findAll());
    }

    @PostMapping("/enviar-lote")
    public ResponseEntity<String> dispararLote(@RequestBody SolicitacaoEnvioDto solicitacao) {
        producerService.publicarSolicitacaoEnvio(solicitacao);
        return ResponseEntity.ok("Solicitação enviada para a fila do RabbitMQ. EventId: " + solicitacao.getEventId());
    }
}
