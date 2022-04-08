import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class Paddle implements Sprite, Collidable {
    private final int paddleMovement;
    private final KeyboardSensor keyboard;
    private Block paddleBlock;
    private final int leftBorder;
    private final int rightBorder;

    public Paddle(KeyboardSensor keyboard, Block paddleBlock, int leftBorder, int rightBorder, int paddleMovement) {
        this.keyboard = keyboard;
        this.paddleBlock = paddleBlock;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.paddleMovement = Math.abs(paddleMovement);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.paddleBlock.drawOn(d);
    }

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

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleBlock.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = this.paddleBlock.hit(collisionPoint, currentVelocity);

        double paddleWidth = this.paddleBlock.getCollisionRectangle().getWidth();
        double region = paddleWidth / 5;
        double collisionXInRelationToPaddle = collisionPoint.getX()
                - this.paddleBlock.getCollisionRectangle().getUpperLeft().getX();

        double speed = newVelocity.getSpeed();
        if (collisionXInRelationToPaddle <= region) {
            newVelocity = Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionXInRelationToPaddle <= 2 * region) {
            newVelocity = Velocity.fromAngleAndSpeed(330, speed);
        } else if (collisionXInRelationToPaddle <= 3 * region) {
            newVelocity.setDy(-newVelocity.getDy());
        } else if (collisionXInRelationToPaddle <= 4 * region) {
            newVelocity = Velocity.fromAngleAndSpeed(30, speed);
        } else {
            newVelocity = Velocity.fromAngleAndSpeed(60, speed);
        }

        return newVelocity;
    }

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
