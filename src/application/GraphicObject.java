package application;

import javafx.scene.Node;

public class GraphicObject {
    protected Node corps;
    protected boolean alive = true;

    public Node getCorps() {
        return corps;
    }

    public void setCorps(Node corps) {
        this.corps = corps;
    }
    public void position(Zone z){
        this.corps.setTranslateX(position2(z.getX()+50, z.getX1()-50));
        this.corps.setTranslateY(position2(z.getY()+50, z.getY1()-50));
    }
    
    public double position2(double min, double max){
        double y;
        while(true){
            y=Math.random()*(max-min);
            if(y<max && y>min){
                break;
            }
        }
        return y;
    }
    
    public boolean boom(GraphicObject e){
        return this.corps.getBoundsInParent().intersects(e.getCorps().getBoundsInParent());
    }
    public void replace(int i, int ii){
        this.corps.setTranslateX(this.corps.getTranslateX()+i);
        this.corps.setTranslateY(this.corps.getTranslateY()+ii);
    }
    public boolean ifalive(){
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
