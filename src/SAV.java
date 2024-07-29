import java.util.Arrays;  // Adiciona esta linha para importar a classe Arrays

public class SAV {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Número incorreto de parâmetros. Use: java SAV t=<tipo> v=<valores> a=<algoritmo>");
            return;
        }

        String tipo = args[0].split("=")[1];
        String valoresStr = args[1].split("=")[1];
        String algoritmoStr = args[2].split("=")[1];

        if (!InputUtils.isTipoValido(tipo)) {
            System.out.println("Tipo inválido. Use 'n' para numérico ou 'c' para caractere.");
            return;
        }

        Sorter.Algorithm algoritmo;
        try {
            algoritmo = Sorter.Algorithm.valueOf(algoritmoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Algoritmo inválido. Use 'BUBBLE_SORT' ou 'MERGE_SORT'.");
            return;
        }

        String[] valoresArray = valoresStr.split(",");

        if (tipo.equals("n")) {
            Integer[] numeros = InputUtils.processarNumeros(valoresArray);
            if (numeros != null) {
                Sorter.sort(numeros, algoritmo);
                System.out.println("Tipo: [Numérico]");
                System.out.println("Valores: " + Arrays.toString(numeros));
            } else {
                System.out.println("Valores inválidos para tipo numérico.");
            }
        } else {
            Character[] caracteres = InputUtils.processarCaracteres(valoresArray);
            if (caracteres != null) {
                Sorter.sort(caracteres, algoritmo);
                System.out.println("Tipo: [Caractere]");
                System.out.println("Valores: " + Arrays.toString(caracteres));
            } else {
                System.out.println("Valores inválidos para tipo caractere.");
            }
        }
    }
}
