package ListasEnlazados.src.main.java.co.edu.uniquindio;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimpleCircularEnlazada<T extends Comparable<? super T>> implements Iterable<T> {

    private Nodo<T> primero;
    private int tam;

    public ListaSimpleCircularEnlazada() {
        primero = null;
        tam = 0;
    }

    // =========================
    // Helpers de circularidad
    // =========================
    private boolean esVacia() { return primero == null; }

    /** Retorna el último nodo (aquel cuyo next apunta a 'primero'). */
    private Nodo<T> getTail() {
        if (primero == null) return null;
        Nodo<T> a = primero;
        while (a.getProximo() != primero) a = a.getProximo();
        return a;
    }

    /** Rompe la circularidad para trabajar como lista lineal: tail.next = null */
    private void romperCirculo() {
        if (primero == null) return;
        Nodo<T> tail = getTail();
        if (tail != null) tail.setProximo(null);
    }

    /** Restaura la circularidad: último.next = primero (asumiendo lista lineal). */
    private void restaurarCirculo() {
        if (primero == null) return;
        Nodo<T> a = primero;
        while (a.getProximo() != null) a = a.getProximo();
        a.setProximo(primero);
    }

    /** Middle para lista lineal (Floyd tortuga-liebre) */
    private Nodo<T> getMiddleLineal(Nodo<T> h) {
        if (h == null) return null;
        Nodo<T> lento = h, rapido = h;
        while (rapido.getProximo() != null && rapido.getProximo().getProximo() != null) {
            lento = lento.getProximo();
            rapido = rapido.getProximo().getProximo();
        }
        return lento;
    }

    // =========================
    // Operaciones básicas
    // =========================
    public void agregarPrimero(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if (primero == null){
            primero = nuevo;
            nuevo.setProximo(nuevo); // circular
        } else {
            Nodo<T> tail = getTail();
            nuevo.setProximo(primero);
            tail.setProximo(nuevo);
            primero = nuevo;
        }
        tam++;
    }

    public void agregarUltimo (T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if (primero == null){
            primero = nuevo;
            nuevo.setProximo(nuevo);
        } else {
            Nodo<T> tail = getTail();
            tail.setProximo(nuevo);
            nuevo.setProximo(primero);
        }
        tam++;
    }

    /** Insertar en posición 0..tam manteniendo circularidad. */
    public void añadirSegunPosicion(T dato, int posicion){
        if (posicion < 0 || posicion > tam) {
            throw new IndexOutOfBoundsException("Error en posicion");
        }
        Nodo<T> nuevo = new Nodo<>(dato);

        if (primero == null) { // lista vacía
            primero = nuevo;
            nuevo.setProximo(nuevo);
        } else if (posicion == 0) { // insertar antes de 'primero'
            Nodo<T> tail = getTail();
            nuevo.setProximo(primero);
            tail.setProximo(nuevo);
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;
            for (int i = 0; i < posicion - 1; i++) {
                actual = actual.getProximo();
            }
            nuevo.setProximo(actual.getProximo());
            actual.setProximo(nuevo);
        }
        tam++;
    }

    public boolean buscar (T datoBusqueda){
        if (primero == null) return false;
        Nodo<T> actual = primero;
        do {
            if (actual.getDato().equals(datoBusqueda)) return true;
            actual = actual.getProximo();
        } while (actual != primero);
        return false;
    }

    /** Retorna la posición (0..tam-1) o -1 si no está. */
    public int localizar(T datoBusqueda){
        if (primero == null) return -1;
        Nodo<T> actual = primero;
        int index = 0;
        do {
            if (actual.getDato().equals(datoBusqueda)) return index;
            index++;
            actual = actual.getProximo();
        } while (actual != primero);
        return -1;
    }

    /** Elimina la primera ocurrencia del dato (si existe), manteniendo circularidad. */
    public void eliminar(T datoBusqueda){
        if (primero == null) return;

        Nodo<T> actual = primero;
        Nodo<T> anterior = getTail(); // previo al primero en circular

        // Recorremos una vuelta completa como máximo
        do {
            if (actual.getDato().equals(datoBusqueda)) {
                if (actual == primero) {
                    if (tam == 1) { // único nodo
                        primero = null;
                    } else {
                        // mover cabeza al siguiente
                        primero = primero.getProximo();
                        anterior.setProximo(primero);
                    }
                } else {
                    // saltar el nodo actual
                    anterior.setProximo(actual.getProximo());
                }
                tam--;
                return;
            }
            anterior = actual;
            actual = actual.getProximo();
        } while (actual != primero);
    }

    /** Inserta manteniendo orden ascendente natural. */
    public void agregarNatural(T dato){
        if (primero == null) {
            agregarPrimero(dato);
            return;
        }
        Nodo<T> nuevo = new Nodo<>(dato);

        // Caso: dato va antes del primero (nueva cabeza)
        if (dato.compareTo(primero.getDato()) <= 0) {
            Nodo<T> tail = getTail();
            nuevo.setProximo(primero);
            tail.setProximo(nuevo);
            primero = nuevo;
            tam++;
            return;
        }

        // Buscar lugar de inserción
        Nodo<T> actual = primero;
        while (actual.getProximo() != primero &&
                actual.getProximo().getDato().compareTo(dato) < 0) {
            actual = actual.getProximo();
        }
        nuevo.setProximo(actual.getProximo());
        actual.setProximo(nuevo);
        tam++;
    }

    public void mostrar(){
        if (primero == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = primero;
        do {
            sb.append(actual.getDato());
            actual = actual.getProximo();
            if (actual != primero) sb.append(" ");
        } while (actual != primero);
        sb.append("]");
        System.out.println(sb.toString());
    }

    // =========================
    // ORDENAMIENTOS
    // =========================

    /** Opción unificada: usa el más eficiente para listas. */
    public void sort() {
        mergeSort();
    }

    /** Bubble Sort (intercambia valores, no rompe el anillo) */
    public void bubbleSort() {
        if (tam < 2) return;

        boolean swapped;
        do {
            swapped = false;
            Nodo<T> actual = primero;
            for (int i = 0; i < tam; i++) {
                Nodo<T> sig = actual.getProximo();
                if (actual.getDato().compareTo(sig.getDato()) > 0) {
                    T tmp = actual.getDato();
                    actual.setDato(sig.getDato());
                    sig.setDato(tmp);
                    swapped = true;
                }
                actual = sig;
            }
        } while (swapped);
    }

    /** Selection Sort (intercambia valores, no rompe el anillo) */
    public void selectionSort() {
        if (tam < 2) return;

        Nodo<T> i = primero;
        for (int k = 0; k < tam; k++) {
            Nodo<T> min = i;
            Nodo<T> j = i.getProximo();
            for (int r = 0; r < tam - 1; r++) {
                if (j.getDato().compareTo(min.getDato()) < 0) min = j;
                j = j.getProximo();
            }
            // swap valores i <-> min
            T tmp = i.getDato();
            i.setDato(min.getDato());
            min.setDato(tmp);

            i = i.getProximo();
        }
    }

    /** Insertion Sort (reordena ENLACES; rompe y restaura circularidad para simplicidad y seguridad) */
    public void insertionSort() {
        if (tam < 2) return;

        romperCirculo(); // trabajar como lista lineal

        Nodo<T> sorted = null;
        Nodo<T> actual = primero;

        while (actual != null) {
            Nodo<T> siguiente = actual.getProximo();
            if (sorted == null || sorted.getDato().compareTo(actual.getDato()) >= 0) {
                actual.setProximo(sorted);
                sorted = actual;
            } else {
                Nodo<T> temp = sorted;
                while (temp.getProximo() != null &&
                        temp.getProximo().getDato().compareTo(actual.getDato()) < 0) {
                    temp = temp.getProximo();
                }
                actual.setProximo(temp.getProximo());
                temp.setProximo(actual);
            }
            actual = siguiente;
        }

        primero = sorted;
        restaurarCirculo(); // volver a circular
    }

    /** Merge Sort (reordena ENLACES; rompe y restaura circularidad) */
    public void mergeSort() {
        if (tam < 2) return;
        romperCirculo();
        primero = mergeSortRec(primero);
        restaurarCirculo();
    }

    private Nodo<T> mergeSortRec(Nodo<T> h) {
        if (h == null || h.getProximo() == null) return h;

        Nodo<T> medio = getMiddleLineal(h);
        Nodo<T> siguienteDelMedio = medio.getProximo();
        medio.setProximo(null);

        Nodo<T> izquierda = mergeSortRec(h);
        Nodo<T> derecha = mergeSortRec(siguienteDelMedio);

        return mergeLineal(izquierda, derecha);
    }

    private Nodo<T> mergeLineal(Nodo<T> a, Nodo<T> b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.getDato().compareTo(b.getDato()) <= 0) {
            a.setProximo(mergeLineal(a.getProximo(), b));
            return a;
        } else {
            b.setProximo(mergeLineal(a, b.getProximo()));
            return b;
        }
    }

    // =========================
    // Iterable
    // =========================
    @Override
    public Iterator<T> iterator() {
        return new ListaIterator();
    }

    private class ListaIterator implements Iterator<T> {
        private Nodo<T> current = primero;
        private boolean firstReturned = false;

        @Override
        public boolean hasNext() {
            if (primero == null) return false;
            if (current == null) return false;
            return !firstReturned || current != primero;
        }

        @Override
        public T next() {
            if (primero == null || current == null || (firstReturned && current == primero)) {
                throw new NoSuchElementException();
            }
            T dato = current.getDato();
            current = current.getProximo();
            firstReturned = true;
            return dato;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove no soportado");
        }
    }

    // =========================
    // Utilidades
    // =========================
    public int size() {
        return tam;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo<T> primero) {
        this.primero = primero;
    }
}
