import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/*
 * Louis Romeo
 * CSC 335 Assignment 8
 * Purpose: Class that includes all the draw-able objects
 * 			used in the DrawingCanvas. Includes multiple 
 * 			sub-classes for different shapes. Utilizes 
 * 			an inheritance hierarchy.
 */
abstract class PaintObject {
    
	protected Color color;
    protected Point2D from, to;

    public PaintObject(Color color, Point2D from, Point2D to) {
        this.color = color;
        this.from = from;
        this.to = to;
    }

    public abstract void draw(GraphicsContext gc);
}

/*
 * Class Line defines the line draw-able object,
 * is below the PaintObject class in the inheritance
 * hierarchy.
 */
class Line extends PaintObject {
    public Line(Color color, Point2D from, Point2D to) {
        super(color, from, to);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
    }
}
/*
 * Class Rectangle defines the rectangle draw-able shape,
 * is below the PaintObject class in the inheritance
 * hierarchy.
 */
class Rectangle extends PaintObject {
    public Rectangle(Color color, Point2D from, Point2D to) {
        super(color, from, to);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double width = Math.abs(to.getX() - from.getX());
        double height = Math.abs(to.getY() - from.getY());
        gc.fillRect(from.getX(), from.getY(), width, height);
    }
}
/*
 * Class Oval defines the oval draw-able shape,
 * is below the PaintObject class in the inheritance
 * hierarchy.
 */
class Oval extends PaintObject {
    public Oval(Color color, Point2D from, Point2D to) {
        super(color, from, to);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double width = Math.abs(to.getX() - from.getX());
        double height = Math.abs(to.getY() - from.getY());
        gc.fillOval(from.getX(), from.getY(), width, height);
    }
}
/*
 * Class Picture defines the picture draw-able object,
 * is below the PaintObject class in the inheritance
 * hierarchy. Allows for the user to implement their 
 * own picture to be printed on the DrawingCanvas.
 *
 */
class Picture extends PaintObject {
    private String imagePath;

    public Picture(Point2D from, Point2D to, String imagePath) {
        super(Color.TRANSPARENT, from, to);
        this.imagePath = imagePath;
    }

    @Override
    public void draw(GraphicsContext gc) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            gc.drawImage(image, from.getX(), from.getY(), Math.abs(to.getX() - from.getX()),
                    Math.abs(to.getY() - from.getY()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}