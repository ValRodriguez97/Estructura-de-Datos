package co.edu.uniquindio;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //E significa generico Element
    //Key parametro de clave de un mapa
    //Value parametro de valor de un mapa
    //N representación de numeros

    /**
     * T Type primer parametro de tipo generico
     * S Type segundo parametro de ttipo generico
     * U Tercer parametro de tipo generico
     * V Type Cuarto parametro de tipo generico
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList lista = new ArrayList();
        ArrayList<String> lista2 = new ArrayList<String>();

        lista.add(22);
        lista.add("Hola mundo");
        //lista.add(new ComparablePerson(12) );
        String cadena = (String) lista.get(0);

        lista2.add("B");

        System.out.println(cadena);
    }
}