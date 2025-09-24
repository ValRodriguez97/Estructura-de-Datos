package co.edu.uniquindio;

public class CajaGenerica <E>{
    private E elemento2;
    private E elemento1;

    public CajaGenerica(E elemento2, E elemento1) {
        this.elemento2 = elemento2;
        this.elemento1 = elemento1;
    }

    public E getElemento2() {
        return elemento2;
    }

    public void setElemento2(E elemento2) {
        this.elemento2 = elemento2;
    }

    public E getElemento1() {
        return elemento1;
    }

    public void setElemento1(E elemento1) {
        this.elemento1 = elemento1;
    }

    //<> Operador diamante


}
