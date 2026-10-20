import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arreglo = null;
        boolean ordenado = false;
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    arreglo = crearArreglo();
                    ordenado = false;
                    break;

                case 2:
                    if (arreglo == null) {
                        System.out.println("\n[!] Primero debe generar el arreglo (Opción 1).\n");
                    } else {
                        System.out.println("\n--- Ordenando con QuickSort ---");
                        ordenarQuickSort(arreglo, 0, arreglo.length - 1);
                        ordenado = true;
                        
                        System.out.println("Arreglo Ordenado:");
                        imprimirArreglo(arreglo);
                        System.out.println();
                    }
                    break;

                case 3:
                    if (arreglo == null) {
                        System.out.println("\n[!] Primero debe generar el arreglo (Opción 1).\n");
                    } else if (!ordenado) {
                        System.out.println("\n[!] El arreglo debe estar ordenado antes de realizar la Búsqueda Binaria. Ordénelo en la Opción 2.\n");
                    } else {
                        System.out.print("\nIngrese el número a buscar: ");
                        int valorBuscado = scanner.nextInt();
                        int posicion = buscar(arreglo, valorBuscado, 0, arreglo.length - 1);

                        if (posicion != -1) {
                            System.out.println("El elemento " + valorBuscado + " se encuentra en el índice " + posicion + ".");
                            if (posicion < arreglo.length / 2) {
                                System.out.println("Ubicación: Encontrado en el lado IZQUIERDO del arreglo.");
                            } else {
                                System.out.println("Ubicación: Encontrado en el lado DERECHO del arreglo.");
                            }
                        } else {
                            System.out.println("El elemento " + valorBuscado + " no fue encontrado en el arreglo.");
                        }
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.\n");
            }
        } while (opcion != 4);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("=== Práctica de Ordenamiento y Búsqueda de Datos ===");
        System.out.println("1. Generar nuevo arreglo aleatorio");
        System.out.println("2. Ordenar arreglo con QuickSort");
        System.out.println("3. Buscar elemento (Búsqueda Binaria)");
        System.out.println("4. Salir");
    }

    public static int[] crearArreglo() {
        int[] arreglo = new int[20];
        System.out.println("\nLista Original:");
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = (int) (Math.random() * 50 + 1);
            System.out.print(arreglo[i] + " ");
        }
        System.out.println("\n");
        return arreglo;
    }

    public static void ordenarQuickSort(int[] arreglo, int inferior, int superior) {
        int i, j, central;
        double pivote;
        central = (inferior + superior) / 2;
        pivote = arreglo[central];
        i = inferior;
        j = superior;

        System.out.println("Límites analizados -> Inferior: " + i + " | Superior: " + j + " | Pivote: " + pivote);

        do {
            while (arreglo[i] < pivote) i++;
            while (arreglo[j] > pivote) j--;
            if (i <= j) {
                intercambiar(arreglo, i, j);
                i++;
                j--;
            }
        } while (i <= j);

        if (inferior < j) {
            ordenarQuickSort(arreglo, inferior, j);
        }
        if (i < superior) {
            ordenarQuickSort(arreglo, i, superior);
        }
    }

    public static void intercambiar(int[] arreglo, int i, int j) {
        int auxiliar = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = auxiliar;
    }

    public static int buscar(int[] arreglo, int valorBuscado, int inferior, int superior) {
        if (inferior > superior) {
            return -1;
        } else {
            int central = (inferior + superior) / 2;
            if (arreglo[central] == valorBuscado) {
                return central;
            } else if (arreglo[central] < valorBuscado) {
                return buscar(arreglo, valorBuscado, central + 1, superior);
            } else {
                return buscar(arreglo, valorBuscado, inferior, central - 1);
            }
        }
    }

    public static void imprimirArreglo(int[] arreglo) {
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}