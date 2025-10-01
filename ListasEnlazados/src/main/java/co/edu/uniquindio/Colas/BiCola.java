package ListasEnlazados.src.main.java.co.edu.uniquindio.Colas;

public class BiCola<T> extends Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    public BiCola(){
        super();
    }

    public void agregarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            tamaño++;
            return;
        } else {
            nuevo.setProximo(inicio);
            inicio = nuevo;
            tamaño++;
        }
    }

    public T eliminarAlFinal() {
        T datoEliminado;
        if (inicio == fin) {
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

}
