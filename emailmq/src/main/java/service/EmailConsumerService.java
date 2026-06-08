package com.puc.emailmq.service;

import com.puc.emailmq.config.RabbitConfig;
import com.puc.emailmq.dto.SolicitacaoEnvioDto;
import com.puc.emailmq.model.EmailDestinatario;
import com.puc.emailmq.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailConsumerService {

    private static final Logger log = LoggerFactory.getLogger(EmailConsumerService.class);

    @Autowired
    private EmailRepository emailRepository;

    @RabbitListener(queues = RabbitConfig.QUEUE_EMAILS)
    public void processarEnvioEmails(SolicitacaoEnvioDto solicitacao) {
        log.info("[CONSUMER] [eventId={}] Nova tarefa de envio em lote detectada.", solicitacao.getEventId());

        List<EmailDestinatario> destinatarios = emailRepository.findAll();

        if (destinatarios.isEmpty()) {
            log.warn("[CONSUMER] [eventId={}] Cancelado: Nenhum e-mail registrado no banco de dados.", solicitacao.getEventId());
            return;
        }

        log.info("[CONSUMER] [eventId={}] Iniciando em lote para {} destinatários.", solicitacao.getEventId(), destinatarios.size());

        for (EmailDestinatario destinatario : destinatarios) {
            try {
                Thread.sleep(400); 
                log.info("[ENVIO SUCESSO] [eventId={}] E-mail enviado para: {} <{}> | Assunto: {}", 
                        solicitacao.getEventId(), destinatario.getNome(), destinatario.getEmail(), solicitacao.getAssunto());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("[ERRO] Falha na simulação de envio", e);
            }
        }

        log.info("[CONSUMER] [eventId={}] Lote finalizado com sucesso.", solicitacao.getEventId());
    }
}
