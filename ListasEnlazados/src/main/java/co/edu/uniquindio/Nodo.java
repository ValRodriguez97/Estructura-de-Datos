package ListasEnlazados.src.main.java.co.edu.uniquindio;

public class Nodo<T> {
    private T dato;
    private Nodo<T> proximo;

    public Nodo(T dato) {
        this.dato = dato;
        this.proximo = null;
    }

    // Getter y Setter del dato
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    // Getter y Setter del proximo
    public Nodo<T> getProximo() {
        return proximo;
    }

    public void setProximo(Nodo<T> proximo) {
        this.proximo = proximo;
    }
}
