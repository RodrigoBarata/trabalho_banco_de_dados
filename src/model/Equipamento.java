package model;

// Classe que representa um equipamento disponível no sistema
public class Equipamento {
    // Atributos da classe
    private int id; // Identificador único do equipamento
    private String nome; // Nome do equipamento
    private String descricao; // Descrição detalhada do equipamento
    private String tipo; // Tipo do equipamento
    private boolean disponivel; // Indica se o equipamento está disponível para uso
    private Integer espacoFisicoId; // ID do espaço físico onde o equipamento está alocado (pode ser null)

    // Construtor padrão
    public Equipamento() {}

    // Construtor com todos os parâmetros
    public Equipamento(int id, String nome, String descricao, String tipo, boolean disponivel, Integer espacoFisicoId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.disponivel = disponivel;
        this.espacoFisicoId = espacoFisicoId;
    }

    // Getters e Setters para todos os atributos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public Integer getEspacoFisicoId() { return espacoFisicoId; }
    public void setEspacoFisicoId(Integer espacoFisicoId) { this.espacoFisicoId = espacoFisicoId; }
} 