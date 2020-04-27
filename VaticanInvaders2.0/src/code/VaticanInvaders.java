package code;

import javax.swing.*;
import java.io.IOException;

public class VaticanInvaders extends JFrame {

    static int width = 1920;
    static int height = 1080;
    public VaticanInvaders() throws IOException {

        Gameplay gameplay = new Gameplay();

        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Vatican Invaders");
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.add(gameplay);

    }
}
