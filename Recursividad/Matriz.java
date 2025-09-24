package Recursividad;

public class Matriz {
    public static void recorrerMatriz(int[] [] matriz, int fila, int columna){
        if( fila == matriz.length){
            return;
        }
        if(columna == matriz[fila].length){
            recorrerMatriz(matriz, fila +1, 0);
            return;
        }
        System.out.print(matriz[fila][columna]);
        recorrerMatriz(matriz, fila, columna + 1);
    }

    public static void main(String [] args){
        int[][] matriz1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        recorrerMatriz(matriz1, 0, 0);
    }

}
