// Importações das classes de modelo
import model.Usuario;
import model.EspacoFisico;
import model.Solicitacao;
// Importações dos serviços
import service.UsuarioService;
import service.EspacoFisicoService;
import service.SolicitacaoService;
import service.AuditoriaService;

import java.util.List;
import java.util.Scanner;

// Classe principal que implementa a interface do usuário do sistema
public class MainApp {
    public static void main(String[] args) {
        // Inicialização dos componentes necessários
        Scanner sc = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();
        EspacoFisicoService espacoService = new EspacoFisicoService();
        SolicitacaoService solicitacaoService = new SolicitacaoService();
        AuditoriaService auditoriaService = new AuditoriaService();
        Usuario usuarioLogado = null;

        // Loop principal do programa
        while (true) {
            // Menu para usuários não logados
            if (usuarioLogado == null) {
                System.out.println("1 - Cadastrar usuário");
                System.out.println("2 - Login");
                System.out.println("0 - Sair");
                int op = sc.nextInt(); sc.nextLine();
                if (op == 1) {
                    // Processo de cadastro de novo usuário
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Senha: "); String senha = sc.nextLine();
                    System.out.print("Tipo (ADMIN/PROFESSOR/ALUNO): "); String tipo = sc.nextLine();
                    try {
                        boolean ok = usuarioService.cadastrarUsuario(nome, email, senha, tipo);
                        System.out.println(ok ? "Usuário cadastrado!" : "Email já cadastrado.");
                    } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
                } else if (op == 2) {
                    // Processo de login
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Senha: "); String senha = sc.nextLine();
                    try {
                        usuarioLogado = usuarioService.autenticar(email, senha);
                        System.out.println(usuarioLogado != null ? "Login realizado!" : "Credenciais inválidas.");
                    } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
                } else if (op == 0) {
                    break;
                }
            } else {
                // Menu para usuários logados
                System.out.println("\nBem-vindo, " + usuarioLogado.getNome() + " (" + usuarioLogado.getTipo() + ")");
                System.out.println("1 - Listar espaços físicos");
                // Opções exclusivas para administradores
                if (usuarioService.isAdmin(usuarioLogado)) {
                    System.out.println("2 - Criar espaço físico");
                    System.out.println("3 - Atualizar espaço físico");
                    System.out.println("4 - Deletar espaço físico");
                }
                System.out.println("5 - Criar solicitação de uso");
                System.out.println("6 - Minhas solicitações");
                // Opções exclusivas para administradores
                if (usuarioService.isAdmin(usuarioLogado)) {
                    System.out.println("7 - Aprovar solicitação");
                    System.out.println("8 - Rejeitar solicitação");
                }
                System.out.println("9 - Consultar auditoria");
                System.out.println("0 - Logout");
                int op = sc.nextInt(); sc.nextLine();
                try {
                    if (op == 1) {
                        // Lista todos os espaços físicos
                        List<EspacoFisico> lista = espacoService.listarTodos();
                        for (EspacoFisico e : lista) {
                            System.out.println(e.getId() + " - " + e.getNome() + " (" + e.getTipo() + ") Capacidade: " + e.getCapacidade() + " Disponível: " + e.isDisponivel());
                        }
                    } else if (op == 2 && usuarioService.isAdmin(usuarioLogado)) {
                        // Criação de novo espaço físico (apenas admin)
                        System.out.print("Nome: "); String nome = sc.nextLine();
                        System.out.print("Descrição: "); String desc = sc.nextLine();
                        System.out.print("Capacidade: "); int cap = sc.nextInt(); sc.nextLine();
                        System.out.print("Tipo: "); String tipo = sc.nextLine();
                        EspacoFisico e = new EspacoFisico(0, nome, desc, cap, tipo, true);
                        espacoService.criarEspaco(e, usuarioLogado);
                        System.out.println("Espaço criado!");
                    } else if (op == 3 && usuarioService.isAdmin(usuarioLogado)) {
                        // Atualização de espaço físico (apenas admin)
                        System.out.print("ID do espaço: "); int id = sc.nextInt(); sc.nextLine();
                        EspacoFisico e = espacoService.buscarPorId(id);
                        if (e != null) {
                            System.out.print("Novo nome: "); e.setNome(sc.nextLine());
                            System.out.print("Nova descrição: "); e.setDescricao(sc.nextLine());
                            System.out.print("Nova capacidade: "); e.setCapacidade(sc.nextInt()); sc.nextLine();
                            System.out.print("Novo tipo: "); e.setTipo(sc.nextLine());
                            System.out.print("Disponível (true/false): "); e.setDisponivel(Boolean.parseBoolean(sc.nextLine()));
                            espacoService.atualizarEspaco(e, usuarioLogado);
                            System.out.println("Espaço atualizado!");
                        } else {
                            System.out.println("Espaço não encontrado.");
                        }
                    } else if (op == 4 && usuarioService.isAdmin(usuarioLogado)) {
                        // Deleção de espaço físico (apenas admin)
                        System.out.print("ID do espaço: "); int id = sc.nextInt(); sc.nextLine();
                        espacoService.deletarEspaco(id, usuarioLogado);
                        System.out.println("Espaço deletado!");
                    } else if (op == 5) {
                        // Criação de solicitação de uso
                        System.out.print("ID do espaço: "); int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Data início (YYYY-MM-DDTHH:MM): "); String di = sc.nextLine();
                        System.out.print("Data fim (YYYY-MM-DDTHH:MM): "); String df = sc.nextLine();
                        Solicitacao s = new Solicitacao();
                        s.setEspacoFisicoId(id);
                        s.setDataInicio(java.time.LocalDateTime.parse(di));
                        s.setDataFim(java.time.LocalDateTime.parse(df));
                        boolean ok = solicitacaoService.criarSolicitacao(s, usuarioLogado);
                        System.out.println(ok ? "Solicitação criada!" : "Conflito de horário ou duplicidade.");
                    } else if (op == 6) {
                        // Listagem das solicitações do usuário
                        List<Solicitacao> lista = solicitacaoService.listarPorUsuario(usuarioLogado.getId());
                        for (Solicitacao s : lista) {
                            System.out.println(s.getId() + " - Espaço: " + s.getEspacoFisicoId() + " Início: " + s.getDataInicio() + " Fim: " + s.getDataFim() + " Status: " + s.getStatus());
                        }
                    } else if (op == 7 && usuarioService.isAdmin(usuarioLogado)) {
                        // Aprovação de solicitação (apenas admin)
                        System.out.print("ID da solicitação: "); int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Justificativa: "); String just = sc.nextLine();
                        boolean ok = solicitacaoService.aprovarSolicitacao(id, usuarioLogado, just);
                        System.out.println(ok ? "Solicitação aprovada!" : "Solicitação não encontrada ou já processada.");
                    } else if (op == 8 && usuarioService.isAdmin(usuarioLogado)) {
                        // Rejeição de solicitação (apenas admin)
                        System.out.print("ID da solicitação: "); int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Justificativa: "); String just = sc.nextLine();
                        boolean ok = solicitacaoService.rejeitarSolicitacao(id, usuarioLogado, just);
                        System.out.println(ok ? "Solicitação rejeitada!" : "Solicitação não encontrada ou já processada.");
                    } else if (op == 9) {
                        // Consulta de registros de auditoria
                        auditoriaService.listarTodos().forEach(a -> {
                            System.out.println(a.getId() + " - Usuário: " + a.getUsuarioId() + " Ação: " + a.getAcao() + " Tabela: " + a.getTabela() + " Registro: " + a.getRegistroId() + " Data: " + a.getDataAcao() + " Detalhes: " + a.getDetalhes());
                        });
                    } else if (op == 0) {
                        // Logout do usuário
                        usuarioLogado = null;
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        }
        sc.close();
    }
} 