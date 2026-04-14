package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Arme extends GraphicObject{
    private Circle c = new Circle(0, 0, 5);
    public Arme(GraphicObject e) {
        super.corps = new ImageView(new Image(getClass().getResourceAsStream("Pictures/Arme.png")));
        ((ImageView)corps).setPreserveRatio(true);
        ((ImageView)corps).setFitWidth(26);
        c.setFill(Color.TRANSPARENT);
        corps.setTranslateX(e.getCorps().getTranslateX()-18);
        corps.setTranslateY(e.getCorps().getTranslateY()+15);
        this.c.setTranslateX(corps.getTranslateX()+9);
        this.c.setTranslateY(corps.getTranslateY());
    }
    public Circle getCircle(){
        return c;
    }
    public void replaceCircle(){
        this.c.setTranslateX(corps.getTranslateX()+9);
        this.c.setTranslateY(corps.getTranslateY());
    }
    public void rotate(int i){
        this.c.setRotate(this.c.getRotate()+i);
        super.corps.setRotate(super.corps.getRotate()+i);
    }
    
    public void retate(){
        double a=Math.toDegrees(Math.cos(Math.toRadians(corps.getRotate()-90)));
        double b=Math.toDegrees(Math.sin(Math.toRadians(corps.getRotate()-90)));
        this.c.setTranslateX(10+corps.getTranslateX()+a);
        this.c.setTranslateY(33.5+corps.getTranslateY()+b);
    }
    
}
