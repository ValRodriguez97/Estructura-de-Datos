package ListasEnlazados.src.main.java.co.edu.uniquindio.Colas;

public class Nodo<T> {
    private T dato;
    private Nodo<T> proximo;

    public Nodo(T dato) {
        this.dato = dato;
        this.proximo = null;
    }

    public T getDato() {
        return dato;
    }

    public Nodo<T> getProximo() {
        return proximo;
    }

    public void setProximo(Nodo<T> proximo) {
        this.proximo = proximo;
    }
}
