public class Section4 {
    public static void main(String[] args) {
        ComputeMethods compute = new ComputeMethods();

        double degreesF = 100.0;
        double celsius = compute.fToC(degreesF);
        System.out.println("A temperatura em Celsius é de: " + celsius);

        int a = 3;
        int b = 4;
        double hypotenuse = compute.hypotenuse(a, b);
        System.out.println("Hipotenusa é igual a:  " + hypotenuse);

        int rollValue = compute.roll();
        System.out.println("O valor do lançamento do dado é de:  " + rollValue);
    }
}

class ComputeMethods {
    public double fToC(double degreesF) {
        return (degreesF - 32) * 5 / 9;
    }

    public double hypotenuse(int a, int b) {
        return Math.sqrt(a * a + b * b);
    }

    public int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}