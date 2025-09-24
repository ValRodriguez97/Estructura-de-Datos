package Recursividad;

public class BusquedaBinaria {

    //Método iterativo
    public static int busquedaBinariaIterativa(int[] arr, int x){
        int inicio = 0, fin = arr.length-1;

        while (inicio <= fin){
            int medio = (inicio + fin) /2;

            //Caso en el que se encuentra el valor
            if(arr[medio] == x) {
                return medio;
            }

            // Si el valor esta en la parte derecha
            if(arr[medio] < x){
                inicio = medio +1;
            }

            //Si el valor esta en la parte izquierda
            else{
                fin = medio -1;
            }
        }

        //Si no se encuentra
        return -1;
    }
}
