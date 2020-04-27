package code;

import java.awt.*;
import java.io.IOException;

public class MapGenerator {
    public Alien aliens[][];
    int maxPos;
    int minPos;
    int speed = 20;
    int startPos = 200;
    public Obstacle obstacle[] = new Obstacle[2];

    public MapGenerator() throws IOException {
        aliens = new Alien[5][10];
        for (int i = 0; i < aliens.length; i++) {
            for (int j = 0; j < aliens[0].length; j++) {
                int odstep = j * ((VaticanInvaders.width-800)/20);

                aliens[i][j] = new Alien(200 + j*Alien.width + odstep, 10 + i * Alien.height);



            }
        }
        this.obstacle[0] = new Obstacle(20,700);
        this.obstacle[1] = new Obstacle(1145,700);


    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < aliens.length; i++) {
            for (int j = 0; j < aliens[0].length; j++) {
                if (aliens[i][j].isAlive) {
                    aliens[i][j].paint(g);
                }
            }
        }
        for(int i = 0; i < obstacle.length;i++){
            if(obstacle[i].alive)
                obstacle[i].paint(g);
        }
    }

    public void AlienController(){
        Thread t1 = new Thread(()->{

                while (true) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    movement();
                    try {
                        shoot();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        t1.start();
    }

    public void movement(){
        if(Gameplay.play) {
            if (aliens[0][0].x <= 20 && speed < 0) {
                speed *= -1;

            } else if (aliens[0][9].x + 240 + 2 * ((VaticanInvaders.width - 800) / 20) >= VaticanInvaders.width - 10) {
                speed *= -1;

            }
            for (int i = 0; i < aliens.length; i++) {
                for (int j = 0; j < aliens[0].length; j++) {
                    aliens[i][j].x += speed;
                    aliens[i][j].y += 1;
                }
            }

        }

    }

    public void shoot() throws IOException {
        if (Gameplay.play) {
            for (int i = 0; i < aliens.length; i++) {
                for (int j = 0; j < aliens[i].length; j++) {
                    int random = (int)(Math.random()*9);
                    if (isOnCoord(i, j) && aliens[i][j].isAlive && aliens[i][j].x >= Player.playerX && aliens[i][j].x <= Player.playerX + 80&&random>2) {
                        Gameplay.bullets.add(new Bullet(aliens[i][j].x + Alien.width / 2, aliens[i][j].y, 1, 10,aliens[i][j]));
                    }

                }
            }
        }
    }
    public boolean isOnCoord(int i, int j){
        if(i == aliens.length-1)
            return true;
        else if(aliens[i+1][j].isAlive)
            return false;
        else
            return true;


    }
}



