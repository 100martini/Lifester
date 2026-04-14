package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends GraphicObject{
 private Circle circle = new Circle(0, 0, 5, Color.WHITE);
 private Point2D dot = new Point2D(0, 0);
    public Ball(Arme arme){
        corps = circle;
        corps.setTranslateX(arme.getCircle().getTranslateX());
        corps.setTranslateY(arme.getCircle().getTranslateY());
        direction(arme.getCorps().getRotate()-90);
    }
    
    public Ball(){
        corps = circle;
        this.circle.setFill(Color.WHITE);
        direction(0);
    }
    
    private void direction(double angle){
        double x = Math.cos(Math.toRadians(angle));
        double y = Math.sin(Math.toRadians(angle));
        Point2D p = new Point2D(x, y);
        this.dot = p.normalize().multiply(5);
    }
    
    public void update(){
        corps.setTranslateX(corps.getTranslateX()+dot.getX());
        corps.setTranslateY(corps.getTranslateY()+dot.getY());
    }
    
    
}
