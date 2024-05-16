import java.util.Vector;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/*
 * Louis Romeo
 * CSC 335 Assignment 8
 * Purpose: Drawing GUI that allows the user to print shapes
 * 			of varying sizes and colors on a drawing canvas.
 */
public class DrawingCanvas extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Use Vector instead of ArrayList
    private Vector<PaintObject> allPaintObjects;

    // uses enumeration to classify the 4 drawing types
    enum CurrentPaintObject {
        LINE, RECTANGLE, OVAL, PICTURE
    }

    // Current object is originally set to line.
    private CurrentPaintObject currentShape = CurrentPaintObject.LINE;
    private Color currentColor = Color.BLACK;
    private Point2D startPoint;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane all = new BorderPane();

        Canvas canvas = new Canvas(960, 740);
        setMouseHandlersOn(canvas);
        all.setCenter(canvas);

        // Add buttons for switching between shapes
        // Each button changes the current paint object.
        Button lineButton = new Button("Line");
        lineButton.setOnAction(e -> currentShape = CurrentPaintObject.LINE);

        Button rectangleButton = new Button("Rectangle");
        rectangleButton.setOnAction(e -> currentShape = CurrentPaintObject.RECTANGLE);

        Button ovalButton = new Button("Oval");
        ovalButton.setOnAction(e -> currentShape = CurrentPaintObject.OVAL);

        Button pictureButton = new Button("Picture");
        pictureButton.setOnAction(e -> currentShape = CurrentPaintObject.PICTURE);

        // Add color picker
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(e -> currentColor = colorPicker.getValue());

        // Layout for buttons and color picker
        HBox buttonPane = new HBox(10);
        buttonPane.getChildren().addAll(lineButton, rectangleButton, ovalButton, pictureButton, colorPicker);
        all.setBottom(buttonPane);

        allPaintObjects = new Vector<>();

        Scene scene = new Scene(all, 960, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setMouseHandlersOn(Canvas canvas) {
        canvas.setOnMousePressed(e -> startPoint = new Point2D(e.getX(), e.getY()));

        canvas.setOnMouseReleased(e -> {
            double x = e.getX();
            double y = e.getY();
            switch (currentShape) {
                case LINE:
                    allPaintObjects.add(new Line(currentColor, startPoint, new Point2D(x, y)));
                    break;
                case RECTANGLE:
                    allPaintObjects.add(new Rectangle(currentColor, startPoint, new Point2D(x, y)));
                    break;
                case OVAL:
                    allPaintObjects.add(new Oval(currentColor, startPoint, new Point2D(x, y)));
                    break;
                case PICTURE:
                    // doge.jpeg is the picture
                    allPaintObjects.add(new Picture(startPoint, new Point2D(x, y), "doge.jpeg"));
                    break;
            }
            redrawCanvas(canvas);
        });

        canvas.setOnMouseDragged(e -> redrawCanvas(canvas, startPoint, new Point2D(e.getX(), e.getY())));
    }

    private void redrawCanvas(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (PaintObject po : allPaintObjects) {
            po.draw(gc);
        }
    }

    private void redrawCanvas(Canvas canvas, Point2D start, Point2D end) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (PaintObject po : allPaintObjects) {
            po.draw(gc);
        }
        // Utilizes cases to switch between drawing objects, resets
        // the currentShape variable which defaults to line.
        switch (currentShape) {
            case LINE:
                gc.setStroke(currentColor);
                gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
                break;
            case RECTANGLE:
                double width = Math.abs(end.getX() - start.getX());
                double height = Math.abs(end.getY() - start.getY());
                gc.setFill(currentColor);
                gc.fillRect(start.getX(), start.getY(), width, height);
                break;
            case OVAL:
                width = Math.abs(end.getX() - start.getX());
                height = Math.abs(end.getY() - start.getY());
                gc.setFill(currentColor);
                gc.fillOval(start.getX(), start.getY(), width, height);
                break;
            case PICTURE:
                Image image = new Image("doge.jpeg"); // Possible error for picture
                gc.drawImage(image, start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
                break;
        }
    }
}
