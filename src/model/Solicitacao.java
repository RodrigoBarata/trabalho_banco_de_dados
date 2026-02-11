package model;

import java.time.LocalDateTime;

// Classe que representa uma solicitação de uso de espaço físico
public class Solicitacao {
    // Atributos da classe
    private int id; // Identificador único da solicitação
    private int usuarioId; // ID do usuário que fez a solicitação
    private int espacoFisicoId; // ID do espaço físico solicitado
    private LocalDateTime dataInicio; // Data e hora de início do uso
    private LocalDateTime dataFim; // Data e hora de término do uso
    private String status; // Status da solicitação (PENDENTE, APROVADA, REJEITADA)
    private String motivo; // Motivo da solicitação ou justificativa
    private LocalDateTime dataCriacao; // Data e hora em que a solicitação foi criada

    // Construtor padrão
    public Solicitacao() {}

    // Construtor com todos os parâmetros
    public Solicitacao(int id, int usuarioId, int espacoFisicoId, LocalDateTime dataInicio, LocalDateTime dataFim, String status, String motivo, LocalDateTime dataCriacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.espacoFisicoId = espacoFisicoId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.motivo = motivo;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters para todos os atributos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public int getEspacoFisicoId() { return espacoFisicoId; }
    public void setEspacoFisicoId(int espacoFisicoId) { this.espacoFisicoId = espacoFisicoId; }
    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}