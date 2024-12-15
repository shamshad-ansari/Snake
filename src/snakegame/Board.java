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

    private  boolean inGame = true;
    private boolean isLeft = false;
    private boolean isRight = true;
    private boolean isUp = false;
    private boolean isDown = false;


    private Timer timer;


    Board(){
        addKeyListener(new TAdapter());
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
        int RANDOM_POSITION = 29;
        int r = (int)(Math.random()* RANDOM_POSITION);
        apple_x = r * DOT_SIZE;

        int y = (int)(Math.random()* RANDOM_POSITION);
        apple_y = y * DOT_SIZE;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g){
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);

            for (int i = 0 ; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    public void move(){
        for (int i = dots;  i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if (isLeft){
            x[0] -= DOT_SIZE;
        }
        if(isRight){
            x[0] += DOT_SIZE;
        }
        if(isUp){
            y[0] -= DOT_SIZE;
        }
        if(isDown){
            y[0] += DOT_SIZE;
        }

    }
    public void appleEaten(){
        if((x[0] == apple_x) && (y[0] == apple_y)){
            dots++;
            locateApple();
        }
    }

    public void checkCollision() {
        for(int i = dots; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
                break;
            }
        }

        if (y[0] >= 300) {
            inGame = false;
        }
        if (x[0] >= 300) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        String msg = "Game Over!";
        Font font = new Font("SAN_SERIF", Font.BOLD, 14);
        FontMetrics matrices = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg, (300 - matrices.stringWidth(msg)) / 2, 300/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            appleEaten();
            checkCollision();
            move();
        }
        repaint();
    }

    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && (!isRight)) {
                isLeft = true;
                isUp = false;
                isDown = false;
            }
            if (key == KeyEvent.VK_RIGHT && (!isLeft)) {
                isRight = true;
                isUp = false;
                isDown = false;
            }
            if (key == KeyEvent.VK_UP && (!isDown)) {
                isLeft = false;
                isUp = true;
                isRight = false;
            }
            if (key == KeyEvent.VK_DOWN && (!isUp)) {
                isLeft = false;
                isDown = true;
                isRight = false;
            }
        }
    }
}
