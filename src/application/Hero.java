package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends GraphicObject{
    private Blood blood;
    public Hero(Zone z){
        corps = new ImageView(new Image(getClass().getResourceAsStream("Pictures/hero.png")));
        ((ImageView)corps).setPreserveRatio(true);
        ((ImageView)corps).setFitWidth(130);
        this.corps.setTranslateX(390);
        this.corps.setTranslateY(310);
        this.blood = new Blood();
    }
    public Blood getBlood() {
        return blood;
    }
   
}
