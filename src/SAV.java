import javax.swing.*;
import java.awt.*;

/**
 * A classe SAV é a classe principal que inicializa a aplicação e gerencia a entrada de parâmetros,
 * a configuração da interface gráfica e a execução do algoritmo de ordenação.
 */
public class SAV {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Número incorreto de parâmetros. Use: java SAV t=<tipo> v=<valores> a=<algoritmo> o=<ordem>");
            return;
        }

        String tipo = args[0].split("=")[1];
        String valoresStr = args[1].split("=")[1];
        String algoritmoStr = args[2].split("=")[1];
        String ordem = args[3].split("=")[1];

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

        boolean crescente = ordem.equalsIgnoreCase("c");

        String[] valoresArray = valoresStr.split(",");

        JFrame frame = new JFrame("Pyramid Sort Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Painel para exibir os parâmetros
        JPanel paramPanel = new JPanel();
        paramPanel.setLayout(new GridLayout(5, 1)); // Alterado para 5 linhas
        paramPanel.setBackground(Color.LIGHT_GRAY);
        paramPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel tipoLabel = new JLabel("Tipo: " + (tipo.equals("n") ? "Número" : "Caractere"));
        JLabel valoresLabel = new JLabel("Valores: " + valoresStr);
        JLabel algoritmoLabel = new JLabel("Algoritmo: " + algoritmoStr);
        JLabel ordemLabel = new JLabel("Ordem: " + (crescente ? "Crescente" : "Decrescente"));
        JLabel tempoLabel = new JLabel("Tempo de Execução: ");

        paramPanel.add(tipoLabel);
        paramPanel.add(valoresLabel);
        paramPanel.add(algoritmoLabel);
        paramPanel.add(ordemLabel);
        paramPanel.add(tempoLabel); // Adicionado o JLabel para tempo de execução

        frame.add(paramPanel, BorderLayout.NORTH);

        PyramidPanel panel;

        long startTime = System.currentTimeMillis(); // Captura o tempo de início

        if (tipo.equals("n")) {
            Integer[] numeros = InputUtils.processarNumeros(valoresArray);
            if (numeros != null) {
                panel = new PyramidPanel(numeros);
                frame.add(panel, BorderLayout.CENTER);
                frame.setVisible(true);
                Sorter.sort(numeros, algoritmo, panel, crescente);
            } else {
                System.out.println("Valores inválidos para tipo numérico.");
            }
        } else {
            Character[] caracteres = InputUtils.processarCaracteres(valoresArray);
            if (caracteres != null) {
                panel = new PyramidPanel(caracteres);
                frame.add(panel, BorderLayout.CENTER);
                frame.setVisible(true);
                Sorter.sort(caracteres, algoritmo, panel, crescente);
            } else {
                System.out.println("Valores inválidos para tipo caractere.");
            }
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo de fim
        long duration = endTime - startTime; // Calcula a duração
        tempoLabel.setText("Tempo de Execução: " + duration + " ms"); // Atualiza o rótulo com o tempo
    }
}
