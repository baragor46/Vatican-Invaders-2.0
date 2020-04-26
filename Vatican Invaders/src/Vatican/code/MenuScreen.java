package Vatican.code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuScreen extends JPanel {
    static int highscore;
    BufferedImage background;

    public MenuScreen() throws IOException {
        this.setBounds(0,0,VaticanInvaders.width,VaticanInvaders.height);
        //BufferedReader fr = new BufferedReader(new FileReader("C:\\IntellijProjekty\\GuiProjektSwing\\scores\\highscores.txt"));
        //highscore = Integer.parseInt((fr.readLine()));
        Scanner in = new Scanner(new File("scores/highscores.txt"));
        highscore = in.nextInt();

        this.background = ImageIO.read(new File("grafiki/menu.jpg"));

    }

    public void paintComponent(Graphics g){
        String hs = Integer.toString(highscore);
        g.drawImage(this.background,0,0,this);
        g.setColor(Color.yellow);
        g.setFont(new Font("serif",Font.BOLD,50));
        g.drawString("Highest score: " + hs,VaticanInvaders.width/2 - 400,
                VaticanInvaders.height/2);
        g.drawString("Press ENTER to start", VaticanInvaders.width/2-420,VaticanInvaders.height/2 + 100);
    }

}
