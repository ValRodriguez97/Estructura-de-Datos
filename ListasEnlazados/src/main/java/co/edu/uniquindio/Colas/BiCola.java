package ListasEnlazados.src.main.java.co.edu.uniquindio.Colas;

import java.util.Comparator;

public class BiCola<T> extends Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    public BiCola(){
        super();
        this.inicio = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // =========================
    // INSERCIÓN / ELIMINACIÓN
    // =========================
    public void agregarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setProximo(inicio);
            inicio = nuevo;
        }
        tamaño++;
    }

    public void agregarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setProximo(nuevo);
            fin = nuevo;
        }
        tamaño++;
    }

    public T eliminarAlInicio() {
        if (inicio == null) {
            throw new IllegalStateException("La BiCola está vacía");
        }
        T dato = inicio.getDato();
        inicio = inicio.getProximo();
        if (inicio == null) { // se eliminó el último
            fin = null;
        }
        tamaño--;
        return dato;
    }

    public T eliminarAlFinal() {
        if (inicio == null) {
            throw new IllegalStateException("La BiCola está vacía");
        }
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
        tamaño--;
        return datoEliminado;
    }

    // =========================
    // UTILITARIOS
    // =========================
    public boolean isEmpty() {
        return tamaño == 0;
    }

    public int size() {
        return tamaño;
    }

    public void mostrar() {
        if (isEmpty()) {
            System.out.println("BiCola vacía");
            return;
        }
        Nodo<T> actual = inicio;
        System.out.print("[");
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getProximo() != null) {
                System.out.print(" ");
            }
            actual = actual.getProximo();
        }
        System.out.println("]");
    }

    // =========================
    // ORDENAMIENTO
    // =========================

    @SuppressWarnings("unchecked")
    private int cmpNatural(T a, T b) {
        if (!(a instanceof Comparable)) {
            throw new IllegalStateException("Ordenamiento natural requiere Comparable");
        }
        return ((Comparable<? super T>) a).compareTo(b);
    }

    private int cmp(T a, T b, Comparator<? super T> comparator) {
        return (comparator != null) ? comparator.compare(a, b) : cmpNatural(a, b);
    }

    /** Ordena ascendentemente por orden natural */
    public void sortAscending() {
        sortAscending(null);
    }

    /** Ordena ascendentemente usando un Comparator */
    public void sortAscending(Comparator<? super T> comparator) {
        if (inicio == null || inicio.getProximo() == null) return;
        boolean swapped;
        do {
            swapped = false;
            Nodo<T> actual = inicio;
            while (actual.getProximo() != null) {
                if (cmp(actual.getDato(), actual.getProximo().getDato(), comparator) > 0) {
                    // intercambio de valores
                    T tmp = actual.getDato();
                    actual.setDato(actual.getProximo().getDato());
                    actual.getProximo().setDato(tmp);
                    swapped = true;
                }
                actual = actual.getProximo();
            }
        } while (swapped);
    }

    /** Ordena descendentemente por orden natural */
    public void sortDescending() {
        sortDescending(null);
    }

    /** Ordena descendentemente usando un Comparator */
    public void sortDescending(Comparator<? super T> comparator) {
        if (comparator != null) {
            sortAscending(comparator.reversed());
        } else {
            sortAscending(new Comparator<T>() {
                @Override
                public int compare(T a, T b) {
                    return -cmpNatural(a, b);
                }
            });
        }
    }
}
