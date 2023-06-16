import java.util.Scanner;

public class Section5 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Insira um código de cor: ");
        double wavelength = teclado.nextDouble();
        teclado.close();

        if (wavelength >= 380 && wavelength < 450) {
            System.out.println("A cor é violeta");
        } else if (wavelength >= 450 && wavelength < 495) {
            System.out.println("A cor é azul");
        } else if (wavelength >= 495 && wavelength < 570) {
            System.out.println("A cor é verde");
        } else if (wavelength >= 570 && wavelength < 590) {
            System.out.println("A cor é amarelo");
        } else if (wavelength >= 590 && wavelength < 620) {
            System.out.println("A cor é laranja");
        } else if (wavelength >= 620 && wavelength <= 750) {
            System.out.println("A cor é vermelha");
        } else {
            System.out.println("O comprimento de onda inserido não faz parte do espectro visível");
        }
    }
}
