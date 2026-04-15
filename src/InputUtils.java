import java.util.Arrays;

/**
 * A classe InputUtils fornece métodos utilitários para validar tipos de entrada e processar arrays de valores.
 */
public class InputUtils {

    /**
     * Verifica se o tipo fornecido é válido.
     *
     * @param tipo O tipo a ser verificado. Pode ser "n" para numérico ou "c" para caractere.
     * @return true se o tipo for válido, false caso contrário.
     */
    public static boolean isTipoValido(String tipo) {
        return tipo.equals("n") || tipo.equals("c");
    }

    /**
     * Processa um array de strings e converte os valores para um array de inteiros.
     *
     * @param valoresArray O array de strings a ser processado.
     * @return Um array de inteiros se a conversão for bem-sucedida; null caso contrário.
     */
    public static Integer[] processarNumeros(String[] valoresArray) {
        try {
            return Arrays.stream(valoresArray)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Processa um array de strings e converte os valores para um array de caracteres.
     *
     * @param valoresArray O array de strings a ser processado.
     * @return Um array de caracteres se a conversão for bem-sucedida; null caso contrário.
     */
    public static Character[] processarCaracteres(String[] valoresArray) {
        Character[] caracteres = new Character[valoresArray.length];
        for (int i = 0; i < valoresArray.length; i++) {
            String valor = valoresArray[i];
            if (valor.length() != 1) {
                return null;
            }
            caracteres[i] = valor.charAt(0);
        }
        return caracteres;
    }
}
