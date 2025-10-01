package ListasEnlazados.src.main.java.co.edu.uniquindio.Colas;

import java.util.ArrayList;

public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;
    private int contador;

    public Cola(int tamaño) {
        this.tamaño = tamaño;
        contador = 0;
    }

    public void encolar(T dato){
        if(contador == tamaño){
            throw new IllegalStateException("La cola esta llena");
        }
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
