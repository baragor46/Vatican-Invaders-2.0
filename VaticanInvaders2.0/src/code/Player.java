package code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Spaceship {

   BufferedImage skin;
   static public int playerX = 1920/2-45;
   static public int playerY = 1080-180;
   static int width = 90;
   static int height = 90;
   int lives;

   public Player() throws IOException {
       this.setBounds(playerX,playerY,width,height);
       skin = ImageIO.read(new File("grafiki/7946058_medium.jpg"));
        lives = 3;
   }

   public void paintComponent(Graphics g){

       g.drawImage(skin,playerX,playerY,this);
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,30));
       g.drawString("Lives:" + lives,10,1000);
   }

    @Override
    public void moveright() {
       playerX+=20;
    }

    @Override
    public void moveleft() {
       playerX-=20;
    }




}
