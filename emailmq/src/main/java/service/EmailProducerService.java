package com.puc.emailmq.service;

import com.puc.emailmq.config.RabbitConfig;
import com.puc.emailmq.dto.SolicitacaoEnvioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailProducerService {

    private static final Logger log = LoggerFactory.getLogger(EmailProducerService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publicarSolicitacaoEnvio(SolicitacaoEnvioDto solicitacao) {
        log.info("[PRODUCER] [eventId={}] Publicando solicitação na exchange: {}", solicitacao.getEventId(), RabbitConfig.EXCHANGE_EMAILS);
        
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_EMAILS,
                RabbitConfig.ROUTING_KEY_EMAILS,
                solicitacao
        );
    }
}
