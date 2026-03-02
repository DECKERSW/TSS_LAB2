import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TitlesFrame extends JFrame {
    public TitlesFrame() {
        this.initUI();
    }

    /**
     * Налаштовує параметри головного вікна програми.
     * Встановлює заголовок, розмір, поведінку при закритті та додає графічну панель.
     */
    private void initUI() {
        this.setTitle("Кривые фигуры");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Виправлення "Магічного числа"

        // РЕФАКТОРИНГ: Використання Enum замість Impostor Type (магічного числа 57)
        this.add(new TitlesPanel(ShapeFactory.FigureShape.SQUARE, ShapeFactory.FigureStyle.GRADIENT));

        this.setSize(350, 350);
        this.setLocationRelativeTo((Component)null);
    }

    /**
     * Головна точка входу в програму.
     * Запускає графічний інтерфейс користувача у безпечному потоці обробки подій.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TitlesFrame ps = new TitlesFrame();
                ps.setVisible(true);
            }
        });
    }
}