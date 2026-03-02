import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

public class ShapeFactory {
    public Shape shape;
    // Усунення Run-On Initialization: встановлюємо безпечні значення за замовчуванням
    public BasicStroke stroke = new BasicStroke(3.0F);
    public Paint paint = Color.black;
    public int width = 25;
    public int height = 25;

    // Перерахування (Enums) для усунення антипатерну "Тип-самозванець" (Impostor Type)
    public enum FigureShape {
        STAR_3, STAR_5, SQUARE, TRIANGLE, ARC
    }

    public enum FigureStyle {
        DEFAULT, THICK_STROKE, GRADIENT, RED
    }

    /**
     * СТАРИЙ КОНСТРУКТОР: Збережено для зворотної сумісності.
     * Конструктор фабрики фігур.
     * Ініціалізує геометричну форму та стиль заливки на основі переданого ідентифікатора.
     *
     * @param shape_type цілочисельний ідентифікатор типу фігури та градієнта (Імпостор Тип)
     */
    public ShapeFactory(int shape_type) {
        this(mapIntToShape(shape_type / 10), mapIntToStyle(shape_type % 10));
    }

    /**
     * НОВИЙ КОНСТРУКТОР: Використовує строгу типізацію (Enum).
     * Конструктор фабрики фігур, який базується на безпечних перерахуваннях.
     * * @param figShape тип геометричної фігури
     * @param figStyle стиль заливки та контуру фігури
     */
    public ShapeFactory(FigureShape figShape, FigureStyle figStyle) {
        initShape(figShape);
        initStyle(figStyle);
    }

    // Допоміжні методи для мапінгу старого int у нові Enum
    private static FigureShape mapIntToShape(int val) {
        switch(val) {
            case 1: return FigureShape.STAR_3;
            case 3: return FigureShape.STAR_5;
            case 5: return FigureShape.SQUARE;
            case 7: return FigureShape.TRIANGLE;
            case 9: return FigureShape.ARC;
            default: throw new IllegalArgumentException("Unsupported shape: " + val);
        }
    }

    private static FigureStyle mapIntToStyle(int val) {
        switch(val) {
            case 1: case 3: return FigureStyle.DEFAULT;
            case 4: return FigureStyle.THICK_STROKE;
            case 7: return FigureStyle.GRADIENT;
            case 8: return FigureStyle.RED;
            default: throw new IllegalArgumentException("Unsupported style: " + val);
        }
    }

    private void initShape(FigureShape figShape) {
        switch(figShape) {
            case STAR_3:
                this.shape = createStar(3, new Point(0, 0), width / 2.0, width / 2.0);
                break;
            case STAR_5:
                this.shape = createStar(5, new Point(0, 0), width / 2.0, width / 4.0);
                break;
            case SQUARE:
                this.shape = new Rectangle2D.Double(-width / 2.0D, -height / 2.0D, width, height);
                break;
            case TRIANGLE:
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0D) / 2.0D * height;
                path.moveTo(-width / 2.0, -tmp_height);
                path.lineTo(0.0D, -tmp_height);
                path.lineTo(width / 2.0, tmp_height);
                path.closePath();
                this.shape = path;
                break;
            case ARC:
                this.shape = new Arc2D.Double(-width / 2.0, -height / 2.0, width, height, 30.0, 300.0, 2);
                break;
        }
    }

    private void initStyle(FigureStyle figStyle) {
        switch(figStyle) {
            case DEFAULT:
                break;
            case THICK_STROKE:
                this.stroke = new BasicStroke(7.0F);
                break;
            case GRADIENT:
                this.paint = new GradientPaint(-width, -height, Color.white, width, height, Color.gray, true);
                break;
            case RED:
                this.paint = Color.red;
                break;
        }
    }

    /**
     * Створює геометричну фігуру у вигляді зірки.
     *
     * @param arms кількість променів зірки
     * @param center координати центру фігури
     * @param rOuter зовнішній радіус
     * @param rInner внутрішній радіус
     * @return згенерована геометрична фігура (Shape)
     */
    private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
        double angle = Math.PI / arms;
        GeneralPath path = new GeneralPath();
        for(int i = 0; i < 2 * arms; ++i) {
            double r = (i & 1) == 0 ? rOuter : rInner;
            Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
            if (i == 0) path.moveTo(p.getX(), p.getY());
            else path.lineTo(p.getX(), p.getY());
        }
        path.closePath();
        return path;
    }
}