import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Equipe {
    private String nome;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int golsMarcados;
    private int golsSofridos;

    public Equipe(String nome) {
        this.nome = nome;
    }

    // Getters e setters

    public String getNome() {
        return nome;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public void setGolsMarcados(int golsMarcados) {
        this.golsMarcados = golsMarcados;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public void setGolsSofridos(int golsSofridos) {
        this.golsSofridos = golsSofridos;
    }
}

class Jogo {
    private static int count = 0;
    private int id;
    private String equipe1;
    private String equipe2;
    private int placar1;
    private int placar2;
    private int temperatura;

    public Jogo(String equipe1, String equipe2, int temperatura) {
        this.id = ++count;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.temperatura = temperatura;
        jogar();
    }

    private void jogar() {
        Random random = new Random();
        placar1 = random.nextInt(temperatura + 1);
        placar2 = random.nextInt(temperatura + 1);
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getEquipe1() {
        return equipe1;
    }

    public String getEquipe2() {
        return equipe2;
    }

    public int getPlacar1() {
        return placar1;
    }

    public int getPlacar2() {
        return placar2;
    }

    public int getTemperatura() {
        return temperatura;
    }
}

class Liga {
    private static final int MAX_SEMANAS_CONSECUTIVAS_GELADAS = 3;

    private List<Equipe> equipes;
    private List<Jogo> jogos;
    private int semanasConsecutivasGeladas;

    public Liga(String[] nomesEquipes) {
        equipes = new ArrayList<>();
        for (String nome : nomesEquipes) {
            equipes.add(new Equipe(nome));
        }
        jogos = new ArrayList<>();
        semanasConsecutivasGeladas = 0;
    }

    public void simularTemporada() {
        Scanner scanner = new Scanner(System.in);
        int semana = 1;

        while (semanasConsecutivasGeladas < MAX_SEMANAS_CONSECUTIVAS_GELADAS) {
            System.out.print("Digite a temperatura para a semana " + semana + ": ");
            int temperatura = scanner.nextInt();

            if (temperatura <= 0) {
                semanasConsecutivasGeladas++;
                System.out.println("Nenhum jogo agendado devido à temperatura congelante.");
            } else {
                semanasConsecutivasGeladas = 0;
                if (temperatura >= 30) {
                    agendarJogos(2, temperatura);
                } else {
                    agendarJogos(1, temperatura);
                }
            }

            semana++;
        }

        exibirEstatisticas();
        scanner.close();
    }

    private void agendarJogos(int quantidadeJogos, int temperatura) {
        Random random = new Random();
        List<Equipe> equipesDisponiveis = new ArrayList<>(equipes);

        for (int i = 0; i < quantidadeJogos; i++) {
            int indexEquipe1 = random.nextInt(equipesDisponiveis.size());
            Equipe equipe1 = equipesDisponiveis.get(indexEquipe1);
            equipesDisponiveis.remove(indexEquipe1);

            int indexEquipe2 = random.nextInt(equipesDisponiveis.size());
            Equipe equipe2 = equipesDisponiveis.get(indexEquipe2);
            equipesDisponiveis.remove(indexEquipe2);

            jogos.add(new Jogo(equipe1.getNome(), equipe2.getNome(), temperatura));
        }
    }

    private void exibirEstatisticas() {
        System.out.println("Estatísticas da temporada:");
        for (Equipe equipe : equipes) {
            System.out.println("Equipe: " + equipe.getNome());
            System.out.println("Vitórias: " + equipe.getVitorias());
            System.out.println("Derrotas: " + equipe.getDerrotas());
            System.out.println("Empates: " + equipe.getEmpates());
            System.out.println("Gols marcados: " + equipe.getGolsMarcados());
            System.out.println("Gols sofridos: " + equipe.getGolsSofridos());
            System.out.println();
        }

        if (!jogos.isEmpty()) {
            int temperaturaMaxima = Integer.MIN_VALUE;
            int somaTemperaturas = 0;

            for (Jogo jogo : jogos) {
                temperaturaMaxima = Math.max(temperaturaMaxima, jogo.getTemperatura());
                somaTemperaturas += jogo.getTemperatura();
            }

            double temperaturaMedia = somaTemperaturas / (double) jogos.size();

            System.out.println("Estatísticas dos jogos:");
            System.out.println("Temperatura máxima: " + temperaturaMaxima);
            System.out.println("Temperatura média: " + temperaturaMedia);
        } else {
            System.out.println("Nenhum jogo foi realizado nesta temporada.");
        }
    }
    
}

public class Section8 {
    public static void main(String[] args) {
        String[] nomesEquipes = {"Equipe A", "Equipe B", "Equipe C", "Equipe D"};

        Liga liga = new Liga(nomesEquipes);
        liga.simularTemporada();
    }
}
