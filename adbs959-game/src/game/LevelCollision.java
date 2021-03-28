package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**allows the player to transistion to the next level with a collision*/
public class LevelCollision implements CollisionListener {

    private GameLevel level;
    private Game game;

    public LevelCollision(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Person && level.isComplete()){
            game.goToNextLevel();
        }
    }
}
