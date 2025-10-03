package ListasEnlazados.src.main.java.co.edu.uniquindio.Colas;

import java.util.Comparator;

public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    // =========================
    // Constructores
    // =========================
    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // =========================
    // Inserciones
    // =========================
    /** Inserta al final (operación estándar de cola). */
    public void encolar(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if (inicio == null){
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setProximo(nuevo);
            fin = nuevo;
        }
        tamaño++;
    }

    /** Inserta al inicio (no estándar, útil para ejercicios). */
    public void encolarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            nuevo.setProximo(inicio);
            inicio = nuevo;
        }
        tamaño++;
    }

    /**
     * Inserta en una posición 0..tamaño (0 = inicio, tamaño = final).
     * Mantiene enlaces correctamente.
     */
    public void insertarSegunPosicion(T dato, int posicion) {
        if (posicion < 0 || posicion > tamaño) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }
        if (posicion == 0) {
            encolarAlInicio(dato);
            return;
        }
        if (posicion == tamaño) {
            encolar(dato);
            return;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        Nodo<T> actual = inicio;
        for (int i = 0; i < posicion - 1; i++) {
            actual = actual.getProximo();
        }
        nuevo.setProximo(actual.getProximo());
        actual.setProximo(nuevo);
        tamaño++;
    }

    /**
     * Encola manteniendo orden natural ascendente (requiere Comparable si no se pasa Comparator).
     * Equivale a una "cola ordenada" (priority-like simple).
     */
    public void encolarOrdenado(T dato) {
        encolarOrdenado(dato, null);
    }

    public void encolarOrdenado(T dato, Comparator<? super T> comparator) {
        if (inicio == null) {
            encolar(dato);
            return;
        }
        // Si va al inicio
        if (cmp(dato, inicio.getDato(), comparator) <= 0) {
            encolarAlInicio(dato);
            return;
        }
        // Si va al final
        if (cmp(dato, fin.getDato(), comparator) >= 0) {
            encolar(dato);
            return;
        }
        // Buscar posición intermedia
        Nodo<T> actual = inicio;
        while (actual.getProximo() != null &&
                cmp(actual.getProximo().getDato(), dato, comparator) < 0) {
            actual = actual.getProximo();
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setProximo(actual.getProximo());
        actual.setProximo(nuevo);
        tamaño++;
    }

    // =========================
    // Eliminaciones
    // =========================
    /** Quita y retorna el frente. */
    public T desencolar() {
        if (inicio == null) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = inicio.getDato();
        inicio = inicio.getProximo();
        if (inicio == null) { // quedó vacía
            fin = null;
        }
        tamaño--;
        return dato;
    }

    /** Elimina la primera ocurrencia del dato (si existe). */
    public boolean eliminarPrimeraOcurrencia(T dato) {
        if (inicio == null) return false;
        if (inicio.getDato() == null ? dato == null : inicio.getDato().equals(dato)) {
            desencolar();
            return true;
        }
        Nodo<T> actual = inicio;
        while (actual.getProximo() != null) {
            if (actual.getProximo().getDato() == null ? dato == null
                    : actual.getProximo().getDato().equals(dato)) {
                if (actual.getProximo() == fin) {
                    fin = actual;
                }
                actual.setProximo(actual.getProximo().getProximo());
                tamaño--;
                return true;
            }
            actual = actual.getProximo();
        }
        return false;
    }

    // =========================
    // Búsqueda y utilitarios
    // =========================
    public T peek() {
        if (inicio == null) {
            throw new IllegalStateException("La cola está vacía");
        }
        return inicio.getDato();
    }

    public boolean isEmpty() { return tamaño == 0; }

    public int size() { return tamaño; }

    public void clear() {
        inicio = null;
        fin = null;
        tamaño = 0;
    }

    /** ¿Existe el dato en la cola? */
    public boolean buscar(T dato) {
        Nodo<T> a = inicio;
        while (a != null) {
            if (a.getDato() == null ? dato == null : a.getDato().equals(dato)) return true;
            a = a.getProximo();
        }
        return false;
    }

    /** Retorna la posición (0..tamaño-1) o -1 si no está. */
    public int localizar(T dato) {
        int idx = 0;
        Nodo<T> a = inicio;
        while (a != null) {
            if (a.getDato() == null ? dato == null : a.getDato().equals(dato)) return idx;
            a = a.getProximo();
            idx++;
        }
        return -1;
    }

    /** Muestra la cola de inicio a fin. */
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> a = inicio;
        while (a != null) {
            sb.append(a.getDato());
            a = a.getProximo();
            if (a != null) sb.append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    // =========================
    // Ordenamiento
    // =========================
    // Comparadores
    @SuppressWarnings("unchecked")
    private int cmpNatural(T a, T b) {
        if (!(a instanceof Comparable)) {
            throw new IllegalStateException("Orden natural requiere Comparable");
        }
        return ((Comparable<? super T>) a).compareTo(b);
    }

    private int cmp(T a, T b, Comparator<? super T> comparator) {
        return (comparator != null) ? comparator.compare(a, b) : cmpNatural(a, b);
    }

    /** Orden ascendente por orden natural (Bubble, intercambiando valores). */
    public void sortAscending() { sortAscending(null); }

    public void sortAscending(Comparator<? super T> comparator) {
        if (inicio == null || inicio.getProximo() == null) return;
        boolean swapped;
        do {
            swapped = false;
            Nodo<T> a = inicio;
            while (a.getProximo() != null) {
                if (cmp(a.getDato(), a.getProximo().getDato(), comparator) > 0) {
                    // swap de valores
                    T tmp = a.getDato();
                    a.setDato(a.getProximo().getDato());
                    a.getProximo().setDato(tmp);
                    swapped = true;
                }
                a = a.getProximo();
            }
        } while (swapped);
    }

    /** Orden descendente por orden natural (usa comparador invertido). */
    public void sortDescending() { sortDescending(null); }

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

    // =========================
    // Getters / Setters
    // =========================
    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
        // Recalcular fin si es necesario
        if (inicio == null) {
            fin = null;
            tamaño = 0;
        } else {
            Nodo<T> a = inicio;
            int count = 0;
            while (a.getProximo() != null) {
                a = a.getProximo();
                count++;
            }
            fin = a;
            tamaño = count + 1;
        }
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
        // No recomendado modificar tamaño directamente; mantener coherencia de enlaces
        this.tamaño = tamaño;
    }
}
