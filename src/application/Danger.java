package application;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Danger extends GraphicObject{

    public Danger() {
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("Pictures/fire.png")));
        img.setPreserveRatio(true);
        img.setFitWidth(70);
        super.corps = img;
        double x = 0+(800-0)*Math.random();
        ((ImageView)corps).setTranslateX(x);
        ((ImageView)corps).setTranslateY(0);
    }
    public void hbet(){
        Line path = new Line(corps.getTranslateX(), 0, corps.getTranslateX(),800);
        PathTransition trans = new PathTransition(Duration.seconds(5), path, this.corps);
        trans.setOrientation(PathTransition.OrientationType.NONE);
        trans.play(); 
    }
    
}
