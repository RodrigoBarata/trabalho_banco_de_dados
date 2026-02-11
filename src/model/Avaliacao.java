package model;

import java.time.LocalDateTime;

// Classe que representa uma avaliação de um espaço físico
public class Avaliacao {
    // Atributos da classe
    private int id; // Identificador único da avaliação
    private int usuarioId; // ID do usuário que fez a avaliação
    private int espacoFisicoId; // ID do espaço físico avaliado
    private int nota; // Nota atribuída ao espaço (geralmente de 1 a 5)
    private String comentario; // Comentário sobre a experiência com o espaço
    private LocalDateTime dataAvaliacao; // Data e hora em que a avaliação foi feita

    // Construtor padrão
    public Avaliacao() {}

    // Construtor com todos os parâmetros
    public Avaliacao(int id, int usuarioId, int espacoFisicoId, int nota, String comentario, LocalDateTime dataAvaliacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.espacoFisicoId = espacoFisicoId;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    // Getters e Setters para todos os atributos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public int getEspacoFisicoId() { return espacoFisicoId; }
    public void setEspacoFisicoId(int espacoFisicoId) { this.espacoFisicoId = espacoFisicoId; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }
} 