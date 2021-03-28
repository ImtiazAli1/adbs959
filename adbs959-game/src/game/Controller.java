package game;

import city.cs.engine.*;
import jdk.dynalink.linker.support.SimpleLinkRequest;
import org.jbox2d.common.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.*;
import java.io.IOException;

/** player is controllable by keyboard, the view must stay on the JFrame, otherwise the key controls will not work */
public class Controller implements KeyListener {

    BodyImage image = new BodyImage("data/player/standing.png", 2.5f);
    BodyImage WalkLeft = new BodyImage("data/player/walkleft/walkleft.gif", 2.5f);
    BodyImage WalkRight = new BodyImage("data/player/walkright/walkright.gif", 2.5f);
    BodyImage Crouch = new BodyImage("data/player/crouch/crouch.gif", 2.5f);

    private Game game;
    private static float JumpSpeed=9;
    private static float StopSpeed=0;
    private static float WalkSpeed=3;

    public Controller(Game game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            Vec2 v = game.getLevel().getPerson().getLinearVelocity();
            /** game.getLevel().getPerson() is used instead of person, because when loading a game file, the keyboard is attached to the old player
            a new player is created therefore it would not work. This line returns the person in the current level in the game */
            if(Math.abs(v.y)<0.01f){
                game.getLevel().getPerson().jump(JumpSpeed);
            }
        }else if(code == KeyEvent.VK_A){
            game.getLevel().getPerson().startWalking(-WalkSpeed);
            game.getLevel().getPerson().removeAllImages(); /*old images are removed, new ones are added to create walking effect*/
            game.getLevel().getPerson().addImage(WalkLeft);
        }else if(code == KeyEvent.VK_D){
            game.getLevel().getPerson().startWalking(WalkSpeed);
            game.getLevel().getPerson().removeAllImages();
            game.getLevel().getPerson().addImage(WalkRight);
        }else if(code == KeyEvent.VK_S){
            game.getLevel().getPerson().removeAllImages();
            game.getLevel().getPerson().addImage(Crouch);
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_A){
            game.getLevel().getPerson().startWalking(StopSpeed);
            game.getLevel().getPerson().removeAllImages();
            game.getLevel().getPerson().addImage(image);
        }   else if(code == KeyEvent.VK_D){
            game.getLevel().getPerson().startWalking(StopSpeed);
            game.getLevel().getPerson().removeAllImages();
            game.getLevel().getPerson().addImage(image);
        }   else if(code == KeyEvent.VK_S){
            game.getLevel().getPerson().removeAllImages();
            game.getLevel().getPerson().addImage(image);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

}
