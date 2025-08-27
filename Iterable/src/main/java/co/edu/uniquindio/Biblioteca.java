package co.edu.uniquindio;

import java.util.Arrays;
import java.util.Iterator;

public class Biblioteca implements Iterable<Libro> {
    private Libro[] libros;
    private int indexLibro;

    public Biblioteca(int cantidad){
           this.indexLibro = 0;
            libros = new Libro[cantidad];
    }

    public void agregarLibro(Libro libro){
        libros[indexLibro] = libro;
        indexLibro++;
    }

    public Libro obtenerLibro(int indexLibro){
        return libros[indexLibro];
    }

    public int size(){
        return indexLibro;
    }

    @Override
    public Iterator<Libro> iterator (){
        return new IteratorBiblioteca(this);
    }

    public Libro[] getLibros() {
        return libros;
    }

    public void setLibros(Libro[] libros) {
        this.libros = libros;
    }

    public int getIndexLibro() {
        return indexLibro;
    }

    public void setIndexLibro(int indexLibro) {
        this.indexLibro = indexLibro;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "libros=" + Arrays.toString(libros) +
                ", indexLibro=" + indexLibro +
                '}';
    }
}
