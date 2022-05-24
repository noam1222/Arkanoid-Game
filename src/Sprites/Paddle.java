// 209407162 Noam Maimon

package Sprites;

import Collidables.Block;
import Collidables.Collidable;
import GameHandler.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Sprites.Paddle class - block who can move in the screen and also act different in case of hit by ball.
 */
public class Paddle implements Sprite, Collidable {
    private final int paddleMovement;
    private final KeyboardSensor keyboard;
    private Block paddleBlock;
    private final int leftBorder;
    private final int rightBorder;

    /**
     * constructor.
     *
     * @param keyboard       keyboard sensor to change location of this paddle if user pressed.
     * @param paddleBlock    this paddle block.
     * @param leftBorder     the left border of the screen that this paddle is going to be played in.
     * @param rightBorder    the right border of the screen that this paddle is going to be played in.
     * @param paddleMovement this paddle speed.
     */
    public Paddle(KeyboardSensor keyboard, Block paddleBlock, int leftBorder, int rightBorder, int paddleMovement) {
        this.keyboard = keyboard;
        this.paddleBlock = paddleBlock;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.paddleMovement = Math.abs(paddleMovement);
    }

    /**
     * draw this paddle on the screen.
     *
     * @param d the surface to draw thw sprite in.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.paddleBlock.drawOn(d);
    }

    /**
     * <p>notify this paddle that time has passed: <br/>
     * - if user pressed left arrow in keyboard - move this paddle left,<br/>
     * - if user pressed right arrow in keyboard - move this paddle right.</p>
     */
    @Override
    public void timePassed() {
        double width = this.paddleBlock.getCollisionRectangle().getWidth();
        double height = this.paddleBlock.getCollisionRectangle().getHeight();
        Color color = this.paddleBlock.getColor();

        Point newUpperLeft = this.paddleBlock.getCollisionRectangle().getUpperLeft();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            newUpperLeft = newUpperLeft.getPointInDistance(-this.paddleMovement, 0);
            if (newUpperLeft.getX() < this.leftBorder) {
                newUpperLeft = newUpperLeft.getPointInDistance(this.leftBorder - newUpperLeft.getX(), 0);
            }
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            newUpperLeft = newUpperLeft.getPointInDistance(this.paddleMovement, 0);
            if (newUpperLeft.getX() + width > this.rightBorder) {
                newUpperLeft = newUpperLeft.getPointInDistance(this.rightBorder
                        - (newUpperLeft.getX() + width), 0);
            }
        }

        Rectangle newPaddleRectangle = new Rectangle(newUpperLeft, width, height);

        this.paddleBlock = new Block(newPaddleRectangle, color);
    }

    /**
     * get the rectangle shape of this paddle.
     *
     * @return the rectangle shape of this paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleBlock.getCollisionRectangle();
    }

    /**
     * notify this paddle that ball hit him.
     *
     * @param hitter the ball that hit this sprite.
     * @param collisionPoint  the collision point in the collidable object.
     * @param currentVelocity velocity of the object we collided with.
     * @return <p>new velocity of the ball:<br/>
     * - if hit in region 1 of this paddle: change angle to 300 degrees.<br/>
     * - if hit in region 2 of this paddle: change angle to 330 degrees.<br/>
     * - if hit in middle region of this paddle: change angle up.<br/>
     * - if hit in region 4 of this paddle: change angle to 60 degrees.<br/>
     * - if hit in region 5 of this paddle: change angle to 30 degrees.<br/>
     * - if hit in the sides of this paddle: act like regular Block object.</p>
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleWidth = this.paddleBlock.getCollisionRectangle().getWidth();
        double region = paddleWidth / 5;
        double collisionXInRelationToPaddle = collisionPoint.getX()
                - this.paddleBlock.getCollisionRectangle().getUpperLeft().getX();

        double speed = currentVelocity.getSpeed();
        if (collisionXInRelationToPaddle <= region) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionXInRelationToPaddle <= 2 * region) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
        } else if (collisionXInRelationToPaddle <= 3 * region) {
            currentVelocity.setDy(-currentVelocity.getDy());
        } else if (collisionXInRelationToPaddle <= 4 * region) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
        } else if (collisionXInRelationToPaddle <= 5 * region) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
        } else {
            // hit vertical borders of paddle
            currentVelocity = this.paddleBlock.hit(hitter, collisionPoint, currentVelocity);
        }
        currentVelocity.setDy(-Math.abs(currentVelocity.getDy()));

        return currentVelocity;
    }

    /**
     * add this paddle to game g.
     *
     * @param g the Game.Game object to add this paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
