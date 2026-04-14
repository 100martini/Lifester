package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster extends GraphicObject{
    static private int nombres = 0;
    private Blood blood;
    
    public Monster(Zone z){
        corps = new ImageView(new Image(getClass().getResourceAsStream("Pictures/SpaceAlien.png")));
        ((ImageView)corps).setPreserveRatio(true);
        ((ImageView)corps).setFitWidth(80);
        ((ImageView)corps).setX(0);
        ((ImageView)corps).setY(0);
        double x = z.getX()+(z.getX1()-z.getX())*Math.random();
        double y = z.getY()+(z.getY1()-z.getY())*Math.random();
        ((ImageView)corps).setTranslateX(x);
        ((ImageView)corps).setTranslateY(y);
        increment();
    }
    public Monster(){
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("Pictures/bigmonstre.png")));
        img.setFitHeight(110);
        img.setFitWidth(110);
        corps = img;
        double x = 0+(800-0)*Math.random();
        double y = 0+(200-0)*Math.random();
        ((ImageView)corps).setTranslateX(x);
        ((ImageView)corps).setTranslateY(y);
        blood = new Blood();
        blood.MonstreBlood();
    }
    public Blood getBlood(){
        return this.blood;
    }
    
    static public int getNombre(){
        return nombres;
    }
    static public void setNombre(int i){
        nombres=i;
    }
    public void increment(){
        nombres++;
    }
}

