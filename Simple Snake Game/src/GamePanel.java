import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 750;
    static final int UNIT_SIZE = 50;  // size of items
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/ UNIT_SIZE; // the maximum units gathered in the window
    static final int DELAY = 175;
    final int x[] = new int [GAME_UNITS];    //snake is not bigger than window
    final int y[] = new int [GAME_UNITS];
    int bodyParts = 6;
    int appleEaten;
    int appleX;  //where apple is located  on X coordinate
    int appleY;  // randomly apple located on Y coordinate
    char direction = 'R'; // snake begins by going Right
    boolean running = false;
    Timer timer;
    Random random; // instance of Random class


     //constructor
    GamePanel(){

        random = new Random(); // finish creating instance of Random class
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT )); // size of Window
        this.setBackground(Color.black);
        this.setFocusable(true); // add focus ability
        this.addKeyListener(new MyKeyAdapter());

        //after finish of construction we start the game
        startGame();
    }

    public void startGame(){
        //create new apple
        newApple();
        running = true;
        // timer is not greater than delay value otherwise it will crash
        timer = new Timer (DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g){
             super.paintComponent(g);
             draw(g);

    }

    public void draw(Graphics g){
        //turn it into matrix grid so it is easier to see

        if(running) {
            /*
            //Panel is made into pixels that give the whole number of items
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                //draw lines across x - and y - axis
                //spaces along x-axis
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);

                //spaces along y - axis
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

                //each item takes one of spaces
            }

             */
                    //draw apple
                    g.setColor(Color.red);
                    g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);


            //Draw the snake
            // for loop that goes through all the bodyParts
            for (int i = 0; i < bodyParts; i++) {

                //first is the head if there is nothing
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    //body randomly changes color
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            //Draw the current score
            g.setColor(Color.red);
            g.setFont(new Font( "Ink Free", Font.BOLD,40));
            //create font metrics
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("SCORE: " + appleEaten, (SCREEN_WIDTH - metrics.stringWidth("SCORE: " + appleEaten ))/2, g.getFont().getSize());
        }
        else {
            gameOver( g);
        }

    }

    public void newApple() {
        //apple appears on X- axis
       appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
       //apple appears on Y-axis
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    //method for moving the snake
    public void move(){
        for (int i = bodyParts; i>0; i--){
            //shift bodyParts of snake
            x[i] = x[i-1];
            y[i] = y [i -1];

        }

        // cases for where the snake is headed
        switch(direction){
            case'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple(){
        //if you touch the apple, increase bodyparts and create new apple
            if((x[0] == appleX ) && (y[0] == appleY)){
                    bodyParts++;
                    appleEaten ++;
                    //new apple after it is eaten
                    newApple();
            }
    }

    public void checkCollisions(){
        ///if head of snake collides with its body
        for (int i = bodyParts;i>0;i--){

            //if head collides with the body, GAME OVER
            if(( x[0] == x[i]) &&  y[0] == y[i]){
                running = false;
            }
        }
        //check if head touches left border
        if (x[0] < 0){
            running = false;
        }

        //check if head touches right border
        if (x[0] > SCREEN_WIDTH){
            running = false;
        }

        //check if head touches top border
        if (y[0] < 0){
            running = false;
        }

        //check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT){
            running = false;
        }

        //stop the timer
        if (!running){
            timer.stop();
        }
    }

    public void gameOver(Graphics g){
        //Score is printed
        //Draw the current score
        g.setColor(Color.red);
        g.setFont(new Font( "Ink Free", Font.BOLD,40));
        //create font metrics
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("SCORE: " + appleEaten, (SCREEN_WIDTH - metrics1.stringWidth("SCORE: " + appleEaten ))/2, g.getFont().getSize());

        //GAME OVER text
        g.setColor(Color.red);
        g.setFont(new Font( "Ink Free", Font.BOLD,75));
        //create font metrics
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER"))/2, SCREEN_HEIGHT/2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running){
            //when game is running move the snake
            move();
            //check if we ran into the apple
            checkApple();
            //check collisions
            checkCollisions();
        }
        //if it doesn't run
        repaint();
    }

    //Create an inner class for controlling the snake

    public class MyKeyAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){

                case KeyEvent.VK_LEFT:
                    //limit the user controlling the snake head to 90 degrees
                    //if direction is not Right then it goes Left
                    if (direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    //limit the user controlling the snake head to 90 degrees
                    //if direction is not LEFT then it goes RIGHT
                    if (direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    //limit the user controlling the snake head to 90 degrees
                    //if direction is not equal to DOWN then it goes UP
                    if (direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    //limit the user controlling the snake head to 90 degrees
                    //if direction is not equal to UP then it goes DOWN
                    if (direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }

        }
    }
}
