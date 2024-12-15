package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board  extends JPanel implements ActionListener {
    private int dots;

    private Image apple;
    private Image dot;
    private Image head;

    private int apple_x;
    private int apple_y;

    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;

    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];

    private final int RANDOM_POSITION = 29;

    private Timer timer;


    Board(){
        setBackground(Color.BLACK);
        setFocusable(true);

        loadImages();
        initGame();
    }

    public void loadImages(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        this.apple = i1.getImage();

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        this.dot = i2.getImage();

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        this.head = i3.getImage();
    }

    public void initGame(){
        this.dots =3;

        for(int i = 0; i<dots; i++){
            y[i] = 50;
            x[i] = 50-i* DOT_SIZE;
        }

        locateApple();

        timer = new Timer(140, this);
        timer.start();
    }

    public void locateApple(){
        int r = (int)(Math.random()*RANDOM_POSITION);
        apple_x = r * DOT_SIZE;

        int y = (int)(Math.random()*RANDOM_POSITION);
        apple_y = y * DOT_SIZE;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(apple, apple_x, apple_y, this);

        for (int i = 0; i<dots; i++){
            if(i==0){
                g.drawImage(head, x[i], y[i], this);
            } else{
                g.drawImage(dot, x[i], y[i], this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void move(){
        for (int i = dots;  i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
//        x[0] += DOT_SIZE;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
}
