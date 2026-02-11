package model;

import java.time.LocalDateTime;

// Classe que representa um registro de auditoria do sistema
public class Auditoria {
    // Atributos da classe
    private int id; // Identificador único do registro de auditoria
    private int usuarioId; // ID do usuário que realizou a ação
    private String acao; // Tipo de ação realizada (CREATE, UPDATE, DELETE)
    private String tabela; // Nome da tabela afetada pela ação
    private int registroId; // ID do registro afetado pela ação
    private LocalDateTime dataAcao; // Data e hora em que a ação foi realizada
    private String detalhes; // Detalhes adicionais sobre a ação

    // Construtor padrão
    public Auditoria() {}

    // Construtor com todos os parâmetros
    public Auditoria(int id, int usuarioId, String acao, String tabela, int registroId, LocalDateTime dataAcao, String detalhes) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.acao = acao;
        this.tabela = tabela;
        this.registroId = registroId;
        this.dataAcao = dataAcao;
        this.detalhes = detalhes;
    }

    // Getters e Setters para todos os atributos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getAcao() { return acao; }
    public void setAcao(String acao) { this.acao = acao; }
    public String getTabela() { return tabela; }
    public void setTabela(String tabela) { this.tabela = tabela; }
    public int getRegistroId() { return registroId; }
    public void setRegistroId(int registroId) { this.registroId = registroId; }
    public LocalDateTime getDataAcao() { return dataAcao; }
    public void setDataAcao(LocalDateTime dataAcao) { this.dataAcao = dataAcao; }
    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }
}