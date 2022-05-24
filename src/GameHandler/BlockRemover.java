// 209407162 Noam Maimon

package GameHandler;

import Collidables.Block;
import Helpers.Counter;
import Sprites.Ball;
import Notification.HitListener;

/**
 * class that in charge of removing blocks from the game, as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * constructor - initialize block remover object.
     * @param game game to remove the blocks from.
     * @param removedBlocks the number of blocks in this game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * remove the receiving block from the game.
     * @param beingHit the block that being hit.
     * @param hitter the Sprites.Ball that hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}