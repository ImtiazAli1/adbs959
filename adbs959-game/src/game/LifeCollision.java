package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**collision class for when an enemy and player collide*/
public class LifeCollision implements CollisionListener {

    private Person person;
    private GameLevel level;
    public LifeCollision(Person person, GameLevel level) {
        this.person = person;
        this.level = level;
    }

    BodyImage damage = new BodyImage("data/player/damage.gif", 2.5f);
    BodyImage die = new BodyImage("data/player/die/die.gif", 2.5f);

    private SoundClip PersonHit;
    private SoundClip PersonLose;

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Enemy || e.getOtherBody() instanceof Slime
                || e.getOtherBody() instanceof Spike || e.getOtherBody() instanceof Boss
                || e.getOtherBody() instanceof Meteor) {
            person.LoseLife();
            if (person.getLifeCount() >=1) {
                person.jump(7.5f);
                System.out.println("Life Lost! "+person.getLifeCount()+" lives remaining");
                person.removeAllImages();
                person.addImage(damage);
                /**returns the player to the start position to stop them from being killed instantly the same way*/
                person.setPosition(level.startPosition());
                try{
                    /**if the player is hit, a sound clip will play*/
                    PersonHit = new SoundClip("data/soundeffects/PersonHit1.wav");
                    PersonHit.play();
                }   catch (UnsupportedAudioFileException | IOException | LineUnavailableException error){
                    System.out.println(error);
                }
            } else if (person.getLifeCount() == 0) {
                System.out.println("Game over");
                person.removeAllImages();
                person.addImage(die);
                level.stop();
                try{
                    PersonLose = new SoundClip("data/soundeffects/PersonLose.wav");
                    PersonLose.play();
                }   catch (UnsupportedAudioFileException | IOException | LineUnavailableException error){
                    System.out.println(error);
                }
            }
        }
    }
}
