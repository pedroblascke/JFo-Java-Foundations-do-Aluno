import java.util.Random;
import java.util.Scanner;

class Cartao {
    private int numeroIdentificacao;
    private int saldoCredito;
    private int saldoTickets;

    public Cartao(int numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
        this.saldoCredito = 0;
        this.saldoTickets = 0;
    }

    public int getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public int getSaldoCredito() {
        return saldoCredito;
    }

    public int getSaldoTickets() {
        return saldoTickets;
    }

    public void adicionarCredito(int valor) {
        if (valor > 0) {
            saldoCredito += valor;
        }
    }

    public void removerCredito(int valor) {
        if (valor > 0 && valor <= saldoCredito) {
            saldoCredito -= valor;
        }
    }

    public void adicionarTickets(int valor) {
        if (valor > 0) {
            saldoTickets += valor;
        }
    }

    public void removerTickets(int valor) {
        if (valor > 0 && valor <= saldoTickets) {
            saldoTickets -= valor;
        }
    }
}

class Jogo {
    private String nome;
    private int creditosNecessarios;

    public Jogo(String nome, int creditosNecessarios) {
        this.nome = nome;
        this.creditosNecessarios = creditosNecessarios;
    }

    public String getNome() {
        return nome;
    }

    public int getCreditosNecessarios() {
        return creditosNecessarios;
    }

    public int jogar(Cartao cartao) {
        if (cartao.getSaldoCredito() >= creditosNecessarios) {
            Random random = new Random();
            int ticketsGanhos = random.nextInt(10) + 1;
            cartao.removerCredito(creditosNecessarios);
            cartao.adicionarTickets(ticketsGanhos);
            return ticketsGanhos;
        } else {
            System.out.println("Créditos insuficientes para jogar o jogo: " + nome);
            return 0;
        }
    }
}

class Premio {
    private String nome;
    private int ticketsNecessarios;
    private int quantidadeDisponivel;

    public Premio(String nome, int ticketsNecessarios, int quantidadeDisponivel) {
        this.nome = nome;
        this.ticketsNecessarios = ticketsNecessarios;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getNome() {
        return nome;
    }

    public int getTicketsNecessarios() {
        return ticketsNecessarios;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void reduzirQuantidadeDisponivel() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        }
    }
}

class Terminal {
    private Cartao cartao;
    public Terminal(Premio[] premios) {
        this.cartao = null;
    }

    public void inserirCartao(Cartao cartao) {
        this.cartao = cartao;
        System.out.println("Cartão " + cartao.getNumeroIdentificacao() + " inserido no terminal.");
    }

    public void carregarCreditos(int valor) {
        if (cartao != null) {
            cartao.adicionarCredito(valor);
            System.out.println("Cartão " + cartao.getNumeroIdentificacao() + " carregado com " + valor + " créditos.");
            System.out.println("Saldo de créditos: " + cartao.getSaldoCredito());
        }
    }

    public void verificarSaldos() {
        if (cartao != null) {
            System.out.println("Cartão " + cartao.getNumeroIdentificacao());
            System.out.println("Saldo de créditos: " + cartao.getSaldoCredito());
            System.out.println("Saldo de tíquetes: " + cartao.getSaldoTickets());
        }
    }

    public void transferirCreditos(Terminal destino, int valor) {
        if (cartao != null && destino != null) {
            if (valor > 0 && valor <= cartao.getSaldoCredito()) {
                cartao.removerCredito(valor);
                destino.cartao.adicionarCredito(valor);
                System.out.println("Transferência de créditos concluída.");
                System.out.println("Saldo de créditos no cartão " + cartao.getNumeroIdentificacao() + ": " + cartao.getSaldoCredito());
                System.out.println("Saldo de créditos no cartão " + destino.cartao.getNumeroIdentificacao() + ": " + destino.cartao.getSaldoCredito());
            } else {
                System.out.println("Transferência de créditos inválida.");
            }
        }
    }

    public void transferirTickets(Terminal destino, int valor) {
        if (cartao != null && destino != null) {
            if (valor > 0 && valor <= cartao.getSaldoTickets()) {
                cartao.removerTickets(valor);
                destino.cartao.adicionarTickets(valor);
                System.out.println("Transferência de tíquetes concluída.");
                System.out.println("Saldo de tíquetes no cartão " + cartao.getNumeroIdentificacao() + ": " + cartao.getSaldoTickets());
                System.out.println("Saldo de tíquetes no cartão " + destino.cartao.getNumeroIdentificacao() + ": " + destino.cartao.getSaldoTickets());
            } else {
                System.out.println("Transferência de tíquetes inválida.");
            }
        }
    }

    public void solicitarPremio(Premio premio) {
        if (cartao != null) {
            if (premio.getTicketsNecessarios() <= cartao.getSaldoTickets()) {
                if (premio.getQuantidadeDisponivel() > 0) {
                    cartao.removerTickets(premio.getTicketsNecessarios());
                    premio.reduzirQuantidadeDisponivel();
                    System.out.println("Prêmio " + premio.getNome() + " concedido ao cartão " + cartao.getNumeroIdentificacao());
                    System.out.println("Saldo de tíquetes: " + cartao.getSaldoTickets());
                    System.out.println("Quantidade restante do prêmio " + premio.getNome() + ": " + premio.getQuantidadeDisponivel());
                } else {
                    System.out.println("Prêmio " + premio.getNome() + " indisponível.");
                }
            } else {
                System.out.println("Tíquetes insuficientes para solicitar o prêmio " + premio.getNome());
            }
        }
    }

    public void jogarJogo(Jogo jogo) {
        if (cartao != null) {
            int ticketsGanhos = jogo.jogar(cartao);
            if (ticketsGanhos > 0) {
                System.out.println("Cartão " + cartao.getNumeroIdentificacao() + " jogou o jogo " + jogo.getNome());
                System.out.println("Tíquetes ganhos: " + ticketsGanhos);
                System.out.println("Saldo de tíquetes: " + cartao.getSaldoTickets());
            }
        }
    }
}

public class Section7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação dos prêmios
        Premio premio1 = new Premio("Prêmio 1", 10, 5);
        Premio premio2 = new Premio("Prêmio 2", 15, 3);
        Premio premio3 = new Premio("Prêmio 3", 20, 2);
        Premio[] premios = {premio1, premio2, premio3};

        // Criação dos terminais
        Terminal terminal1 = new Terminal(premios);
        Terminal terminal2 = new Terminal(premios);

        // Criação dos cartões
        Cartao cartao1 = new Cartao(1);
        Cartao cartao2 = new Cartao(2);

        // Carregar créditos nos cartões
        cartao1.adicionarCredito(20);
        cartao2.adicionarCredito(15);

        // Inserir cartões nos terminais
        terminal1.inserirCartao(cartao1);
        terminal2.inserirCartao(cartao2);

        // Jogar jogo no terminal 1
        terminal1.jogarJogo(new Jogo("Jogo 1", 5));

        // Transferir saldo de créditos e tíquetes do cartão 1 para o cartão 2
        terminal1.transferirCreditos(terminal2, cartao1.getSaldoCredito());
        terminal1.transferirTickets(terminal2, cartao1.getSaldoTickets());

        // Solicitar prêmios usando o cartão 2
        terminal2.solicitarPremio(premio1);
        terminal2.solicitarPremio(premio2);

        // Tentar jogar jogo e solicitar prêmio usando o cartão 1
        terminal1.jogarJogo(new Jogo("Jogo 2", 10));
        terminal1.solicitarPremio(premio3);

        // Verificar saldos dos cartões
        terminal1.verificarSaldos();
        terminal2.verificarSaldos();

        scanner.close();
    }
}
