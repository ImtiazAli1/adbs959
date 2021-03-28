package game;

import city.cs.engine.*;
import com.sun.jdi.ShortType;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**collision class for most body collisions in the game*/
public class ShootCollision implements CollisionListener {

    private Person person;
    public ShootCollision(Person person) {this.person = person;}

    BodyImage die = new BodyImage("data/enemy/boss/die/die.gif", 6);

    private SoundClip EnemyHit;

    @Override
    public void collide(CollisionEvent e) {
        try{
            EnemyHit = new SoundClip("data/soundeffects/EnemyHit.wav");
        }   catch (UnsupportedAudioFileException | IOException | LineUnavailableException error){
            System.out.println(error);
        }
        /**if the players 'shot' hits the enemy, the enemy will lose health and the shot is destroyed upon contact
        when the enemys health reaches 0, it will be destroyed*/
        if (e.getOtherBody() instanceof Shoot && e.getReportingBody() instanceof Enemy) {
            EnemyHit.play();
            if(((Enemy) e.getReportingBody()).getEnemyLife() <=3 && ((Enemy) e.getReportingBody()).getEnemyLife() >=1){
                ((Enemy) e.getReportingBody()).loseEnemyLife();
                System.out.println("Enemy Hit!");
            }if(((Enemy) e.getReportingBody()).getEnemyLife() == 0){
                person.addKill();
                person.getKillCount();
                e.getReportingBody().destroy();
            }
            e.getOtherBody().destroy();
            /**follows the same principle as the if statement above, but only requires one collision to destroy the body*/
        }else if (e.getOtherBody() instanceof Shoot && e.getReportingBody() instanceof Slime) {
            System.out.println("Enemy Hit!");
            person.addKill();
            person.getKillCount();
            EnemyHit.play();
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
            /**Collision listener that causes the players shot to be destroyed if it collides with bodies other than 'enemies'*/
        }else if(e.getOtherBody() instanceof Shoot && (e.getReportingBody() instanceof Gate || e.getReportingBody() instanceof Coin
                 || e.getReportingBody() instanceof StaticBody|| e.getReportingBody() instanceof Meteor)){
            e.getOtherBody().destroy();
            /**collision listener for the 'boss' class, same principle as enemy, except the boss has 25 health*/
        }else if(e.getOtherBody() instanceof Shoot && e.getReportingBody() instanceof Boss){
            if(((Boss) e.getReportingBody()).getBossLife()<=25 && ((Boss) e.getReportingBody()).getBossLife()>=1) {
                ((Boss) e.getReportingBody()).loseBossLife();
                e.getOtherBody().destroy();
                System.out.println("Boss hp:" +((Boss) e.getReportingBody()).getBossLife() );
            }if(((Boss) e.getReportingBody()).getBossLife() == 0){
                    e.getOtherBody().destroy();
                    e.getReportingBody().removeAllImages();
                    e.getReportingBody().addImage(die);
                    e.getReportingBody().destroy();
                    person.addKill();
                    person.getKillCount();
            }
        }
    }
}