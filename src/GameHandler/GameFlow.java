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
     *
     * @param ar animation runner for this game.
     * @param ks keyboard sensor of the user.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * return the levels of the game.
     *
     * @param levels levels of the game to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter scoreCounter = new Counter();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, scoreCounter, this.animationRunner, this.keyboardSensor);
            level.initialize();
            level.run();

            if (level.looseAllBalls()) {
                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(false, scoreCounter)));
                this.animationRunner.close();
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(true, scoreCounter)));
        this.animationRunner.close();
    }
}

