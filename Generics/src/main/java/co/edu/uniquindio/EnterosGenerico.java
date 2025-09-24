package co.edu.uniquindio;

public class EnterosGenerico<E> {
    private E a;
    private E b;

    public EnterosGenerico(E a, E b) {
        this.a = a;
        this.b = b;
    }

    public EnterosGenerico<E> swap (){
        return new EnterosGenerico<E>(a, b);
    }

    public E getA() {
        return a;
    }

    public void setA(E a) {
        this.a = a;
    }

    public E getB() {
        return b;
    }

    public void setB(E b) {
        this.b = b;
    }
}
