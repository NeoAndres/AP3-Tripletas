/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matricesdispersas;

import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class Tripleta {

    // Atributos
    private int Mtri[][];

    // Metodos
    public Tripleta(int N) {
        Mtri = new int[N][3];
    }

    public int getMtri(int k, int i) {
        return Mtri[k][i];
    }

    public void setMtri(int k, int i, int dato) {
        this.Mtri[k][i] = dato;
    }

    // Tamanio de la tripleta = N+1 * 3
    public void LlenarTripleta(int Mat[][]) {
        int k = 1;
        Mtri[0][0] = Mat.length;      // numero de filas
        Mtri[0][1] = Mat[0].length;   // numero de columnas
        Mtri[0][2] = 0;               // numero de datos

        for (int i = 0; i < Mat.length; i++) {
            for (int j = 0; j < Mat[0].length; j++) {
                if (Mat[i][j] != 0) {
                    //Ingresar  a  tripleta
                    Mtri[k][0] = i; // fila ----
                    Mtri[k][1] = j; // Columna ||||
                    Mtri[k][2] = Mat[i][j];  // Dato

                    k++;
                    Mtri[0][2]++;
                }
            }
        }
    }

    public void Mostrar() {
        String a;
        String p = "";
        for (int k = 0; k <= Mtri[0][2]; k++) { // menor o igua pq si no no muestra la ultima o la primera linea, enm este caso la ultiima linea
            a = ("| " + Mtri[k][0] + " | " + Mtri[k][1] + " | " + Mtri[k][2] + " |");
            p += a + "\n";
        }

        JOptionPane.showMessageDialog(null, p);
    }

    //para organizar, uso burbuja
    public void Reconstruir() {

        for (int i = 1; i < Mtri[0][2]; i++) {

            for (int j = i + 1; j <= Mtri[0][2]; j++) {

                if (Mtri[i][0] > Mtri[j][0]
                        || (Mtri[i][0] == Mtri[j][0] && Mtri[i][1] > Mtri[j][1])) {

                    int tempFila = Mtri[i][0];
                    int tempCol = Mtri[i][1];
                    int tempDato = Mtri[i][2];

                    Mtri[i][0] = Mtri[j][0];
                    Mtri[i][1] = Mtri[j][1];
                    Mtri[i][2] = Mtri[j][2];

                    Mtri[j][0] = tempFila;
                    Mtri[j][1] = tempCol;
                    Mtri[j][2] = tempDato;
                }
            }
        }
    }

    // insertar  A este le falta parar el programa si no funciona bien los datos digitados
    public void Insertar(int fila, int columna, int dato) {

        int k = 1;

        if (fila >= Mtri[0][0] || columna >= Mtri[0][1]) {
            System.out.println("Error pa");
        }

        if (fila < 0 || columna < 0) {
            System.out.println("No se puede pa");
        }

        if (dato == 0) {
            System.out.println("No se puede pa");
        }

        boolean existe = false; // para saber si existe ya o no la coordenada

        while (k <= Mtri[0][2]) {

            if (Mtri[k][0] == fila && Mtri[k][1] == columna) {
                existe = true;
                break;
            }

            k++;
        }

        if (!existe) {

            int aux[][] = new int[Mtri.length + 1][3];

            for (int i = 0; i <= Mtri[0][2]; i++) {
                for (int j = 0; j < 3; j++) {
                    aux[i][j] = Mtri[i][j];
                }
            }

            int pos = Mtri[0][2] + 1;

            aux[pos][0] = fila;
            aux[pos][1] = columna;
            aux[pos][2] = dato;

            aux[0][2] = Mtri[0][2] + 1;

            Mtri = aux;
        } else {

            int opc = Integer.parseInt(JOptionPane.showInputDialog("La posicion ya existe\n" + "1. Sumar\n" + "2. Reemplazar\n" + "3. Dejar igual"));

            if (opc == 1) {
                Mtri[k][2] = Mtri[k][2] + dato;
            } else if (opc == 2) {
                Mtri[k][2] = dato;
            }
        }
        Reconstruir();
    }

// eliminar  
    public void Eliminar() {
        //Eliminar por coordenada
        String opc = JOptionPane.showInputDialog("1. Eliminar por coord \n" + "2. Eliminar por dato");

        if (opc.equals("1")) {
            int fila = Integer.parseInt(JOptionPane.showInputDialog("Fila a eliminar"));
            int columna = Integer.parseInt(JOptionPane.showInputDialog("Columna a eliminar"));

            int pos = -1;

            for (int k = 1; k <= Mtri[0][2]; k++) {

                if (Mtri[k][0] == fila && Mtri[k][1] == columna) {
                    pos = k;
                    break;
                }

            }

            if (pos == -1) {
                System.out.println("No existe ese dato");
                return;
            }

            int aux[][] = new int[Mtri.length - 1][3];

            int j = 0;

            for (int i = 0; i <= Mtri[0][2]; i++) {

                if (i == pos) {
                    continue;
                }

                aux[j][0] = Mtri[i][0];
                aux[j][1] = Mtri[i][1];
                aux[j][2] = Mtri[i][2];

                j++;
            }

            aux[0][2] = Mtri[0][2] - 1;

            Mtri = aux;
            //eliminar por dato
        } else if (opc.equals("2")) {
            int dato = Integer.parseInt(JOptionPane.showInputDialog("Digite el dato a eliminar !"));

            int contador = 0;

            for (int i = 1; i <= Mtri[0][2]; i++) {
                if (Mtri[i][2] != dato) {
                    contador++;
                }
            }

            if (contador == Mtri[0][2]) {
                System.out.println("No existe ese dato");
                return;
            }

            int aux[][] = new int[contador + 1][3];

            aux[0][0] = Mtri[0][0];
            aux[0][1] = Mtri[0][1];

            int j = 1;

            for (int i = 1; i <= Mtri[0][2]; i++) {
                if (Mtri[i][2] != dato) {
                    aux[j][0] = Mtri[i][0];
                    aux[j][1] = Mtri[i][1];
                    aux[j][2] = Mtri[i][2];
                    j++;
                }
            }

            aux[0][2] = contador;

            Mtri = aux;
        }
    }

    // sumar filas      
    public int SumarFila(int fila) {

        int suma = 0;

        for (int k = 1; k <= Mtri[0][2]; k++) {

            if (Mtri[k][0] == fila) {
                suma += Mtri[k][2];
            }

        }

        return suma;
    }

// sumar columnas        
    public int SumarColumna(int columna) {

        int suma = 0;

        for (int k = 1; k <= Mtri[0][2]; k++) {

            if (Mtri[k][1] == columna) {
                suma += Mtri[k][2];
            }

        }

        return suma;
    }

    // codigo para suMAR TRIPLETAS
    public Tripleta SumandoTripletas(Tripleta B) {

        boolean sumable = false;

        int max = this.Mtri[0][2] + B.Mtri[0][2] + 1;

        if (Mtri[0][0] == B.Mtri[0][0] && Mtri[0][1] == B.Mtri[0][1]) {
            sumable = true;
        }
        if (sumable == true) {
            Tripleta C = new Tripleta(max);

            int i = 1, j = 1, k = 1;

            while (i <= this.Mtri[0][2] || j <= B.Mtri[0][2]) {

                if (i <= this.Mtri[0][2] && j <= B.Mtri[0][2]) {

                    int filaA = this.Mtri[i][0];
                    int colA = this.Mtri[i][1];

                    int filaB = B.Mtri[j][0];
                    int colB = B.Mtri[j][1];

                    if (filaA == filaB && colA == colB) {

                        int suma = this.Mtri[i][2] + B.Mtri[j][2];

                        if (suma != 0) {
                            C.Mtri[k][0] = filaA;
                            C.Mtri[k][1] = colA;
                            C.Mtri[k][2] = suma;
                            k++;
                        }

                        i++;
                        j++;

                    } else if (filaA < filaB || (filaA == filaB && colA < colB)) {

                        C.Mtri[k][0] = filaA;
                        C.Mtri[k][1] = colA;
                        C.Mtri[k][2] = this.Mtri[i][2];
                        i++;
                        k++;

                    } else if (filaA > filaB || (filaA == filaB && colA > colB)) {

                        C.Mtri[k][0] = filaB;
                        C.Mtri[k][1] = colB;
                        C.Mtri[k][2] = B.Mtri[j][2];
                        j++;
                        k++;

                    }

                } else {

                    if (i <= this.Mtri[0][2]) {

                        C.Mtri[k][0] = this.Mtri[i][0];
                        C.Mtri[k][1] = this.Mtri[i][1];
                        C.Mtri[k][2] = this.Mtri[i][2];
                        i++;
                        k++;

                    }

                    if (j <= B.Mtri[0][2]) {

                        C.Mtri[k][0] = B.Mtri[j][0];
                        C.Mtri[k][1] = B.Mtri[j][1];
                        C.Mtri[k][2] = B.Mtri[j][2];
                        j++;
                        k++;

                    }

                }
            }

            C.Mtri[0][0] = this.Mtri[0][0];
            C.Mtri[0][1] = this.Mtri[0][1];
            C.Mtri[0][2] = k - 1;

            return C;
        } else {
            JOptionPane.showMessageDialog(null, "Error, las matrices deben ser de iguales dimensiones");

            return null;
        }
    }

    // codigo para multiplicar tripletas
    public Tripleta MultiplicarTripletas(Tripleta B) {
        if (this.Mtri[0][1] != B.Mtri[0][0]) {
            JOptionPane.showMessageDialog(null, "No se pueden multiplicar");
            return null;
        }
        int max = this.Mtri[0][2] * B.Mtri[0][2] + 1;
        Tripleta C = new Tripleta(max);

        int k = 1;

        for (int i = 1; i <= this.Mtri[0][2]; i++) { // para A

            int filaA = this.Mtri[i][0];
            int colA = this.Mtri[i][1];
            int valA = this.Mtri[i][2];

            for (int j = 1; j <= B.Mtri[0][2]; j++) { // para B 

                int filaB = B.Mtri[j][0];
                int colB = B.Mtri[j][1];
                int valB = B.Mtri[j][2];

                if (colA == filaB) {
                    int resultado = valA * valB;

                    boolean existe = false;

                    for (int x = 1; x < k; x++) {
                        if (C.Mtri[x][0] == filaA && C.Mtri[x][1] == colB) {
                            C.Mtri[x][2] += resultado;
                            existe = true;
                            break; 
                        }
                    }
                    if (existe == false) {
                        C.Mtri[k][0] = filaA;
                        C.Mtri[k][1] = colB;
                        C.Mtri[k][2] = resultado;
                        k++;
                    }
                }
            }
        }
        
        // columnas filas y datos
        C.Mtri[0][0] = this.Mtri[0][0];
        C.Mtri[0][1] = B.Mtri[0][1];
        C.Mtri[0][2] = k - 1;
        return C;
    }
}
