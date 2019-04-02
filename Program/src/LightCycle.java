//import modules
import javax.swing.*;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
import static java.nio.file.StandardOpenOption.*;


class LightCycle {

    //dim private variables and constants
    private int x,y; //position of lightcycle
    private int xDir, yDir; //direction of lightcycle
    private final int VELOCITY = 5; //velocity of lightcycle

    private boolean alive = true; //alive light cycle (uncrashed)



    private ArrayList<Point> points = new ArrayList<Point>(); //List of points travelled by lightcycle


    private int size; //size of light cycle
    private Color color;   //color of light cycle



    private static final Random random = new Random(); //generate random

    //constructor
    LightCycle(int size, Color color) {
        x = 0;
        y = 0;
        xDir = VELOCITY;
        yDir = 0;
        this.size = size;
        this.color = color;
    }

    //directions modification of light cycle (UP, DOWN, LEFT, RIGHT)
    public void up() {
        xDir = 0;
        yDir = -VELOCITY;
    }

    public void down() {
        xDir = 0;
        yDir = VELOCITY;
    }

    public void left(){
        xDir = -VELOCITY;
        yDir = 0;

    }

    public void right(){
        xDir = VELOCITY;
        yDir = 0;

    }

    //get points travelled by light cycle
    public ArrayList<Point> getPoints(){
        return points;
    }

    //move light cycle
    void move(Dimension size, ArrayList<Point> opponentPoints) {
        // update position based on current direction
        int ox = x; //distance from origin
        int oy = y; //distance from origin

        //distance + direction travelled
        x = x + xDir;
        y = y + yDir;

        //stop when touch the line
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (x == point.x && y == point.y) {
                xDir = 0;
                yDir = 0;
                x= ox;
                y = oy;

                break;
            }
        }


        //stop when touch opponent's line
        for (int i = 0; i < opponentPoints.size(); i++) {
            Point point = opponentPoints.get(i);
            if (x == point.x && y == point.y) {
                xDir = 0;
                yDir = 0;
                x= ox;
                y = oy;

                break;
            }
        }

        //stop when touch the wall
        if(x < 0 || x > 600) {
            xDir = 0;
        }
        if (y < 0 || y > 600){
            yDir = 0;
        }

        //crash
        crash();

        //add points to list
        if (alive){
            Point point = new Point(ox, oy);
            points.add(point);
        }
    }

    //when crash
    void crash(){
        if (xDir == 0 && yDir == 0){
            alive = false;
        }
    }

    //random initial position
    void getRandomStart(){
        x = size*random.nextInt(50);
        y = size*random.nextInt(50);
    }


//get score
    int getScore(){
        int score = points.size();
        return score;

    }

    //write file to leaderboard
    void leaderBoard(){
        int sco = getScore();
        Path file = Paths.get("leaderboard.txt");
        String s = String.valueOf(sco) + "\n"  ;
        byte [] data = s.getBytes();
        OutputStream output = null;
        try {
            output = new BufferedOutputStream(Files.newOutputStream(file,APPEND));
            output.write(data );

            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    boolean getAlive(){
        return alive;
    }

    //draw light cycle, draw line
    void draw(Graphics graphics) {

        graphics.setColor(color);
        graphics.fillRect(x,y,size,size);

    for(Point point : points) {
        graphics.fillRect(point.x,point.y,size,size);
    }

    }
//restart
    void restart(){
        getRandomStart();
        xDir = VELOCITY;
        yDir = 0;
        this.size = size;
        this.color = color;
        alive = true;
        points.clear();
    }
}
