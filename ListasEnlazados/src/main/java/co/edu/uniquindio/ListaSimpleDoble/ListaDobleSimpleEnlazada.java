package ListasEnlazados.src.main.java.co.edu.uniquindio.ListaSimpleDoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDobleSimpleEnlazada<T extends Comparable<? super T>> implements Iterable<T> {

    private NodoDoble<T> primero;
    private int tam;

    public ListaDobleSimpleEnlazada() {
        this.primero = null;
        this.tam = 0;
    }

    // =========================
    // Inserciones
    // =========================

    /** Inserta al inicio */
    public void agregarPrimero(T dato){
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (primero == null){
            primero = nuevo;
        } else {
            nuevo.setProximo(primero);
            primero.setAnterior(nuevo);
            primero = nuevo;
        }
        tam++;
    }

    /** Inserta al final */
    public void agregarUltimo (T dato){
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoDoble<T> actual = primero;
            while (actual.getProximo() != null){
                actual = actual.getProximo();
            }
            actual.setProximo(nuevo);
            nuevo.setAnterior(actual);
        }
        tam++;
    }

    /** Inserta en posición (0..tam). Mantiene enlaces anterior/proximo. */
    public void añadirSegunPosicion(T dato, int posicion){
        if (posicion < 0 || posicion > tam){
            throw new IndexOutOfBoundsException("Error en posicion: " + posicion);
        }
        if (posicion == 0){
            agregarPrimero(dato);
            return;
        }
        if (posicion == tam){
            agregarUltimo(dato);
            return;
        }
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        NodoDoble<T> actual = primero;
        for (int i = 0; i < posicion - 1; i++){
            actual = actual.getProximo();
        }
        NodoDoble<T> siguiente = actual.getProximo();
        nuevo.setProximo(siguiente);
        nuevo.setAnterior(actual);
        actual.setProximo(nuevo);
        if (siguiente != null) {
            siguiente.setAnterior(nuevo);
        }
        tam++;
    }

    /** Inserta manteniendo orden natural ascendente */
    public void agregarNatural(T dato){
        if (primero == null) {
            agregarPrimero(dato);
            return;
        }
        // Si va al inicio
        if (dato.compareTo(primero.getDato()) <= 0) {
            agregarPrimero(dato);
            return;
        }
        NodoDoble<T> actual = primero;
        while (actual.getProximo() != null &&
                actual.getProximo().getDato().compareTo(dato) < 0) {
            actual = actual.getProximo();
        }
        // insertar después de 'actual'
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        NodoDoble<T> sig = actual.getProximo();
        nuevo.setProximo(sig);
        nuevo.setAnterior(actual);
        actual.setProximo(nuevo);
        if (sig != null) sig.setAnterior(nuevo);
        tam++;
    }

    // =========================
    // Eliminación / Búsqueda
    // =========================

    public void eliminar(T datoBusqueda){
        if (primero == null) return;

        NodoDoble<T> actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(datoBusqueda)) {
                NodoDoble<T> ant = actual.getAnterior();
                NodoDoble<T> sig = actual.getProximo();
                if (ant == null) { // era la cabeza
                    primero = sig;
                    if (sig != null) sig.setAnterior(null);
                } else {
                    ant.setProximo(sig);
                    if (sig != null) sig.setAnterior(ant);
                }
                tam--;
                return;
            }
            actual = actual.getProximo();
        }
    }

    public boolean buscar (T datoBusqueda){
        NodoDoble<T> actual = primero;
        while (actual != null){
            if (actual.getDato().equals(datoBusqueda)) return true;
            actual = actual.getProximo();
        }
        return false;
    }

    public int localizar(T datoBusqueda){
        NodoDoble<T> actual = primero;
        int index = 0;
        while (actual != null){
            if (actual.getDato().equals(datoBusqueda)) return index;
            index++;
            actual = actual.getProximo();
        }
        return -1;
    }

    // =========================
    // Mostrar / Tamaño
    // =========================
    public void mostrar(){
        if (primero == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        NodoDoble<T> actual = primero;
        while (actual != null) {
            sb.append(actual.getDato());
            actual = actual.getProximo();
            if (actual != null) sb.append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public int size() {
        return tam;
    }

    // =========================
    // ORDENAMIENTOS
    // =========================

    /** Método unificado: usa merge sort por eficiencia en listas */
    public void sort() {
        mergeSort();
    }

    /** Bubble Sort: intercambia valores (simple) */
    public void bubbleSort() {
        if (primero == null || primero.getProximo() == null) return;

        boolean swapped;
        do {
            swapped = false;
            NodoDoble<T> a = primero;
            while (a.getProximo() != null) {
                if (a.getDato().compareTo(a.getProximo().getDato()) > 0) {
                    T tmp = a.getDato();
                    a.setDato(a.getProximo().getDato());
                    a.getProximo().setDato(tmp);
                    swapped = true;
                }
                a = a.getProximo();
            }
        } while (swapped);
    }

    /** Selection Sort: intercambia valores */
    public void selectionSort() {
        for (NodoDoble<T> i = primero; i != null; i = i.getProximo()) {
            NodoDoble<T> min = i;
            for (NodoDoble<T> j = i.getProximo(); j != null; j = j.getProximo()) {
                if (j.getDato().compareTo(min.getDato()) < 0) {
                    min = j;
                }
            }
            T tmp = i.getDato();
            i.setDato(min.getDato());
            min.setDato(tmp);
        }
    }

    /** Insertion Sort: reordena ENLACES (sin copiar datos) */
    public void insertionSort() {
        if (primero == null || primero.getProximo() == null) return;

        NodoDoble<T> sortedHead = null;
        NodoDoble<T> actual = primero;

        while (actual != null) {
            NodoDoble<T> siguiente = actual.getProximo();

            // desconectar actual
            actual.setAnterior(null);
            actual.setProximo(null);

            // insertar 'actual' en lista 'sortedHead'
            if (sortedHead == null || actual.getDato().compareTo(sortedHead.getDato()) <= 0) {
                if (sortedHead != null) {
                    sortedHead.setAnterior(actual);
                }
                actual.setProximo(sortedHead);
                sortedHead = actual;
            } else {
                NodoDoble<T> t = sortedHead;
                while (t.getProximo() != null && t.getProximo().getDato().compareTo(actual.getDato()) < 0) {
                    t = t.getProximo();
                }
                NodoDoble<T> tNext = t.getProximo();
                t.setProximo(actual);
                actual.setAnterior(t);
                actual.setProximo(tNext);
                if (tNext != null) tNext.setAnterior(actual);
            }

            actual = siguiente;
        }

        primero = sortedHead;
    }

    /** Merge Sort: reordena ENLACES (estable, O(n log n)) */
    public void mergeSort() {
        primero = mergeSortRec(primero);
    }

    private NodoDoble<T> mergeSortRec(NodoDoble<T> head) {
        if (head == null || head.getProximo() == null) return head;

        NodoDoble<T> mid = split(head);
        NodoDoble<T> left = mergeSortRec(head);
        NodoDoble<T> right = mergeSortRec(mid);

        return merge(left, right);
    }

    /** Parte la lista en dos mitades y retorna la cabeza de la segunda mitad. */
    private NodoDoble<T> split(NodoDoble<T> head) {
        NodoDoble<T> slow = head, fast = head;
        while (fast.getProximo() != null && fast.getProximo().getProximo() != null) {
            slow = slow.getProximo();
            fast = fast.getProximo().getProximo();
        }
        NodoDoble<T> second = slow.getProximo();
        slow.setProximo(null);
        if (second != null) second.setAnterior(null);
        return second;
    }

    /** Mezcla dos listas doblemente enlazadas ya ordenadas. */
    private NodoDoble<T> merge(NodoDoble<T> a, NodoDoble<T> b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.getDato().compareTo(b.getDato()) <= 0) {
            a.setProximo(merge(a.getProximo(), b));
            if (a.getProximo() != null) a.getProximo().setAnterior(a);
            a.setAnterior(null);
            return a;
        } else {
            b.setProximo(merge(a, b.getProximo()));
            if (b.getProximo() != null) b.getProximo().setAnterior(b);
            b.setAnterior(null);
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
        private NodoDoble<T> current = primero;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T dato = current.getDato();
            current = current.getProximo();
            return dato;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove no soportado");
        }
    }
}
