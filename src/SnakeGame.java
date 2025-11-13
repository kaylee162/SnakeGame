import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = (WIDTH * HEIGHT) / (DOT_SIZE * DOT_SIZE);
    private final int DELAY = 140;

    private final ArrayList<Point> snake;
    private Point food;
    private char direction;
    private boolean running;

    private int score = 0;
    private int highScore = 0;
    private final String SCORE_FILE = "highscore.txt";

    public SnakeGame() {
        snake = new ArrayList<>();
        direction = 'R';
        running = false;

        loadHighScore();

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != 'D') direction = 'U';
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') direction = 'D';
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') direction = 'L';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') direction = 'R';
                        break;
                }
            }
        });

        initGame();
    }

    private void loadHighScore() {
        try {
            java.io.File file = new java.io.File(SCORE_FILE);
            if (file.exists()) {
                java.util.Scanner scanner = new java.util.Scanner(file);
                if (scanner.hasNextInt()) {
                    highScore = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (Exception e) {
            System.out.println("Could not load high score.");
        }
    }

    private void saveHighScore() {
        try {
            java.io.PrintWriter out = new java.io.PrintWriter(SCORE_FILE);
            out.println(highScore);
            out.close();
        } catch (Exception e) {
            System.out.println("Could not save high score.");
        }
    }

    private void initGame() {
        score = 0;
        snake.clear();
        snake.add(new Point(5, 5));
        spawnFood();
        running = true;

        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    private void spawnFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / DOT_SIZE);
        int y = rand.nextInt(HEIGHT / DOT_SIZE);
        food = new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (running) {
            // Draw food
            g.setColor(Color.RED);
            g.fillRect(food.x * DOT_SIZE, food.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);

            // Draw snake
            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x * DOT_SIZE, p.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);
            }

            // ===== NEW SCORE UI =====
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("Score: " + score, 10, 20);
            g.drawString("High Score: " + highScore, 10, 40);
            // =========================

        } else {
            showGameOver(g);
        }
    }


    private void showGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Game Over", WIDTH / 2 - 30, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
            checkFood();
            repaint();
        }
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case 'U':
                newHead.y--;
                break;
            case 'D':
                newHead.y++;
                break;
            case 'L':
                newHead.x--;
                break;
            case 'R':
                newHead.x++;
                break;
        }

        snake.add(0, newHead);
        snake.remove(snake.size() - 1); // Remove the tail
    }

    private void checkCollision() {
        Point head = snake.get(0);

        boolean collided = false;

        // Wall collision
        if (head.x < 0 || head.x >= WIDTH / DOT_SIZE ||
            head.y < 0 || head.y >= HEIGHT / DOT_SIZE) {
            collided = true;
        }

        // Self collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                collided = true;
                break;
            }
        }

        // If any collision happened, stop game + handle high score
        if (collided) {
            running = false;

            if (score > highScore) {
                highScore = score;
                saveHighScore();
            }
        }
    }

    private void checkFood() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            snake.add(new Point(food)); 
            score += 10;                
            spawnFood();                
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();

        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}