package Vatican.code;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class Obstacle{
    int lives;
    static int width = 500;
    static int height = 30;
    int x, y;
    boolean alive;


    public Obstacle(int x, int y){
        this.x = x;
        this.y = y;
        this.lives = 4;
        this.alive = true;

    }

    public void paint(Graphics g){
        if(this.lives >= 4)
            g.setColor(Color.red);
        else if(this.lives <= 2)
            g.setColor(Color.green);
        g.fillRect(this.x,this.y,width,height);
    }
}
