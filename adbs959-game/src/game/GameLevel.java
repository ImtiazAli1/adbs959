package game;

import city.cs.engine.SoundClip;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

public abstract class GameLevel extends World {

    /**character and gate is on every level */
    public Person person;
    public Gate gate;

    public GameLevel(Game game){

        gate = new Gate(this);
        /** gatePosition() is set in every level, used to determine the starting position of the gate */
        gate.setPosition(gatePosition());
        /**collision listener that acts on the gate and character */

    }

    public void Populate(Game game){
        person = new Person(this);
        /**startPosition() is set in every level, used to determine the starting position of the character */
        person.setPosition(startPosition());
        /**collision listener that acts on the character and enemies to detect hits */
        LifeCollision lifeCollision = new LifeCollision(person,this);
        person.addCollisionListener(lifeCollision);
        /**collision listener that acts on the coin class and character, used to collect coins */
        CoinCollision coinCollision = new CoinCollision(person);
        person.addCollisionListener(coinCollision);
        /**collision listener allowing the player to move to the next level */
        LevelCollision levelCollision = new LevelCollision(this, game);
        gate.addCollisionListener(levelCollision);
        ShootCollision shootCollision = new ShootCollision(person);
        gate.addCollisionListener(shootCollision);
    }

    public Person getPerson(){return person;}
    public void setPerson(Person p){
        person = p;
    }
    public Gate getGate(){return gate;}


    public abstract boolean isComplete();
    /**used to set the position of the player in the beginning of the level */
    public abstract Vec2 startPosition();
    /**sets the position of the 'gate', used to transition to the next level */
    public abstract Vec2 gatePosition();
    public abstract String getLevelName();
    /**used to get the background attached to each level */
    public abstract Image getBackground();
}
