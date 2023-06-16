import java.util.Scanner;

public class Section6 {
    public static void main(String[] args) {
        int validoPIN = 2104;
        int usuarioPIN;

        Scanner teclado = new Scanner(System.in);

        System.out.print("Insira o PIN: ");
        usuarioPIN = teclado.nextInt();

        while (usuarioPIN != validoPIN) {
            System.out.println("PIN está incorreto. Tente novamente.");
            System.out.print("Insira o PIN: ");
            usuarioPIN = teclado.nextInt();
        }

        System.out.println("PIN correto. Acesso à conta concedido.");
        teclado.close();
    }
}
