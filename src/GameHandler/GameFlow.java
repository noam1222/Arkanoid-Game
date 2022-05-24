package GameHandler;

import Helpers.Counter;
import Levels.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * class that handle the game levels display and run.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;

    /**
     * constructor - initialize this game flow object.
     * @param ar animation runner for this game.
     * @param ks keyboard sensor of the user.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * reun the levels of the game.
     * @param levels levels of the game to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter scoreCounter = new Counter();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, scoreCounter, this.animationRunner, this.keyboardSensor);

            level.initialize();

            level.run();

            if (level.looseAllBalls()) {
                animationRunner.run(new EndScreen(false, scoreCounter, this.keyboardSensor));
                this.animationRunner.close();
           }
        }
        animationRunner.run(new EndScreen(true, scoreCounter, this.keyboardSensor));
        this.animationRunner.close();
    }
}

