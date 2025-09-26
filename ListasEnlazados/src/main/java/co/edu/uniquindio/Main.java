package co.edu.uniquindio;

public class Main {
    public static void main(String[] args) {
        ListaSimpleCircularEnlazada<String> lista = new ListaSimpleCircularEnlazada<>();

        lista.agregarPrimero("una lista");
        lista.agregarPrimero("desde");
        lista.agregarPrimero("A todos");
        lista.agregarPrimero(" Hola");
        lista.agregarUltimo("Vanesa");

        lista.mostrar();

    }
}