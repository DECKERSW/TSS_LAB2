/* Decompiler 69ms, total 305ms, lines 27 */
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TitlesFrame extends JFrame {
    public TitlesFrame() {
        this.initUI();
    }

    private void initUI() {
        this.setTitle("Кривые фигуры");
        this.setDefaultCloseOperation(3);
        this.add(new TitlesPanel(57));
        this.setSize(350, 350);
        this.setLocationRelativeTo((Component)null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TitlesFrame ps = new TitlesFrame();
                ps.setVisible(true);
            }
        });
    }
}