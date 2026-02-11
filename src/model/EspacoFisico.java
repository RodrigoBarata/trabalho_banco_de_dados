package model;

// Classe que representa um espaço físico no sistema
public class EspacoFisico {
    private int id;
    private String nome;
    private String descricao;
    private int capacidade;
    private String tipo;
    private boolean disponivel;

    public EspacoFisico() {}

    public EspacoFisico(int id, String nome, String descricao, int capacidade, String tipo, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.disponivel = disponivel;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getCapacidade() { return capacidade; }
    public String getTipo() { return tipo; }
    public boolean isDisponivel() { return disponivel; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}