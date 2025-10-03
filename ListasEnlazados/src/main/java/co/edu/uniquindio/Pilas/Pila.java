package ListasEnlazados.src.main.java.co.edu.uniquindio.Pilas;

import java.util.Comparator;
import ListasEnlazados.src.main.java.co.edu.uniquindio.Colas.Nodo;

public class Pila<T> {
    private Nodo<T> cima;
    private int capacidadMaxima;  // capacidad límite (si no deseas límite, usa Integer.MAX_VALUE)
    private int elementos;        // cantidad actual de elementos

    // =========================
    // Constructores
    // =========================
    public Pila(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser > 0");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.cima = null;
        this.elementos = 0;
    }

    // =========================
    // Operaciones básicas
    // =========================
    /** Inserta un elemento en la pila (push) */
    public void empilar(T dato) {
        if (isFull()) {
            throw new IllegalStateException("La pila está llena");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setProximo(cima);
        cima = nuevo;
        elementos++;
    }

    /** Elimina y retorna el elemento de la cima (pop) */
    public T desempilar() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T datoEliminado = cima.getDato();
        cima = cima.getProximo();
        elementos--;
        return datoEliminado;
    }

    /** Retorna el elemento en la cima sin eliminarlo (peek) */
    public T cima() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return cima.getDato();
    }

    /** ¿Está vacía? */
    public boolean isEmpty() {
        return elementos == 0;
    }

    /** ¿Está llena? */
    public boolean isFull() {
        return elementos == capacidadMaxima;
    }

    /** Cantidad de elementos */
    public int size() {
        return elementos;
    }

    /** Imprime la pila desde la cima hasta el fondo */
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("Pila vacía");
            return;
        }
        System.out.print("Cima -> ");
        Nodo<T> actual = cima;
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getProximo();
        }
        System.out.println("<- Fondo");
    }

    // =========================
    // ORDENAMIENTO
    // =========================
    // --- Helpers de comparación ---
    @SuppressWarnings("unchecked")
    private int cmpNatural(T a, T b) {
        if (!(a instanceof Comparable)) {
            throw new IllegalStateException(
                    "Ordenamiento natural requiere que T implemente Comparable"
            );
        }
        return ((Comparable<? super T>) a).compareTo(b);
    }

    private int cmp(T a, T b, Comparator<? super T> comparator) {
        return (comparator != null) ? comparator.compare(a, b) : cmpNatural(a, b);
    }

    // --- Ordenamiento con pila auxiliar (iterativo) ---
    /**
     * Ordena ascendentemente usando orden natural (menor queda en la CIMA al finalizar).
     * Requiere que T implemente Comparable.
     */
    public void sortAscending() {
        sortAscending(null);
    }

    /**
     * Ordena ascendentemente usando un Comparator (menor queda en la CIMA al finalizar).
     * Si comparator es null, usa orden natural.
     */
    public void sortAscending(Comparator<? super T> comparator) {
        Pila<T> aux = new Pila<>(Math.max(this.capacidadMaxima, this.elementos));

        while (!this.isEmpty()) {
            T tmp = this.desempilar();
            while (!aux.isEmpty() && cmp(aux.cima(), tmp, comparator) > 0) {
                this.empilar(aux.desempilar());
            }
            aux.empilar(tmp);
        }
        // Pasamos de aux -> this para que el menor quede en la cima de this
        while (!aux.isEmpty()) {
            this.empilar(aux.desempilar());
        }
    }

    /**
     * Ordena descendentemente usando orden natural (mayor queda en la CIMA).
     * Requiere que T implemente Comparable.
     */
    public void sortDescending() {
        sortDescending(null);
    }

    /**
     * Ordena descendentemente usando un Comparator (mayor queda en la CIMA).
     * Si comparator es null, usa orden natural.
     */
    public void sortDescending(Comparator<? super T> comparator) {
        // Podemos reutilizar sortAscending pasando comparador invertido
        Comparator<? super T> inv = (comparator != null)
                ? comparator.reversed()
                : new Comparator<T>() {
            @Override
            public int compare(T a, T b) {
                return -cmpNatural(a, b);
            }
        };
        sortAscending(inv);
    }

    // --- Ordenamiento recursivo (sin pila auxiliar visible) ---
    /** Ordena ascendentemente (natural) usando técnica recursiva. */
    public void sortAscendingRecursive() {
        if (!isEmpty()) {
            T top = desempilar();
            sortAscendingRecursive();
            insertSortedAscending(top);
        }
    }

    private void insertSortedAscending(T x) {
        if (isEmpty() || cmpNatural(cima(), x) >= 0) {
            empilar(x);
        } else {
            T top = desempilar();
            insertSortedAscending(x);
            empilar(top);
        }
    }

    /** Ordena descendentemente (natural) usando técnica recursiva. */
    public void sortDescendingRecursive() {
        if (!isEmpty()) {
            T top = desempilar();
            sortDescendingRecursive();
            insertSortedDescending(top);
        }
    }

    private void insertSortedDescending(T x) {
        if (isEmpty() || cmpNatural(cima(), x) <= 0) {
            empilar(x);
        } else {
            T top = desempilar();
            insertSortedDescending(x);
            empilar(top);
        }
    }

    // =========================
    // Getters / Setters básicos
    // =========================
    public Nodo<T> getCima() {
        return cima;
    }

    public void setCima(Nodo<T> cima) {
        this.cima = cima;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima < elementos) {
            throw new IllegalArgumentException(
                    "La nueva capacidad no puede ser menor que el número de elementos actuales"
            );
        }
        this.capacidadMaxima = capacidadMaxima;
    }
}
