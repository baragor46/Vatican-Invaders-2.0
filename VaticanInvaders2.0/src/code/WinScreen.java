package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinScreen extends JPanel {
    BufferedImage screen;
    public WinScreen() throws IOException {
        setBounds(0,0,VaticanInvaders.width,VaticanInvaders.height);
        this.screen = ImageIO.read(new File("grafiki/WinScreen.jpg"));
    }

    public void paintComponent(Graphics g){
        g.drawImage(screen,0,0,this);
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.ITALIC,60));
        g.drawString("Imperium kremówkowe podbiło świat",VaticanInvaders.width/3-100,VaticanInvaders.height/2);
        g.drawString("Wynik: " + (2137-Gameplay.time),VaticanInvaders.width/3-100,VaticanInvaders.height/2+200);
        g.drawString("Naciśnij ESC",VaticanInvaders.width/3-100,VaticanInvaders.height/2+275);
    }
}
