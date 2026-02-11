package model;

// Classe que representa um usuário do sistema
public class Usuario {
    // Atributos da classe
    private int id; // Identificador único do usuário
    private String nome; // Nome completo do usuário
    private String email; // Email do usuário (usado para login)
    private String senha; // Senha do usuário
    private String tipo; // Tipo do usuário (ADMIN, PROFESSOR, ALUNO)

    // Construtor padrão
    public Usuario() {}

    // Construtor com todos os parâmetros
    public Usuario(int id, String nome, String email, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Getters e Setters para todos os atributos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}