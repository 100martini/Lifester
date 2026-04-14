package application;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{
    Media sound = new Media(getClass().getResource("Sounds/test.mp3").toExternalForm());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    private double width=900,height=600;
    private ArrayList<Monster> Monsters = new ArrayList<>();
    private ArrayList<Ball> Balls = new ArrayList<>();
    private int nbMonsterrest = 5;
    private int nbrbigmonsterrest = 15;
    private Text nbMonster = new Text();
    Pane root = new Pane();
    Scene scene = new Scene(root,width,height);
    Line line = new Line(0, 200, width, 200);
    Zone zone1 = new Zone(line.getStartX(), 0, line.getEndX()-50, line.getEndY()-50);
    Zone zone2 = new Zone(0, 0, 300, 700);
    Hero hero = new Hero(zone2);
    Rectangle r = new Rectangle(190, 30);
    int nbBalls = 100;
    Rectangle r2 = new Rectangle(180, 30);
    Text nbBallsRest = new Text();
    boolean stage2 = false;
    boolean alertt = false;
    boolean fin = false;
    int check = 25;
    ArrayList<Ball> danger = new ArrayList<>();
    boolean stage3 = false;
    boolean allt = false;
    AnimationTimer animation,animation2,animm;
    Monster bigMonster;
    ArrayList<Danger> Dangers = new ArrayList<>();
    int wacch=25;
    Monster m1,m2;
    Arme arme = new Arme(hero);
    Arme arme2 = new Arme(hero);
    Arme arme3 = new Arme(hero);


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mediaPlayer.play();
        stage.setResizable(false);
        root.setId("pane");
        line.setStroke(Color.TRANSPARENT);
        r.setFill(Color.WHITE);
        r.setTranslateX(8);
        r.setTranslateY(562);
        nbMonster.setText("Number of monsters left: "+nbMonsterrest);
        nbMonster.setTranslateX(10);
        nbMonster.setTranslateY(580);

        this.animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                theBall(root);
                    if(Math.random()<0.02 && Monster.getNombre()<5){
                        Monster Monster = new Monster(zone1);
                        root.getChildren().add(Monster.getCorps());
                        Monsters.add(Monster);
                    }
                    if(stage2){
                        for(Ball Ball : danger){
                            if(Ball.boom(hero)){
                                hero.getBlood().n9es(check);
                                check++;
                            }
                        }

                        if(alertt){
                            stage2(stage);
                            alertt = false;
                            nbMonsterrest = 10;
                        }

                        if(Math.random()<0.02 && Monster.getNombre()<15){
                            Monster Monster = new Monster(zone1);
                            root.getChildren().add(Monster.getCorps());
                            Monsters.add(Monster);

                            double x = 70*Math.random();
                            double y = (150*Math.random())+30;
                            double x1 = (700*Math.random())+150;
                            double y1 = (150*Math.random())+30;

                            Line path = new Line(x, y, x1, y1);
                            PathTransition trans = new PathTransition(Duration.seconds(2), path, Monster.getCorps());
                            trans.setOrientation(PathTransition.OrientationType.NONE);
                            trans.setCycleCount(FadeTransition.INDEFINITE);
                            trans.setAutoReverse(true);
                            trans.play();
                        }

                        AnimationTimer anim = new AnimationTimer() {
                            @Override
                            public void handle(long l) {
                                if(nbMonsterrest==0){
                                    stage3 = true;
                                    allt = true;
                                }
                            }
                        };
                        anim.start();
                    }
            }
        };

        animation2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(nbMonsterrest==0){
                    alertt = true;
                    stage2 = true;
                }
                if(nbBalls!=100) {alertt=false;}
                if(nbBalls<=0 || hero.getBlood().ba9i()) {hero.getBlood().again(); nbBalls=100; stage2(stage);}

                if(stage3){
                    animation.stop();
                    stage3(stage, this);
                }
            }
        };

        stage.setTitle("Lifester");
        Image icon = new Image(getClass().getResourceAsStream("Pictures/icon.png"));
        stage.getIcons().add(icon);
        final Stage landingPage = new Stage();
        landingPage.setTitle("Lifester");
        landingPage.getIcons().add(icon);
        Pane land = new Pane();
        Button btn = new Button();
        btn.setStyle("-fx-background-color: TRANSPARENT;");
        Media landingVideo = new Media(getClass().getResource("/Video/landing.mp4").toExternalForm());
        MediaPlayer landingPlayer = new MediaPlayer(landingVideo);
        landingPlayer.setAutoPlay(true);
        landingPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(landingPlayer);
        mediaView.setFitWidth(900);
        mediaView.setFitHeight(600);
        land.getChildren().setAll(mediaView, btn);
        land.setId("landingPage");
        Scene landingScene = new Scene(land);

        landingPage.setScene(landingScene);
        landingPage.show();

        animation2.start();
        root.getChildren().addAll(line, hero.getCorps(), arme.getCorps(), arme.getCircle(), r, nbMonster);
        animation.start();
        landingScene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
        action(scene, arme, hero, root, stage);
        stage.setScene(scene);

        btn.setOnAction((eh)->{
            landingPage.close();
            stage.show();
            scene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
        });
    }

    public void stage3(Stage stage, AnimationTimer anim){
        hero.corps.setTranslateX(390);
        hero.corps.setTranslateY(310);
        animation.stop();
        animation2.stop();
        root.setId("stage3");
        nbBalls = 150;
        nbBallsRest.setText("Number of balls left: "+nbBalls);
        hero.getBlood().again();

        for(Ball Ball : Balls){
            root.getChildren().remove(Ball.getCorps());
        }
        Balls.clear();
        root.getChildren().removeAll(nbMonster, r);

        for(Ball Ball : danger){
            root.getChildren().remove(Ball.getCorps());
        }
        for(Monster Monster : Monsters){
            root.getChildren().remove(Monster.getCorps());
        }

        anim.stop();
        stage.close();
        final Stage dialog = new Stage();
        Image icon = new Image(getClass().getResourceAsStream("Pictures/icon.png"));
        dialog.getIcons().add(icon);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        Pane dialogVbox = new Pane();
        Text t = new Text("You win!!! You passed the second stage of your life! Keep going..");
        t.setLayoutX(40);
        t.setLayoutY(80);
        Button btn = new Button("CLICK ON THE SPACE BUTTON TO PASS TO THE NEXT LEVEL!");
        btn.setLayoutX(38);
        btn.setLayoutY(95);
        btn.setStyle("-fx-background-color: TRANSPARENT;");
        dialogVbox.getChildren().addAll(btn, t);
        Scene dialogScene = new Scene(dialogVbox, 500, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("Lifester");
        dialog.show();

        btn.setOnAction((eh)->{
            dialog.close();
            stage.show();
        });

        m1 = new Monster();
        m2 = new Monster();

        Line path = new Line(-10, 50, 810, 50);
        PathTransition trans = new PathTransition(Duration.seconds(2), path, m1.getCorps());
        trans.setOrientation(PathTransition.OrientationType.NONE);
        trans.setCycleCount(FadeTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();

        Line path1 = new Line(-10, 150, 810, 150);
        PathTransition trans1 = new PathTransition(Duration.seconds(3), path1, m2.getCorps());
        trans1.setOrientation(PathTransition.OrientationType.NONE);
        trans1.setCycleCount(FadeTransition.INDEFINITE);
        trans1.setAutoReverse(true);
        trans1.play();

        m2.getBlood().bdel();
        root.getChildren().removeAll(arme2.getCorps(), arme2.getCircle());
        root.getChildren().addAll(m1.getCorps(), m2.getCorps(), m1.getBlood().getRect1(), m2.getBlood().getRect1(), m1.getBlood().getRect2(), m2.getBlood().getRect2(), hero.getBlood().getRect1(), hero.getBlood().getRect2(), arme3.getCorps(), arme3.getCircle());
        action(scene, arme3, hero, root, stage);

        anim = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(Ball Ball : Balls){
                    Ball.update();
                }

                for(Danger Danger : Dangers){
                    if(Danger.boom(hero)){
                        hero.getBlood().n9es(check);
                        check++;
                    }
                }

                if(m1.getBlood().ba9i()) root.getChildren().removeAll(m1.getBlood().getRect1(), m1.getBlood().getRect2(), m1.getCorps());
                if(m2.getBlood().ba9i()) root.getChildren().removeAll(m2.getBlood().getRect1(), m2.getBlood().getRect2(), m2.getCorps());
                if(m1.getBlood().ba9i() && m2.getBlood().ba9i()) { fin(stage, this); }

                if(Math.random()<0.004){
                    Danger Danger = new Danger();
                    Danger.hbet();
                    Dangers.add(Danger);
                    root.getChildren().add(Danger.getCorps());
                }

                for(Ball Ball : Balls){
                    if(Ball.boom(m1)){
                        root.getChildren().remove(Ball.getCorps());
                        Ball.setAlive(false);
                        m1.getBlood().n9es(wacch);
                        wacch++;
                    }
                    if(Ball.boom(m2)){
                        root.getChildren().remove(Ball.getCorps());
                        Ball.setAlive(false);
                        m2.getBlood().n9es(check);
                        check++;
                    }
                }
            }
        };
        anim.start();
    }

    public void inStage3(Stage stage, AnimationTimer animation){
        animation.stop();
        Balls.clear();
        root.getChildren().remove(bigMonster.getCorps());

        m1 = new Monster();
        m2 = new Monster();

        Line path = new Line(-10, 50, 810, 50);
        PathTransition trans = new PathTransition(Duration.seconds(2), path, m1.getCorps());
        trans.setOrientation(PathTransition.OrientationType.NONE);
        trans.setCycleCount(FadeTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play();

        Line path1 = new Line(-10, 150, 810, 150);
        PathTransition trans1 = new PathTransition(Duration.seconds(3), path1, m2.getCorps());
        trans1.setOrientation(PathTransition.OrientationType.NONE);
        trans1.setCycleCount(FadeTransition.INDEFINITE);
        trans1.setAutoReverse(true);
        trans1.play();

        m2.getBlood().bdel();
        root.getChildren().addAll(m1.getCorps(), m2.getCorps(), m1.getBlood().getRect1(), m2.getBlood().getRect1(), m1.getBlood().getRect2(), m2.getBlood().getRect2(), hero.getBlood().getRect1(), hero.getBlood().getRect2());

        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(Ball Ball : Balls){
                    if(Ball.boom(m1)){
                        root.getChildren().remove(Ball.getCorps());
                        Ball.setAlive(false);
                        m1.getBlood().n9es(wacch);
                        wacch++;
                    }
                    if(Ball.boom(m2)){
                        root.getChildren().remove(Ball.getCorps());
                        Ball.setAlive(false);
                        m2.getBlood().n9es(check);
                        check++;
                    }
                }
            }
        };
        anim.start();

        AnimationTimer aniii = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(m1.getBlood().ba9i()) root.getChildren().removeAll(m1.getBlood().getRect1(), m1.getBlood().getRect2(), m1.getCorps());
                if(m2.getBlood().ba9i()) root.getChildren().removeAll(m2.getBlood().getRect1(), m2.getBlood().getRect2(), m2.getCorps());
                if(m1.getBlood().ba9i() && m2.getBlood().ba9i()) fin(stage, this);
            }
        };
        aniii.start();
    }

    public void fin(Stage stage, AnimationTimer anim){
        anim.stop();
        stage.close();
        final Stage dialog = new Stage();
        Image icon = new Image(getClass().getResourceAsStream("Pictures/icon.png"));
        dialog.getIcons().add(icon);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        Pane dialogVbox = new Pane();
        Text t = new Text("Woah! YOU WIN! You've finished the battle of life, be proud!");
        t.setLayoutX(40);
        t.setLayoutY(80);
        Button btn = new Button("CLICK ON THE SPACE BUTTON TO CLOSE THE GAME!");
        btn.setLayoutX(38);
        btn.setLayoutY(95);
        btn.setStyle("-fx-background-color: TRANSPARENT;");
        dialogVbox.getChildren().addAll(btn, t);
        Scene dialogScene = new Scene(dialogVbox, 500, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("Lifester");
        dialog.show();

        btn.setOnAction((eh)->{
            dialog.close();
        });
    }

    public void stage2(Stage stage){
        hero.corps.setTranslateX(390);
        hero.corps.setTranslateY(310);
        nbMonster.setText("Number of monsters left: "+nbrbigmonsterrest);
        stage.close();
        root.setId("root");
        final Stage dialog = new Stage();
        Image icon = new Image(getClass().getResourceAsStream("Pictures/icon.png"));
        dialog.getIcons().add(icon);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        Pane dialogVbox = new Pane();
        Text t = new Text("You win!!! You passed the first stage of your life! Keep going..");
        t.setLayoutX(40);
        t.setLayoutY(80);
        Button btn = new Button("CLICK ON THE SPACE BUTTON TO PASS TO THE NEXT LEVEL!");
        btn.setLayoutX(38);
        btn.setLayoutY(95);
        btn.setStyle("-fx-background-color: TRANSPARENT;");
        dialogVbox.getChildren().addAll(btn, t);
        Scene dialogScene = new Scene(dialogVbox, 500, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("Lifester");
        dialog.show();
        btn.setOnAction((eh)->{
            dialog.close();
            stage.show();
        });

        r2.setFill(Color.WHITE);
        r2.setTranslateX(310);
        r2.setTranslateY(562);
        nbBallsRest.setTranslateX(322);
        nbBallsRest.setTranslateY(580);
        nbBallsRest.setText("Number of balls left: "+nbBalls);
        root.getChildren().removeAll(arme.getCorps(), arme.getCircle());
        root.getChildren().addAll(r2, nbBallsRest, arme2.getCorps(), arme2.getCircle());
        action(scene, arme2, hero, root, stage);
    }

    public void action(Scene scene, Arme arme, Hero hero, Pane root, Stage stage){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                switch (t.getCode()){
                    case UP:    hero.replace(0,-5);
                                arme.replace(0,-5);
                                arme.replaceCircle();
                                break;
                    case LEFT:  hero.replace(-5,0);
                                arme.replace(-5,0);
                                arme.replaceCircle();
                                break;
                    case RIGHT: hero.replace(5,0);
                                arme.replace(5,0);
                                arme.replaceCircle();
                                break;
                    case DOWN:  hero.replace(0,5);
                                arme.replace(0,5);
                                arme.replaceCircle();
                                break;
                    case W: arme.retate(); arme.rotate(5); break;
                    case X: arme.retate(); arme.rotate(-5); break;
                    case SPACE:
                        Media sound1 = new Media(getClass().getResource("Sounds/shot.wav").toExternalForm());
                        MediaPlayer shot = new MediaPlayer(sound1);
                        shot.play();
                        Image image = new Image(getClass().getResourceAsStream("Pictures/Arme2.png"));
                        ((ImageView)arme.corps).setImage(image);
                        Ball Ball = new Ball(arme);
                        Balls.add(Ball);
                        if(stage2){ nbBalls--; nbBallsRest.setText("Number of balls left : "+nbBalls); }
                        root.getChildren().add(Ball.getCorps());
                        new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    Image image = new Image(getClass().getResourceAsStream("Pictures/Arme.png"));
                                    ((ImageView)arme.corps).setImage(image);
                                }
                            },
                            200
                        );
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void theBall(Pane group){
        for(Ball Ball : Balls){
            Ball.update();
        }
        for(Ball Ball : Balls){
            for(Monster Monster : Monsters){
                if(Ball.boom(Monster)){
                    group.getChildren().removeAll(Monster.getCorps(), Ball.getCorps());
                    Ball.setAlive(false);
                    Monster.setAlive(false);
                    nbMonsterrest--;
                    nbrbigmonsterrest--;
                    nbMonster.setText("Number of monsters left: "+nbMonsterrest);
                }
            }
        }
        Monsters.removeIf(GraphicObject::ifalive);
        Balls.removeIf(GraphicObject::ifalive);
    }
}
