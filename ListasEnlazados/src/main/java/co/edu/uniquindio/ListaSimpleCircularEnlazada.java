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

    public void agregarPrimero(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if(primero == null){
            primero = nuevo;
            nuevo.setProximo(nuevo);
        }else{
            Nodo<T> auxiliar = primero;

            while (auxiliar.getProximo() != primero){
                auxiliar = auxiliar.getProximo();
            }
            nuevo.setProximo(primero);
            auxiliar.setProximo(nuevo);
            primero = nuevo;
        }
        tam++;
    }

    public void mostrar(){
        if (primero == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder mensaje = new StringBuilder("[");
        Nodo<T> actual = primero;
        while (actual != null) {
            mensaje.append(actual.getDato());
            if (actual.getProximo() != null) mensaje.append(" ");
            actual = actual.getProximo();
        }
        mensaje.append("]");
        System.out.println(mensaje.toString());
    }

    public void agregarUltimo (T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if(primero == null){
            primero = nuevo;
            nuevo.setProximo(nuevo);
        } else{
            Nodo<T> auxiliar = primero;
            while (auxiliar.getProximo() != primero) {
                auxiliar = auxiliar.getProximo();
            }
            auxiliar.setProximo(nuevo);
            nuevo.setProximo(primero);
        }
        tam++;
    }

    public void añadirSegunPosicion(T dato, int posicion){
        if(posicion<0||posicion>tam){
            throw new IndexOutOfBoundsException("Error en posicion");
        }
        Nodo<T> newNodo = new Nodo<>(dato);
        if(posicion==0){
            newNodo.setProximo(primero);
            primero = newNodo;
        } else {
            Nodo<T> actual = primero;
            for(int i =0; i<posicion-1;i++){
                actual = actual.getProximo();
            }
            newNodo.setProximo(actual.getProximo());
            actual.setProximo(newNodo);
        }
        tam++;
    }

    public int localizar(T datoBusqueda){
        Nodo<T> actual = primero;
        int indexBusqueda = 0;
        while(actual != null){
            if(actual.getDato().equals(datoBusqueda)){
                return indexBusqueda;
            }
            indexBusqueda++;
            actual = actual.getProximo();
        }
        return -1;
    }

    public boolean buscar (T datoBusqueda){
        Nodo<T> actual = primero;
        while(actual != null){
            if(actual.getDato().equals(datoBusqueda)){
                return true;
            }
            actual=actual.getProximo();
        }
        return false;
    }

    public void eliminar(T datoBusqueda){
        if (primero == null) return;
        Nodo<T> actual = primero;
        if (actual.getDato().equals(datoBusqueda)) {
            primero = actual.getProximo();
            tam--;
            return;
        } else {
            while (actual.getProximo() != null) {
                if (actual.getProximo().getDato().equals(datoBusqueda)) {
                    actual.setProximo(actual.getProximo().getProximo());
                    tam--;
                    return;
                }
                actual = actual.getProximo();
            }
        }
    }

    public void agregarNatural(T dato){
        if (!(dato instanceof Comparable)) {
            throw new IllegalArgumentException("T debe implementar Comparable");
        }
        @SuppressWarnings("unchecked")
        Comparable<T> compDato = (Comparable<T>) dato;

        Nodo<T> newNodo = new Nodo<>(dato);

        if (primero == null || compDato.compareTo(primero.getDato()) <= 0) {
            newNodo.setProximo(primero);
            primero = newNodo;
        } else {
            Nodo<T> actual = primero;
            while (actual.getProximo() != null &&
                    compDato.compareTo(actual.getProximo().getDato()) > 0) {
                actual = actual.getProximo();
            }
            newNodo.setProximo(actual.getProximo());
            actual.setProximo(newNodo);
        }
        tam++;
    }

    // ---------------------------
    // IMPLEMENTACIÓN DE ITERABLE
    // ---------------------------
    @Override
    public Iterator<T> iterator() {
        return new ListaIterator();
    }

    private class ListaIterator implements Iterator<T> {
        private Nodo<T> current = primero;

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

        /**
         * remove() no soportado en esta implementación.
         * Si quieres que funcione, puedo implementarlo (requiere llevar referencia al anterior).
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove no soportado");
        }
    }

    // Método auxiliar opcional para obtener el tamaño
    public int size() {
        return tam;
    }
}

