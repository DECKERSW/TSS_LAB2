import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TitlesPanel extends JPanel implements ActionListener {
    private Graphics2D g2d;
    private Timer animation;
    private boolean is_done = true;
    private int start_angle = 0;

    // Замість int зберігаємо фабрику (усунення Impostor Type)
    private ShapeFactory shapeFactory;

    /**
     * СТАРИЙ КОНСТРУКТОР: Збережено для зворотної сумісності.
     * Конструктор панелі для анімації.
     * Встановлює тип фігури та запускає таймер для оновлення кадрів.
     *
     * @param _shape ідентифікатор фігури (старий формат числа), що буде малюватися
     */
    public TitlesPanel(int _shape) {
        this.shapeFactory = new ShapeFactory(_shape);
        initTimer();
    }

    /**
     * НОВИЙ КОНСТРУКТОР: Використовує строгу типізацію (Enum).
     * Конструктор панелі для анімації, що використовує безпечні перерахування.
     * * @param shape безпечний тип геометричної форми
     * @param style безпечний тип стилю та заливки
     */
    public TitlesPanel(ShapeFactory.FigureShape shape, ShapeFactory.FigureStyle style) {
        this.shapeFactory = new ShapeFactory(shape, style);
        initTimer();
    }

    // Винесення ініціалізації таймера в окремий метод (усунення Run-On Initialization)
    private void initTimer() {
        this.animation = new Timer(50, this);
        this.animation.setInitialDelay(50);
        this.animation.start();
    }

    public void actionPerformed(ActionEvent arg0) {
        if (this.is_done) {
            this.repaint();
        }
    }

    /**
     * Виконує безпосереднє відмальовування графіки на панелі.
     * Застосовує трансформації, обертання та згладжування до фігур.
     *
     * @param g об'єкт графічного контексту для малювання
     */
    private void doDrawing(Graphics g) {
        this.is_done = false;
        this.g2d = (Graphics2D)g;
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension size = this.getSize();
        Insets insets = this.getInsets();
        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        // Використовуємо вже проініціалізовані значення фабрики
        this.g2d.setStroke(shapeFactory.stroke);
        this.g2d.setPaint(shapeFactory.paint);

        double angle = (double)(this.start_angle++);
        if (this.start_angle > 360) this.start_angle = 0;

        double dr = 90.0D / ((double)w / ((double)shapeFactory.width * 1.5D));

        for(int j = shapeFactory.height; j < h; j = (int)((double)j + (double)shapeFactory.height * 1.5D)) {
            for(int i = shapeFactory.width; i < w; i = (int)((double)i + (double)shapeFactory.width * 1.5D)) {
                angle = angle > 360.0D ? 0.0D : angle + dr;
                AffineTransform transform = new AffineTransform();
                transform.translate((double)i, (double)j);
                transform.rotate(Math.toRadians(angle));
                this.g2d.draw(transform.createTransformedShape(shapeFactory.shape));
            }
        }
        this.is_done = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.doDrawing(g);
    }
}