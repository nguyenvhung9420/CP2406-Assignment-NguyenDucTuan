//import modules
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


// this is what an anonymous inner class does... kind of :)
class fAnon implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

//Main class
public class Main  {
    public static void main(String[] args)  {

        //getting input for name and color of two players

        String a = JOptionPane.showInputDialog("Enter 1st player name: ");


        Color c = JColorChooser.showDialog(null, "Choose a color for your Light Cycle", Color.RED);

        String b = JOptionPane.showInputDialog("Enter 2nd player name: ");

        Color d = JColorChooser.showDialog(null, "Choose a color for your Light Cycle", Color.RED);


        //Create frame of game, game title
        JFrame frame = new JFrame("Light Cycle");
        Grid grid = new Grid();

        //Setting up two light cycles
        LightCycle car1 = new LightCycle(5, c);
        LightCycle car2 = new LightCycle(5, d);


        //display names, scores and leaderboard on screen
        JLabel name = new JLabel("Name: " + a + " | " );
        JLabel name2= new JLabel("Name: " + b + " | " );

        final JLabel scoreDisplay = new JLabel("Score: " );
        final JLabel scoreDisplay2 = new JLabel("Score: ");
        LeaderBoard leaderBoard = new LeaderBoard();


        name.setForeground(Color.BLACK);
        name2.setForeground(Color.BLACK);


        name.setBackground(Color.WHITE);
        name2.setBackground(Color.WHITE);
        frame.setSize(600,800);

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.add(name, BorderLayout.NORTH);


        scoreBoard.add(scoreDisplay, BorderLayout.SOUTH);

        scoreBoard.add(name2,BorderLayout.NORTH);
        scoreBoard.add(scoreDisplay2,BorderLayout.SOUTH);

        /**scoreBoard.add(button, BorderLayout.EAST);**/

        frame.add(scoreBoard, BorderLayout.SOUTH);
        frame.add(leaderBoard, BorderLayout.EAST);


        //get random position of light cyclels in grid
        car1.getRandomStart();
        car2.getRandomStart();



        // frame-by-frame animation loop
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car1.move(grid.getSize(),car2.getPoints()); //move car1
                car2.move(grid.getSize(), car1.getPoints()); //move car2

                //display scores
                scoreDisplay.setText("Score: " + String.valueOf(car1.getScore()) + "  ------  ");
                scoreDisplay2.setText("Score: " + String.valueOf(car2.getScore()));


                //when car 1 dies
                if (!car1.getAlive()) {

                    car1.leaderBoard(); //save scores to leaderboard
                    car2.leaderBoard();

                    //display winner
                    int selectedOption = JOptionPane.showConfirmDialog(frame, b + " won!" +
                           "\n Do you want to play again?");

                    //restart
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        car1.restart();
                        car2.restart();

                        //close System
                    }
                    if (selectedOption == JOptionPane.NO_OPTION || selectedOption == JOptionPane.CANCEL_OPTION){
                        System.exit(0);
                    }
                }

                //if car2 dies
                if (!car2.getAlive()) {

                    car1.leaderBoard();     //save scores to leaderboard
                    car2.leaderBoard();

                    //display winner
                    int selectedOption = JOptionPane.showConfirmDialog(frame, a + " won!" +
                            "\n Do you want to play again?");

                    //restart
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        car1.restart();
                        car2.restart();

                    }

                    //close system
                    if (selectedOption == JOptionPane.NO_OPTION || selectedOption == JOptionPane.CANCEL_OPTION){
                        System.exit(0);
                    }
                }

                //repaint for each frame per second
                grid.repaint();

            }
        });

        //start timer
        timer.start();




        //Setting up key interaction with light cycle for car1
        grid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                e.getKeyCode();
                if (e.getKeyCode() == KeyEvent.VK_W){
                    car1.up();
                }
                if (e.getKeyCode() == KeyEvent.VK_S){
                    car1.down();
                }
                if (e.getKeyCode() == KeyEvent.VK_A){
                    car1.left();
                }
                if (e.getKeyCode() == KeyEvent.VK_D){
                    car1.right();
                }

            }
        });

        //Setting up key interaction with light cycle for car2
        grid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                e.getKeyCode();
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    car2.up();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    car2.down();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    car2.left();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    car2.right();
                }

            }
        });




        //add grid to frame
        frame.add(grid, BorderLayout.CENTER);

        //add light cycles to grid
        grid.addLightCycle(car1);
        grid.addLightCycle(car2);

        //set frame's properties
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}
