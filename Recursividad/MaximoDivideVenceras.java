package Recursividad;

public class MaximoDivideVenceras {
    public static int encontrarMaximo(int[] arreglo, int inicio, int fin){
        if (inicio == fin){
            return arreglo[inicio];
        }

        //Dividir el arreglo en dos mitades
        int medio = (inicio + fin) /2;

        //Encontrar el máimo en la mitad izquierda y derecha
        int maxIzq = encontrarMaximo(arreglo, inicio, medio);
        int maxDer = encontrarMaximo(arreglo, medio +1, fin);

        //Combinar ambos resultados y compararlos
        return Math.max(maxIzq, maxDer);
    }

    public static void main(String[] args) {
        int [] arreglo = {3, 7, 2, 9, 12, 5, 10, 8};

        int maximo = encontrarMaximo(arreglo, 0, arreglo.length-1);
        System.out.println(maximo);
    }
}
