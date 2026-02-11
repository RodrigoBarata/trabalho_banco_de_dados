// Importações necessárias para conexão com banco de dados
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe principal que demonstra a conexão com o banco de dados PostgreSQL
public class App {
    public static void main(String[] args) {
        // Configurações de conexão com o banco de dados
        String url = "jdbc:postgresql://localhost:5432/gestao_espaco"; // URL do banco de dados
        String user = "postgres"; // Usuário do banco
        String password = "postgres"; // Senha do banco

        try {
            // Carrega o driver do PostgreSQL
            Class.forName("org.postgresql.Driver"); 
            // Estabelece a conexão com o banco de dados
            Connection conn = DriverManager.getConnection(url, user, password);

            // Verifica se a conexão foi estabelecida com sucesso
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            // Trata erros de conexão com o banco
            System.out.println("Connection failed: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Trata erro caso o driver do PostgreSQL não seja encontrado
            System.out.println("PostgreSQL Driver not found: " + e.getMessage());
        }
    }
}