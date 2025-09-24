package co.edu.uniquindio;

public class MainEnterosTest {
    public static void main(String[] args) {
        EnterosGenerico<Integer> parEnteros = new EnterosGenerico<>(1, 2);
        System.out.println(parEnteros.getB());
    }
}
