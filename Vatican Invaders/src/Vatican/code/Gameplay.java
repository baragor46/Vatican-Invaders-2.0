package Vatican.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
//przeskaluj kosmitów bo teraz jest za trudno!

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    public static boolean play = false;
    private int score = 0;

    private int totalAliens = 50;

    private Timer timer;

    //time
    static int time;


    Player player = new Player();

    static ArrayList<Bullet> bullets = new ArrayList<>();

    private MapGenerator map;
    //win screen
    WinScreen ws = new WinScreen();
    boolean won = false;
    //lost screen
    LostScreen ls = new LostScreen();
    boolean lost = false;
    //menu
    MenuScreen ms = new MenuScreen();
    boolean menu = true;

    public Gameplay() throws IOException {
        map = new MapGenerator();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 12;
        timer = new Timer(delay, this);
        timer.start();
        this.map.AlienController();
        this.time = 0;
        this.counter();

    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, VaticanInvaders.width, VaticanInvaders.height);
        //border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, VaticanInvaders.height - 3);
        g.fillRect(0, 0, VaticanInvaders.width - 10, 3);
        g.fillRect(1912, 0, 3, VaticanInvaders.height - 3);



        //map
        map.draw((Graphics2D) g);
        //Player

        player.paintComponent(g);
        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i)!=null)
                bullets.get(i).paint(g);
        }
        //time
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Time: 213.7 - " + this.time, 20, 30);
        //menu
        if(menu){
            ms.paintComponent(g);
        }
        //lost
        if (lost) {
            ls.paintComponent(g);
        }
        //won
        if(won)
            ws.paintComponent(g);

        g.dispose();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {

            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i) != null) {
                    bullets.get(i).y += bullets.get(i).vy * bullets.get(i).direction;

                    if (bullets.get(i).y > VaticanInvaders.height || bullets.get(i).y <= 0) {
                        bullets.set(i, null);
                    }
                }


            }

            for (int i = 0; i < map.aliens.length; i++) {
                for (int j = 0; j < map.aliens[0].length; j++) {
                    for (int k = 0; k < bullets.size(); k++) {
                        if (bullets.get(k) != null && map.aliens[i][j].isAlive) {
                            Rectangle bulletRec = new Rectangle(bullets.get(k).x, bullets.get(k).y, Bullet.radius, Bullet.radius);
                            Rectangle alienRec = new Rectangle(map.aliens[i][j].x, map.aliens[i][j].y, Alien.width, Alien.height);
                            if (bulletRec.intersects(alienRec) && bullets.get(k).owner instanceof Player) {
                                map.aliens[i][j].lives -= 1;
                                if(map.aliens[i][j].lives <= 0){
                                    map.aliens[i][j].isAlive = false;
                                    totalAliens -= 1;
                                }
                                bullets.set(k, null);

                            }
                        }
                    }
                }
            }
        for(int i = 0; i < bullets.size();i++){
            if(bullets.get(i)!=null) {
                Rectangle bulletRec = new Rectangle(bullets.get(i).x, bullets.get(i).y, Bullet.radius, Bullet.radius);
                Rectangle playerRec = new Rectangle(Player.playerX, Player.playerY, Player.width, Player.height);
                if (bulletRec.intersects(playerRec) && bullets.get(i).owner instanceof Alien) {
                    bullets.set(i, null);
                    player.lives -= 1;

                }
            }


        }

            for(int i = 0; i < map.obstacle.length; i ++){
                for(int j = 0; j < bullets.size();j++){
                    if(bullets.get(j)!=null && map.obstacle[i].alive){
                        Rectangle bulletRec = new Rectangle(bullets.get(j).x, bullets.get(j).y, Bullet.radius, Bullet.radius);
                        Rectangle obsRec = new Rectangle(map.obstacle[i].x,map.obstacle[i].y,Obstacle.width,Obstacle.height);
                        if(bulletRec.intersects(obsRec)){
                            bullets.set(j,null);
                            map.obstacle[i].lives-=1;
                            if(map.obstacle[i].lives==0){
                                map.obstacle[i].alive = false;
                            }
                        }
                    }
                }
            }



        if(player.lives<=0) {
            lost = true;
            play = false;
        }
        for(int i = 0; i < map.aliens.length; i++){
            for(int j = 0; j < map.aliens[0].length; j++)
                for(int k = 0; k < map.obstacle.length; k++){
                    Rectangle alienRec = new Rectangle(map.aliens[i][j].x, map.aliens[i][j].y, Alien.width, Alien.height);
                    Rectangle obsRec = new Rectangle(map.obstacle[k].x,map.obstacle[k].y,Obstacle.width,Obstacle.height);
                    if(alienRec.intersects(obsRec)&&map.aliens[i][j].isAlive)
                        lost=true;
                }

        }

            if(this.totalAliens <= 0) {
                play = false;
                won = true;
                if(213.7 - time > MenuScreen.highscore) {
                    try {
                       // FileWriter fw = new FileWriter("scores/highscores.txt");
                        //fw.write(Integer.toString(time));
                        PrintWriter zapis = new PrintWriter("scores/highscores.txt");
                        zapis.println(2137-time);
                        zapis.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }


            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (Player.playerX + 460 < VaticanInvaders.width)
                player.moveright();
            else
                Player.playerX = VaticanInvaders.width - 460;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (Player.playerX > 20)
                player.moveleft();
            else
                Player.playerX = 20;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && play) {
            try {
                bullets.add(new Bullet(Player.playerX + 45 - 17, Player.playerY, -1,20, player));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !lost && !won){
            play=true;
            menu=false;
        }
        if(won || lost){
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);

                }
        }



    }


    public void counter(){
        Thread t1= new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.time+=1;
            }
        });
        t1.start();
    }



}
