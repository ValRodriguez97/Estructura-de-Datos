package co.edu.uniquindio;

public class MainGenericsTest {
    public static void main(String[] args) {
        CajaNormal cajaRaquel = new CajaNormal(7);
        System.out.println(cajaRaquel.getNumero());

        CajaGenerica<String> cajaJuanGenerica = new CajaGenerica<>("Sofia", "Juan");
        System.out.println(cajaJuanGenerica.getElemento1());
    }
}
