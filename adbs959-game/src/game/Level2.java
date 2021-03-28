package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.*;
/**second level in the game*/
public class Level2 extends GameLevel{

    /**'old portal' is created to show where the player appeared from*/
    private static BodyImage platformmedium = new BodyImage("data/1platform.png", 2f);
    private static BodyImage oldportal = new BodyImage("data/portal.gif", 3.5f);
    private static BodyImage portal = new BodyImage("data/closedportal.gif", 3.5f);
    private Image background;
    public Level2(Game game){

        super(game);

        background = new ImageIcon("data/bg2.gif").getImage();

        ShootCollision shootCollision = new ShootCollision(person);

        Color color = new Color(0, 0, 0, 1);

        Gate gate1 = new Gate(this);
        gate1.removeAllImages();
        gate1.addImage(oldportal).flipHorizontal();
        gate1.setPosition(new Vec2(-24f,10f));
        gate1.addCollisionListener(shootCollision);
        getGate().removeAllImages();
        getGate().addImage(portal).setRotation(1.5f);

        /**creating the platforms*/
        Shape PlatformShape = new BoxShape(3.5f, 0.5f);

        for(int i=0;i<3;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-21+i*21, 8));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-10+i*20, -1));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<3;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-10.5f+i*10.5f, -11));
            platforms.addImage(platformmedium).flipHorizontal();
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        Body platforms = new StaticBody(this, PlatformShape);
        platforms.setPosition(new Vec2(0, -5f));
        platforms.addImage(platformmedium).flipHorizontal();
        platforms.setFillColor(color);
        platforms.setLineColor(color);
        platforms.addCollisionListener(shootCollision);
        for(int i=0;i<2;i++){
            MovingPlatformSide movingPlatformSide = new MovingPlatformSide(this);
            movingPlatformSide.setPosition(new Vec2(-13.5f+i*21,8));
            movingPlatformSide.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++){
            MovingPlatformDown movingPlatformDown = new MovingPlatformDown(this);
            movingPlatformDown.setPosition(new Vec2(-20+i*40,1));
            movingPlatformDown.addCollisionListener(shootCollision);
        }


    }

    /**populate method for specific bodies*/
    @Override
    public void Populate(Game game) {
        super.Populate(game);
        ShootCollision shootCollision = new ShootCollision(person);

        for(int i=0;i<2;i++){
            Slime slime = new Slime(this);
            slime.setPosition(new Vec2(-12+i*20,1));
            slime.addCollisionListener(shootCollision);
        }
        for (int i = 0; i < 3; i++) {
                Coin coin = new Coin(this);
                coin.setPosition(new Vec2(-13+i*2, 9.5f));
                coin.addCollisionListener(shootCollision);
        }
    }

    @Override
    public boolean isComplete() {
        if (getPerson().getCoinCount() >= 3)
            return true;
        else
            return false;
    }
    @Override
    public Vec2 startPosition(){
        return new Vec2(-22f, 8.5f);
    }
    @Override
    public Vec2 gatePosition(){
        return new Vec2(0f, -4f);
    }
    @Override
    public String getLevelName() {
        return "Level 2";
    }
    @Override
    public Image getBackground(){
        return background;
    }
}
