import java.util.Arrays;

public class InputUtils {
    public static boolean isTipoValido(String tipo) {
        return tipo.equals("n") || tipo.equals("c");
    }

    public static Integer[] processarNumeros(String[] valoresArray) {
        try {
            return Arrays.stream(valoresArray)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
        } catch (NumberFormatException e) {
            return null;
        }
    }

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
