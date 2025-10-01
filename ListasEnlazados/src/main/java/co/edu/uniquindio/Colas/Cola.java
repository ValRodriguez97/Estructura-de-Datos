package ListasEnlazados.src.main.java.co.edu.uniquindio.Colas;

import java.util.ArrayList;

public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    public Cola(int tamaño) {
        this.tamaño = tamaño;
    }

    public void encolar(T dato){
        Nodo<T> nuevo = new Nodo(dato);
        if(inicio == null){
            inicio = nuevo;
            fin = nuevo;
        }else{
            fin.setProximo(nuevo);
            fin = nuevo;
        }
        tamaño++;
    }

    public T desencolar() {
        if (inicio == null) {
            throw new IllegalStateException("La cola está vacía");
        }

        T dato = inicio.getDato();
        inicio = inicio.getProximo();

        if (inicio == null) { // si quedó vacía
            fin = null;
        }
        return dato;
    }


    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public Nodo<T> getFin() {
        return fin;
    }

    public void setFin(Nodo<T> fin) {
        this.fin = fin;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
}
