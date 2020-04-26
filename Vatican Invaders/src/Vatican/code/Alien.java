package Vatican.code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Alien extends Spaceship {

    BufferedImage skin;
    static int width = 80;
    static int height = 80;
    boolean isAlive;
    int lives = 1;

    int x;
    int y;

    public Alien(int x, int y) throws IOException {
        this.setBounds(x,y,width,height);
        skin = ImageIO.read(new File("grafiki/gf-eLVp-yYWY-C8b1_spalony-dom-jezus-milosierny-664x442-nocrop.jpg"));
        this.x=x;
        this.y=y;
        this.isAlive = true;
    }

    public void paint(Graphics2D g){
        g.drawImage(skin,this.x,this.y,this);
    }



    @Override
    public void moveright() {
        this.x += 20;
    }

    @Override
    public void moveleft() {
        this.x -=20;
    }
    public void moveDown(){
        this.y += height;
    }


}
