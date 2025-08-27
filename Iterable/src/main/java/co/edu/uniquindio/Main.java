package co.edu.uniquindio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Biblioteca newBiblioteca = new Biblioteca(5);
        newBiblioteca.agregarLibro(new Libro("Cien años de soledad"));
        newBiblioteca.agregarLibro(new Libro("El nombre del viento"));
        newBiblioteca.agregarLibro(new Libro("El arte de la guerra"));

        for (Libro libro : newBiblioteca){
            System.out.println(libro);
        }
    }
}