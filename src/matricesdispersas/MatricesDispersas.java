/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package matricesdispersas;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author andre
 */
public class MatricesDispersas {

    public static int Menu() {

        int opc = Integer.parseInt(JOptionPane.showInputDialog(
                "----- MENU MATRIZ DISPERSA -----\n"
                + "1. Insertar\n"
                + "2. Eliminar\n"
                + "3. Sumar filas\n"
                + "4. Sumar columnas\n"
                + "5. Mostrar tripleta\n"
                + "6. Sumar tripletas\n"
                + "7. Multiplicar tripletas\n"
                + "0. Salir\n\n"
                + "Seleccione una opcion:"
        ));

        return opc;
    }

    public static int ContarDatos(int Mat[][]) {
        int N = 0;
        for (int i = 0; i < Mat.length; i++) {
            for (int j = 0; j < Mat[0].length; j++) {

                if (Mat[i][j] != 0) {
                    N++;
                }
            }
        }
        return N;
    }

    public static void random(int[][] Mat2, int Tamf, int Tamc) {
        Random random = new Random();

        // Llenar matriz
        for (int i = 0; i < Tamf; i++) {
            for (int j = 0; j < Tamc; j++) {
                int p = random.nextInt(3);
                if (p == 0 || p == 2) {
                    Mat2[i][j] = 0;

                } else {
                    int h = random.nextInt(3);
                    if (h == 0) {
                        Mat2[i][j] = random.nextInt(99) * -1;

                    } else if (h == 1 || h == 2) {
                        Mat2[i][j] = random.nextInt(99);
                    }
                }
            }
        }

        // Imprimir matriz
        String c = "";
        String b;
        for (int i = 0; i < Tamf; i++) {
            for (int j = 0; j < Tamc; j++) {

                b = Mat2[i][j] + " ";
                c = c + b;

            }
            c = c + "\n";
        }
        JOptionPane.showMessageDialog(null, c);
    }

    public static void main(String[] args) {

        int opc = 0;

        int tamFilaa = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamanio de la Fila de la matriz A: "));
        int tamColumna = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamanio de la Columna de la matriz A: "));
        int Mat[][] = new int[tamFilaa][tamColumna];

        random(Mat, tamFilaa, tamColumna);
        int N = ContarDatos(Mat);
        Tripleta T1 = new Tripleta(N + 1);
        T1.LlenarTripleta(Mat);

        do {

            opc = Menu();

            switch (opc) {

                case 1:
                    System.out.println("Insertar");
                    int fila = Integer.parseInt(JOptionPane.showInputDialog("Digite la fila"));
                    int columna = Integer.parseInt(JOptionPane.showInputDialog("Digite la columna"));
                    int dato = Integer.parseInt(JOptionPane.showInputDialog("Digite la dato"));

                    T1.Insertar(fila, columna, dato);
                    break;

                case 2:
                    T1.Eliminar();
                    break;

                case 3:
                    T1.SumarFilas();

                    break;

                case 4:
                    T1.SumarColumnas();

                    break;

                case 5:
                    T1.Mostrar();
                    break;

                case 6:
                    int tamFila = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamanio de la Fila de la matriz B: "));
                    int tamColumn = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamanio de la Columna de la matriz B: "));

                    int Mat2[][] = new int[tamFila][tamColumn];
                    random(Mat2, tamFila, tamColumn);

                    Tripleta B = new Tripleta(ContarDatos(Mat2) + 1);
                    B.LlenarTripleta(Mat2);

                    Tripleta C = T1.SumandoTripletas(B);

                    //esto es para que si retorna el null del sumar me bote joption y vuelva al menu principal
                    if (C != null) {
                        C.Mostrar();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo realizar la suma");
                    }

                    break;

                case 7:
                    int tamFilas = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamanio de la Fila de la matriz B: "));
                    int tamColumns = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamanio de la Columna de la matriz B: "));

                    int Mat2s[][] = new int[tamFilas][tamColumns];
                    random(Mat2s, tamFilas, tamColumns);

                    Tripleta Bs = new Tripleta(ContarDatos(Mat2s) + 1);
                    Bs.LlenarTripleta(Mat2s);

                    Tripleta Cs = T1.MultiplicarTripletas(Bs);

                    //esto es para que si retorna el null del multo´plicar me bote joption y vuelva al menu principal
                    if (Cs != null) {
                        Cs.Mostrar();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo realizar la multiplicacion");
                    }

                    break;

                case 0:
                    System.out.println("Fin del programa");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opc != 0);
    }
}
