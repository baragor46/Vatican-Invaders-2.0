package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LostScreen extends JPanel {
    BufferedImage screen;
    public LostScreen() throws IOException {
        setBounds(0,0,VaticanInvaders.width,VaticanInvaders.height);
        this.screen = ImageIO.read(new File("grafiki/01b35c1852409413d90c94240dd4a5cc.jpg"));
    }

    public void paintComponent(Graphics g){
        g.drawImage(screen,0,0,this);
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.ITALIC,60));
        g.drawString("Wybiła 21:37 ... czas iść spać",VaticanInvaders.width/3-100,VaticanInvaders.height/2);
        g.setColor(Color.yellow);
        //g.drawString("Wynik: " + (2137-Gameplay.time),VaticanInvaders.width/3-100,VaticanInvaders.height/2+100);
        g.drawString("Naciśnij ESC",VaticanInvaders.width/3-100,VaticanInvaders.height/2+100);
    }
}
