import java.util.Arrays;

/**
 * Classe que implementa algoritmos de ordenação com visualização em um painel.
 */
public class Sorter {

    /**
     * Enumeração que define os algoritmos de ordenação suportados.
     */
    public enum Algorithm {
        BUBBLE_SORT,
        MERGE_SORT,
        QUICK_SORT
    }

    // Atraso em milissegundos para visualização
    private static final long DELAY = 500;

    /**
     * Maneira genérica para ordenar um array de elementos comparáveis.
     *
     * @param array     O array a ser ordenado.
     * @param algorithm O algoritmo de ordenação a ser utilizado.
     * @param panel     O painel que será atualizado com a visualização.
     * @param crescente Verdadeiro se a ordenação deve ser crescente, falso se deve ser decrescente.
     * @param <T>      O tipo dos elementos do array, que deve implementar Comparable.
     */
    public static <T extends Comparable<T>> void sort(T[] array, Algorithm algorithm, PyramidPanel panel, boolean crescente) {
        if (array instanceof Integer[]) {
            sortIntegers((Integer[]) array, algorithm, panel, crescente);
        } else {
            sortGenerics(array, algorithm, panel, crescente);
        }
    }

    /**
     * Metodo específico para ordenar um array de inteiros.
     *
     * @param array     O array de inteiros a ser ordenado.
     * @param algorithm O algoritmo de ordenação a ser utilizado.
     * @param panel     O painel que será atualizado com a visualização.
     * @param crescente Verdadeiro se a ordenação deve ser crescente, falso se deve ser decrescente.
     */
    private static void sortIntegers(Integer[] array, Algorithm algorithm, PyramidPanel panel, boolean crescente) {
        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(array, panel, crescente);
                break;
            case MERGE_SORT:
                mergeSort(array, 0, array.length - 1, panel, crescente);
                break;
            case QUICK_SORT:
                quickSort(array, 0, array.length - 1, panel, crescente);
                break;
        }
    }

    /**
     * Metodo genérico para ordenar um array de elementos comparáveis.
     *
     * @param array     O array a ser ordenado.
     * @param algorithm O algoritmo de ordenação a ser utilizado.
     * @param panel     O painel que será atualizado com a visualização.
     * @param crescente Verdadeiro se a ordenação deve ser crescente, falso se deve ser decrescente.
     * @param <T>      O tipo dos elementos do array, que deve implementar Comparable.
     */
    private static <T extends Comparable<T>> void sortGenerics(T[] array, Algorithm algorithm, PyramidPanel panel, boolean crescente) {
        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(array, panel, crescente);
                break;
            case MERGE_SORT:
                mergeSort(array, 0, array.length - 1, panel, crescente);
                break;
            case QUICK_SORT:
                quickSort(array, 0, array.length - 1, panel, crescente);
                break;
        }
    }

    /**
     * Implementação do algoritmo Bubble Sort com visualização.
     *
     * @param array     O array a ser ordenado.
     * @param panel     O painel que será atualizado com a visualização.
     * @param crescente Verdadeiro se a ordenação deve ser crescente, falso se deve ser decrescente.
     * @param <T>      O tipo dos elementos do array, que deve implementar Comparable.
     */
    private static <T extends Comparable<T>> void bubbleSort(T[] array, PyramidPanel panel, boolean crescente) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if ((crescente && array[j].compareTo(array[j + 1]) > 0) || (!crescente && array[j].compareTo(array[j + 1]) < 0)) {
                    // Troca os elementos
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
                panel.updatePyramid(array);
                sleep();
            }
            if (!swapped) break;
        }
    }

    /**
     * Implementação do algoritmo Merge Sort com visualização.
     *
     * @param array O array a ser ordenado.
     * @param left  O índice esquerdo do subarray.
     * @param right O índice direito do subarray.
     * @param panel O painel que será atualizado com a visualização.
     * @param crescente Verdadeiro se a ordenação deve ser crescente, falso se deve ser decrescente.
     * @param <T> O tipo dos elementos do array, que deve implementar Comparable.
     */
    private static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right, PyramidPanel panel, boolean crescente) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle, panel, crescente);
            mergeSort(array, middle + 1, right, panel, crescente);
            merge(array, left, middle, right, crescente);
            panel.updatePyramid(array);
            sleep();
        }
    }

    /**
     * Metodo auxiliar para mesclar dois subarrays.
     *
     * @param array   O array a ser mesclado.
     * @param left    O índice esquerdo do primeiro subarray.
     * @param middle  O índice médio do subarray.
     * @param right   O índice direito do segundo subarray.
     * @param crescente Verdadeiro se a mesclagem deve ser crescente, falso se deve ser decrescente.
     * @param <T>     O tipo dos elementos do array, que deve implementar Comparable.
     */
    private static <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right, boolean crescente) {
        T[] temp = Arrays.copyOf(array, array.length);
        int i = left, j = middle + 1, k = left;

        while (i <= middle && j <= right) {
            if ((crescente && temp[i].compareTo(temp[j]) <= 0) || (!crescente && temp[i].compareTo(temp[j]) > 0)) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while (i <= middle) {
            array[k++] = temp[i++];
        }
    }

    /**
     * Implementação do algoritmo Quick Sort com visualização.
     *
     * @param array O array a ser ordenado.
     * @param low   O índice inferior do subarray.
     * @param high  O índice superior do subarray.
     * @param panel O painel que será atualizado com a visualização.
     * @param crescente Verdadeiro se a ordenação deve ser crescente, falso se deve ser decrescente.
     * @param <T> O tipo dos elementos do array, que deve implementar Comparable.
     */
    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high, PyramidPanel panel, boolean crescente) {
        if (low < high) {
            int pi = partition(array, low, high, crescente);
            quickSort(array, low, pi - 1, panel, crescente);
            quickSort(array, pi + 1, high, panel, crescente);
            panel.updatePyramid(array);
            sleep();
        }
    }

    /**
     * Metodo auxiliar para particionar o array para o Quick Sort.
     *
     * @param array O array a ser particionado.
     * @param low   O índice inferior do subarray.
     * @param high  O índice superior do subarray.
     * @param crescente Verdadeiro se a particionamento deve ser crescente, falso se deve ser decrescente.
     * @param <T> O tipo dos elementos do array, que deve implementar Comparable.
     * @return O índice da partição.
     */
    private static <T extends Comparable<T>> int partition(T[] array, int low, int high, boolean crescente) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if ((crescente && array[j].compareTo(pivot) <= 0) || (!crescente && array[j].compareTo(pivot) > 0)) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    /**
     * Metodo que causa um atraso no thread atual para visualização.
     */
    private static void sleep() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
