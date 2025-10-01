package ListasEnlazados.src.main.java.co.edu.uniquindio.Pilas;

import ListasEnlazados.src.main.java.co.edu.uniquindio.Colas.Nodo;

public class Pila<T> {
    private Nodo<T> cima;
    private int tamaño;

    public Pila(int tamaño) {
        this.tamaño = tamaño;
    }

    public void empilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setProximo(cima);
        cima = nuevo;
    }

    public T desempilar() {
        T datoEliminado = cima.getDato();
        cima = cima.getProximo();
        return datoEliminado;
    }

    public Nodo<T> getCima() {
        return cima;
    }

    public void setCima(Nodo<T> cima) {
        this.cima = cima;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
}
