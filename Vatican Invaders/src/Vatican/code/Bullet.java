package Vatican.code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet {
    int x;
    int y;
     int vy;
    static int radius = 30;
    int direction;
    Spaceship owner;
    Image bI;
    public Bullet(int x,int y, int direction,int vy, Spaceship owner) throws IOException {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.owner = owner;
        this.vy = vy;
        bI = ImageIO.read(new File("grafiki/kremowkaPocisk.jpg"));

    }

    public void fly(){
        this.y += vy;
    }

    public void paint(Graphics g){
        //zrób że papież strzela świętym graalem, a kosmici krzyżami
        g.drawImage(bI,this.x,this.y,null);
        //g.setColor(Color.red);
        //g.fillOval(x,this.y, radius, radius);
    }
}
