/**
 * class that in charge of score tracking in the Arkanoid game.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * constructor - initialize score tracking listener object.
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * add 5 points to score when ball hit block.
     * @param beingHit the block that being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}