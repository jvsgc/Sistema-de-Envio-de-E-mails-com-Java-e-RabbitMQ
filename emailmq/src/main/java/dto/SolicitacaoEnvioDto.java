package com.puc.emailmq.dto;

import java.io.Serializable;
import java.util.UUID;

public class SolicitacaoEnvioDto implements Serializable {

    private String eventId;
    private String assunto;
    private String corpoMensagem;

    public SolicitacaoEnvioDto() {
        this.eventId = UUID.randomUUID().toString();
    }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }
    public String getCorpoMensagem() { return corpoMensagem; }
    public void setCorpoMensagem(String corpoMensagem) { this.corpoMensagem = corpoMensagem; }
}
