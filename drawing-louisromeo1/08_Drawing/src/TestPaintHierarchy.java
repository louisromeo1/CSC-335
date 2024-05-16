/* A Canvas that has all four types of PaintObjects drawn on it.
 * Currently, a list of paint objects is hardcoded. A Canvas exists
 * in this BorderPane that will draw all PaintObject stored
 * in this hardCoded Vector<PaintObject> object.
 * 
 * Note PaintObject is the base class of the paint hierarchy.
 * 
 * you must implement Line, Rectangle, Oval, and Picture, one
 * at a time, all of which must extend abstract class PaintObject.
 * 
 * @author Rick Mercer
 * *MODIFIED BY Louis Romeo
*/

import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Point2D;


public class TestPaintHierarchy extends Application {

 public static void main(String[] args) {
   launch(args);
 }

 @Override
 public void start(Stage primaryStage) throws Exception {
   BorderPane all = new BorderPane();
   Canvas canvas = new Canvas(800, 550);
   all.setCenter(canvas);
   
   // Draw a bunch of PaintObjects
   Vector<PaintObject> allPaintObjects = createVectorOfPaintObjects();
   drawAllPaintObects(allPaintObjects, canvas);

   Scene scene = new Scene(all, 800, 550);
   primaryStage.setScene(scene);
   primaryStage.show();
 }

 private void drawAllPaintObects(Vector<PaintObject> allPaintObjects, Canvas canvas) {
   GraphicsContext gc = canvas.getGraphicsContext2D();
   for (PaintObject po : allPaintObjects) {
     po.draw(gc);
   }
 }

 // TODO: Uncomment the PaintObjects as you are implementing 
 // the subclasses. class Line extends PaintObject first
 private Vector<PaintObject> createVectorOfPaintObjects() {
   // All of these PaintObject objects must be constructed with two Point objects.
   // The first point could be to the upper left or to the lower right 
   Vector<PaintObject> allPaintObjects = new Vector<>();
   // Create six line objects, where any line must be drawn between the
   // two Point objects, from and to, in the provided color. 
   PaintObject a = new Line(Color.RED, new Point2D(10, 100), new Point2D(500, 100));
   PaintObject b = new Line(Color.CYAN, new Point2D(250, 150), new Point2D(250, 5));
   PaintObject c = new Line(Color.GREEN, new Point2D(255, 10), new Point2D(255, 145));
   PaintObject d = new Line(Color.BLACK, new Point2D(245, 145), new Point2D(245, 10));
   PaintObject oneMore = new Line(Color.BLACK, new Point2D(0, 0), new Point2D(245, 145));
   PaintObject anOther = new Line(Color.GRAY, new Point2D(500, 0), new Point2D(255, 145));
   allPaintObjects.add(a);
   allPaintObjects.add(b);
   allPaintObjects.add(c);
   allPaintObjects.add(d);
   allPaintObjects.add(oneMore);
   allPaintObjects.add(anOther);

   // Draw five rectangles
   // First Point2D(200, 200) is above and the left of the second Point2D
   PaintObject e = new Rectangle(Color.PINK, new Point2D(200, 200), new Point2D(350, 500));
   // First Point2D (150, 300) is below and to the right of the second Point2D
   PaintObject f = new Rectangle(Color.CYAN, new Point2D(150, 300), new Point2D(100, 100));
   PaintObject g = new Rectangle(Color.GREEN, new Point2D(400, 200), new Point2D(420, 250));
   PaintObject h = new Rectangle(Color.BLUE, new Point2D(500, 380), new Point2D(360, 420));
   PaintObject i = new Rectangle(Color.RED, new Point2D(400, 520), new Point2D(540, 480));
   allPaintObjects.add(e);
   allPaintObjects.add(f);
   allPaintObjects.add(g);
   allPaintObjects.add(h);
   allPaintObjects.add(i);

   // Draw five ovals 
   // First Point2D(500, 20) is above and the left of the second Point2D
   PaintObject j = new Oval(Color.BLACK, new Point2D(300, 20), new Point2D(340, 120));
   // Make an oval that is wider than higher. First Point2D is still upper left
   PaintObject k = new Oval(Color.GREEN, new Point2D(100, 220), new Point2D(200, 260));
   // First Point2D(800, 250) is below and to the right of the second Point2D for a circle
   PaintObject l = new Oval(Color.RED, new Point2D(222, 320), new Point2D(230, 220));
   // Another circle with upper left to the other side
   PaintObject m = new Oval(Color.BLUE, new Point2D(600, 50), new Point2D(700, 100));
   allPaintObjects.add(j);
   allPaintObjects.add(k);
   allPaintObjects.add(l);
   allPaintObjects.add(m);

   PaintObject n = new Picture(new Point2D(12, 8), new Point2D(50, 50), "doge.jpeg");
   // The constructor needs to adjust values if from is lower right of to
   PaintObject o = new Picture(new Point2D(140, 140), new Point2D(220, 280),  "doge.jpeg");
   PaintObject p = new Picture (new Point2D(500, 400), new Point2D(600, 600), "doge.jpeg");
   allPaintObjects.add(n);
   allPaintObjects.add(o);
   allPaintObjects.add(p);
  
   return allPaintObjects;
 }
}