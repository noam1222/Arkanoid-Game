// 209407162 Noam Maimon

package GameHandler;

import Collidables.Block;
import Helpers.Counter;
import Sprites.Ball;
import Notification.HitListener;

/**
 * class that in charge of removing balls from the game, as well as keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * constructor - initialize ball remover object.
     * @param game game to remove the blocks from.
     * @param remainingBalls the number of blocks in this game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * remove the receiving ball from the game.
     * @param beingHit the block that being hit.
     * @param hitter the Sprites.Ball that hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}
