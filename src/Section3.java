import java.util.Scanner;

public class Section3 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Entradas
        System.out.print("Digite seu nome: ");
        String name = teclado.nextLine();

        System.out.print("Digite sua idade: ");
        int age = teclado.nextInt();
        teclado.nextLine(); // Limpar o buffer do scanner

        System.out.print("Digite o nome de uma cidade: ");
        String city = teclado.nextLine();

        System.out.print("Digite a temperatura em graus Celsius: ");
        double temperature = teclado.nextDouble();

        // Processamentos
        int days = age * 365;
        double fahrenheit = (temperature * 9 / 5) + 32;

        // Ajeitar ideias
        String story = "Olá, " + name + "!\n";
        story += "Você tem " + age + " anos, o que equivale a aproximadamente " + days + " dias de vida.\n";
        story += "Você está em " + city + " e a temperatura atual é de " + temperature + " graus Celsius.\n";
        story += "Isso é equivalente a " + fahrenheit + " graus Fahrenheit.";

        // Saídas
        System.out.println(story);

        teclado.close();
    }
}
