import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PyramidPanel extends JPanel {
    private Object[] array; // Alterado para Object[] para suportar tanto Integer quanto Character

    public PyramidPanel(Object[] array) {
        this.array = array;
    }

    public void updatePyramid(Object[] array) {
        this.array = array;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (array == null || array.length == 0) {
            return;
        }

        // Determine o valor máximo absoluto para dimensionar as colunas
        int maxAbs = Arrays.stream(array)
                .mapToInt(value -> {
                    if (value instanceof Integer) {
                        return Math.abs((Integer) value);
                    } else if (value instanceof Character) {
                        return Math.abs((Character) value); // Usando o valor ASCII
                    }
                    return 0;
                })
                .max().orElse(0);

        int barWidth = getWidth() / array.length;
        int halfHeight = getHeight() / 2;

        for (int i = 0; i < array.length; i++) {
            int barHeight;
            Color barColor;

            if (array[i] instanceof Integer) {
                barHeight = (int) (((double) Math.abs((Integer) array[i]) / maxAbs) * halfHeight);
                barColor = ((Integer) array[i]) < 0 ? Color.RED : Color.BLUE;
            } else {
                // Considerando o valor ASCII do caractere
                barHeight = (int) (((double) Math.abs((Character) array[i]) / maxAbs) * halfHeight);
                barColor = Color.GREEN; // Cor para caracteres
            }

            int x = i * barWidth;
            int y = (array[i] instanceof Integer && (Integer) array[i] < 0) ? halfHeight : halfHeight - barHeight;

            g.setColor(barColor);
            g.fillRect(x, y, barWidth - 2, barHeight); // Ajuste de -2 para bordas brancas
            g.setColor(Color.WHITE);
            g.drawRect(x, y, barWidth - 2, barHeight); // Desenho da borda

            // Desenho do valor no centro da coluna
            g.setColor(Color.BLACK); // Cor do texto
            String valor = array[i] instanceof Integer ? String.valueOf(array[i]) : String.valueOf((int) (char) array[i]);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(valor);
            int textHeight = fm.getHeight();
            int textX = x + (barWidth - 2 - textWidth) / 2; // Centraliza o texto horizontalmente
            int textY = y + (barHeight + textHeight) / 2; // Centraliza o texto verticalmente
            g.drawString(valor, textX, textY);
        }
    }
}
