package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

import javax.swing.*;

import static java.lang.Math.abs;

public class Tracker implements StepListener {
    private Game game;
    private Person person;
    public Tracker(Game game, Person person) {
        this.game = game;
        this.person = person;
    }
    public void preStep(StepEvent e) {

        /**stops the player from 'falling off' the map, if the player falls off they are sent to the top of the map*/
        if(person.getPosition().y<-13){
            person.setPosition(new Vec2(person.getPosition().x,abs(person.getPosition().y)));
        }
        /**stops the game if the player 'dies'*/
        if(person.getLifeCount()==0){
            game.getLevel().stop();
        }
    }
    public void postStep(StepEvent e) {
    }
}