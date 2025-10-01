package ListasEnlazados.src.main.java.co.edu.uniquindio.Colas;

public class BiCola<T>{
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    public BiCola(int tamaño){
        this.tamaño = tamaño;
    }

    public void agregarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setProximo(inicio);
            inicio = nuevo;
        }
    }

    public T eliminarAlFinal() {
        T datoEliminado;

        if (inicio == fin) { // Solo un nodo
            datoEliminado = fin.getDato();
            inicio = null;
            fin = null;
        } else {
            Nodo<T> actual = inicio;
            while (actual.getProximo() != fin) {
                actual = actual.getProximo();
            }
            datoEliminado = fin.getDato();
            fin = actual;
            fin.setProximo(null);
        }

        return datoEliminado;
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
