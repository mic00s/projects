import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){


        this.add(new GamePanel());  // starts new GamePanel
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // gets jframe and fits around all components added to the frame
        this.setVisible(true);
        this.setLocationRelativeTo(null); //Window is initiated at the center of screen

    }
}
