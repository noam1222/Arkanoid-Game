import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.awt.Color;

/**
 * class of Arkanoid game handler.
 */
public class Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BORDER_THICK = 10;

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;

    /**
     * constructor - initialize this game object with new SpriteCollection and GameEnvironment objects.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * add Collidable object to the environment of this game.
     * @param c the Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add Sprite object to the sprites of this game.
     * @param s Sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * get the screen blocks that restrict the borders of this game.
     * @return Block array of the screen blocks that restrict the borders of this game.
     */
    private Block[] getScreenBlockBorders() {
        Rectangle u = new Rectangle(new Point(0, 0), WIDTH, BORDER_THICK);
        Rectangle r = new Rectangle(new Point(WIDTH - BORDER_THICK, 0), BORDER_THICK, HEIGHT);
        Rectangle l = new Rectangle(new Point(0, 0), BORDER_THICK, HEIGHT);
        Rectangle d = new Rectangle(new Point(0, HEIGHT - BORDER_THICK), WIDTH, BORDER_THICK);
        Block up = new Block(u, Color.BLACK);
        Block right = new Block(r, Color.BLACK);
        Block down = new Block(l, Color.BLACK);
        Block bottom = new Block(d, Color.BLACK);
        return new Block[]{up, right, down, bottom};
    }

    /**
     * check if received point is out of this game borders.
     * @param p point to check.
     * @return true if the point is out of borders, false otherwise.
     */
    private boolean isOutOfBorders(Point p) {
        return p.getX() < 0 || p.getX() > WIDTH || p.getY() < 0 || p.getY() >  HEIGHT;
    }

    /**
     * get random point that not in any of the collidable object in this game environment.
     * @return random point that not in any of the collidable object in this game environment.
     */
    private Point getRandomFreePoint() {
        java.util.Random random = new java.util.Random();
        Point p;
        do {
            double x = random.nextDouble() * WIDTH;
            double y = random.nextDouble() * HEIGHT;
            p = new Point(x, y);
        } while (!this.environment.isFreePoint(p));
        return p;
    }



    /**
     * // Initialize a new game: create the necessary objects and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);

        ArrayList<Ball> balls = new ArrayList<>();

        Ball b1 = new Ball(5, 1, 10, Color.red, this.environment);
        b1.setVelocity(10, 10);
        balls.add(b1);
        Ball b2 = new Ball(100, 200, 5, Color.yellow, this.environment);
        b2.setVelocity(5, 7);
        balls.add(b2);
        Ball b3 = new Ball(-15, 450, 15, Color.green, this.environment);
        b3.setVelocity(5, 7);
        balls.add(b3);
        Ball b4 = new Ball(600, 450, 10, Color.pink, this.environment);
        b4.setVelocity(5, 7);
        balls.add(b4);

        new Block(new Rectangle(new Point(650, 400), 50, 50), Color.darkGray).addToGame(this);
        new Block(new Rectangle(new Point(50, 400), 25, 50), Color.orange).addToGame(this);
        new Block(new Rectangle(new Point(300, 300), 100, 25), Color.magenta).addToGame(this);
        new Block(new Rectangle(new Point(85, 75), 10, 10), Color.CYAN).addToGame(this);

        for (Block b : this.getScreenBlockBorders()) {
            b.addToGame(this);
        }

        for (Ball ball : balls) {
            if (!this.environment.isFreePoint(ball.getCenter()) || isOutOfBorders(ball.getCenter())) {
                ball.setCenter(this.getRandomFreePoint());
            }
            ball.addToGame(this);
        }
    }

    /**
     * run the game - start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}