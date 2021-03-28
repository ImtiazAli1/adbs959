package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/**creating the first level of the game*/
public class Level1  extends GameLevel{
    private static BodyImage platformmedium = new BodyImage("data/platform.png", 2f);
    private Image background;

    public Level1(Game game) {
        super(game);

        background = new ImageIcon("data/bg1.gif").getImage();
        ShootCollision shootCollision = new ShootCollision(person);

        Color color = new Color(0, 0, 0, 1); /**makes platforms invisible using alpha value
        /**Creating platforms for the player to stand on*/
        MovingPlatform movingPlatform = new MovingPlatform(this);
        movingPlatform.setPosition(new Vec2(-21,7f));
        movingPlatform.addCollisionListener(shootCollision);

        Shape PlatformShape = new BoxShape(3.5f, 0.5f);
        /**creating several platforms*/
        for(int i=0;i<3;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-21+i*11, -11));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<4;i++) {
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(21 - i * 11, 7.5f));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-2+i*9, -i * 2));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(10+i*8, -7+i*2));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }

    }

    /**populate method created for the implementation of save and load*/
    @Override
    public void Populate(Game game){
        super.Populate(game);

        /**Creating coins and collision listeners to collect the coins*/
        ShootCollision shootCollision = new ShootCollision(person);
        for(int j=0;j<2;j++){
            for(int i=0;i<3;i++){
                Coin coin = new Coin(this);
                coin.setPosition(new Vec2((-12+i*2)+j*11,-9.5f));
                coin.addCollisionListener(shootCollision);
            }
        }for(int j=0;j<2;j++){
            for(int i=0;i<2;i++){
                Coin coin = new Coin(this);
                coin.setPosition(new Vec2((-3f+i*2)+j*20,1.5f-j*5f));
                coin.addCollisionListener(shootCollision);
            }
        }
    }

    /**coins are required to complete the level objective*/
    @Override
    public boolean isComplete() {
        if (getPerson().getCoinCount() >= 10)
            return true;
        else
            return false;
    }

    /**setting the player and gate position*/
    @Override
    public Vec2 startPosition(){
        return new Vec2(-22f, -10.5f);
    }
    @Override
    public Vec2 gatePosition() {
        return new Vec2(23, 9.5f);
    }
    /**returns the level name for the save and load implementation*/
    @Override
    public String getLevelName() {
        return "Level 1";
    }
    @Override
    public Image getBackground(){
        return background;
    }
}