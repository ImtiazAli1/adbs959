package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/** collison listener for Coin class, increments the coinCount and plays a sound every time a coin is collected */
public class CoinCollision implements CollisionListener {

    private Person person;
    public CoinCollision(Person person) { this.person = person; }
    private SoundClip coinCollect;

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Coin){
            try{
                coinCollect = new SoundClip("data/soundeffects/CoinCollect.wav");
                coinCollect.play();
            }   catch (UnsupportedAudioFileException| IOException | LineUnavailableException e){
                System.out.println(e);
            }
            collisionEvent.getOtherBody().destroy();
            person.AddCoin();
            System.out.println("Coins Collected:"+person.getCoinCount());
        }else if(collisionEvent.getOtherBody() instanceof Heart){
            collisionEvent.getOtherBody().destroy();
            person.setLifeCount(person.getLifeCount()+1);
            System.out.println("+1 Life");
        }

    }

}
