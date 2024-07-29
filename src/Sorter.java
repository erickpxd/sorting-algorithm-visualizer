import java.util.Arrays;

public class Sorter {
    public enum Algorithm {
        BUBBLE_SORT,
        MERGE_SORT
    }

    private static final long DELAY = 500;  // Delay in milliseconds

    // Método de ordenação genérica para tipos comparáveis
    public static <T extends Comparable<T>> void sort(T[] array, Algorithm algorithm) {
        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(array);
                break;
            case MERGE_SORT:
                mergeSort(array, 0, array.length - 1);
                break;
        }
    }

    // Método de ordenação específico para caracteres
    public static void sort(Character[] array, Algorithm algorithm) {
        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(array);
                break;
            case MERGE_SORT:
                mergeSort(array, 0, array.length - 1);
                break;
        }
    }

    // Implementação do Bubble Sort com visualização
    private static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    // Troca os elementos
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
                printPyramid(array);
                sleep();
            }
            if (!swapped) break;
        }
    }

    // Implementação do Merge Sort com visualização
    private static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
            printPyramid(array);
            sleep();
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right) {
        T[] temp = Arrays.copyOf(array, array.length);
        int i = left, j = middle + 1, k = left;

        while (i <= middle && j <= right) {
            if (array[i].compareTo(array[j]) <= 0) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (int l = left; l <= right; l++) {
            array[l] = temp[l];
        }
    }

    // Método auxiliar para imprimir o array como uma pirâmide
    private static <T> void printPyramid(T[] array) {
        int max = Arrays.stream(array)
                .mapToInt(v -> (Integer) v)
                .max()
                .orElse(0);

        // Cria uma matriz com a altura igual ao maior valor e largura igual ao número de elementos
        char[][] grid = new char[max][array.length];
        for (int i = 0; i < max; i++) {
            Arrays.fill(grid[i], '⬜');  // Inicializa com blocos '⬜'
        }

        for (int i = 0; i < array.length; i++) {
            int value = (Integer) array[i];
            for (int j = 0; j < value; j++) {
                grid[max - j - 1][i] = '⬛';  // Define blocos '⬛' com base no valor
            }
        }

        for (char[] row : grid) {
            System.out.println(new String(row));
        }
        System.out.println();
    }

    // Método auxiliar para pausar a execução
    private static void sleep() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
