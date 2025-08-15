package co.edu.uniquindio;
//Suma de los elementos de una matriz cuadrada
//Dada una matriz cuadrada de enteros, calcule la suma total de sus elementos usando Divide y Vencerás

public class SumaMatriz{

    public static int sumarMatriz(int[][] matriz, int filaInicio, int filaFinal, int columnaInicio, int columnaFinal) {
        // Caso base
        if (filaInicio == filaFinal && columnaInicio == columnaFinal) {
            return matriz[filaInicio][columnaInicio];
        }

        // Caso base para las filas de las submatrices
        if (filaInicio == filaFinal) {
            int suma = 0;
            for (int j = columnaInicio; j <= columnaFinal; j++) {
                suma += matriz[filaInicio][j];
            }
            return suma;
        }

        // Caso base para las columnas de las submatrices
        if (columnaInicio == columnaFinal) {
            int suma = 0;
            for (int i = filaInicio; i <= filaFinal; i++) {
                suma += matriz[i][columnaInicio];
            }
            return suma;
        }

        // División en submatrices
        int filaMedia = (filaInicio + filaFinal) / 2;
        int colMedia = (columnaInicio + columnaFinal) / 2;

        int submatriz1 = sumarMatriz(matriz, filaInicio, filaMedia, columnaInicio, colMedia);         // Arriba izquierza
        int submatriz2= sumarMatriz(matriz, filaInicio, filaMedia, colMedia + 1, columnaFinal);        // Arriba derecha
        int submatriz3= sumarMatriz(matriz, filaMedia + 1, filaFinal, columnaInicio, colMedia);        // Abajo izquierda
        int submatriz4= sumarMatriz(matriz, filaMedia + 1, filaFinal, colMedia + 1, columnaFinal);       // Abajo derrecha

        //Se unen los resultados
        return submatriz1 + submatriz2 + submatriz3 + submatriz4;
    }

    //Se crea la matriz
    public static void main(String[] args) {
        int[][] matriz = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int n = matriz.length;
        int sumaTotal = sumarMatriz(matriz, 0, n - 1, 0, n - 1);

        //Se imprime el resultadp
        System.out.println("La suma total es: " + sumaTotal);
    }
}