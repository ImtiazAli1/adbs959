package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level3 extends GameLevel implements ActionListener {

    private static BodyImage platformmedium = new BodyImage("data/platform3.png", 2f);
    private static BodyImage platformsmall = new BodyImage("data/smallplatform3.png", 1.25f);
    private static BodyImage wall = new BodyImage("data/wall3.png", 8f);
    private static BodyImage oldportal = new BodyImage("data/portal.gif", 3.5f);
    private static BodyImage portal = new BodyImage("data/closedportal.gif", 3.5f);
    private Image background;

    public Level3(Game game){
        super(game);

        background = new ImageIcon("data/bg3.gif").getImage();

        ShootCollision shootCollision = new ShootCollision(person);

        Gate gate1 = new Gate(this);
        gate1.removeAllImages();
        gate1.addImage(oldportal).setRotation(1.5f);
        gate1.setPosition(new Vec2(0f,-4f));
        gate1.addCollisionListener(shootCollision);
        getGate().removeAllImages();
        getGate().addImage(portal).flipHorizontal();

        Color color = new Color(0, 0, 0, 1);

        Shape PlatformShape = new BoxShape(3.5f, 1f);
        Shape PlatformShape1 = new BoxShape(1.5f, 0.5f);
        Shape WallShape = new BoxShape(1f, 4f);

        for(int i=0;i<2;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-3.5f+i*7f, -2));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
        }
        for(int i=0;i<2;i++){
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-15+i*15, -11));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++) {
            Body wall1 = new StaticBody(this, WallShape);
            wall1.setPosition(new Vec2(-8.25f+i*16.5f, -5));
            wall1.addImage(wall);
            wall1.setFillColor(color);
            wall1.setLineColor(color);
            wall1.addCollisionListener(shootCollision);
        }
        for(int i=0;i<3;i++) {
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-7f + i * 14f, 5));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++){
            Body platform2 = new StaticBody(this, PlatformShape);
            platform2.setPosition(new Vec2(-22+i*3, 7-i*9));
            platform2.addImage(platformmedium);
            platform2.setFillColor(color);
            platform2.setLineColor(color);
            platform2.addCollisionListener(shootCollision);
        }

        Body platforms = new StaticBody(this, PlatformShape1);
        platforms.setPosition(new Vec2(14f, -11));
        platforms.addImage(platformsmall);
        platforms.setFillColor(color);
        platforms.setLineColor(color);
        platforms.addCollisionListener(shootCollision);

        Body wall1 = new StaticBody(this, WallShape);
        wall1.setPosition(new Vec2(19.5f, -5));
        wall1.addImage(wall);
        wall1.setFillColor(color);
        wall1.setLineColor(color);

        /**'spikes' created damage player upon contact*/
        for(int i=0;i<3;i++) {
            Spike spike = new Spike(this);
            spike.setPosition(new Vec2(-6f+i*2, -3.75f));
            spike.rotate(3.14f);
            spike.addCollisionListener(shootCollision);
        }
        for(int i=0;i<3;i++) {
            Spike spike = new Spike(this);
            spike.setPosition(new Vec2(6f-i*2, -3.75f));
            spike.rotate(3.14f);
            spike.addCollisionListener(shootCollision);
        }
        for(int i=0;i<4;i++) {
            Spike spike = new Spike(this);
            spike.setPosition(new Vec2(10f, -2.1f-i*2f));
            spike.rotate(-1.57f);
            spike.addCollisionListener(shootCollision);
        }
        for(int i=0;i<4;i++) {
            Spike spike = new Spike(this);
            spike.setPosition(new Vec2(17.75f, -2-i*2f));
            spike.rotate(1.57f);
            spike.addCollisionListener(shootCollision);
        }
        for(int j=0;j<3;j++){
            for(int i=0;i<3;i++) {
                Spike spike = new Spike(this);
                spike.setPosition(new Vec2((-9f+i*2)+j*14, 3.3f));
                spike.rotate(3.14f);
                spike.addCollisionListener(shootCollision);
            }
        }

    }

    /**populate method*/
    @Override
    public void Populate(Game game) {
        super.Populate(game);
        ShootCollision shootCollision = new ShootCollision(person);

        for(int i=0;i<3;i++){
            Coin coin = new Coin(this);
            coin.setPosition(new Vec2(-1.5f+i*1.5f,0));
            coin.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++){
            Coin coin = new Coin(this);
            coin.setPosition(new Vec2(20f+i*2f,7f));
            coin.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++){
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(5.5f-i*14,6.5f));
            enemy.addCollisionListener(shootCollision);
        }
        Timer timer = new Timer(2000,this);
        timer.setInitialDelay(3000);
        timer.start();
        timer.setRepeats(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        CoinCollision coinCollision = new CoinCollision(person);
        Heart heart = new Heart(this);
        heart.setPosition(new Vec2(-20,8.5f));
        heart.addCollisionListener(coinCollision);
    }

    @Override
    public boolean isComplete() {
        if (getPerson().getCoinCount() >= 5)
            return true;
        else
            return false;
    }
    @Override
    public Vec2 startPosition(){
        return new Vec2(0, -6.5f);
    }
    @Override
    public Vec2 gatePosition(){
        return new Vec2(-24f, 9f);
    }
    @Override
    public String getLevelName() {
        return "Level 3";
    }
    @Override
    public Image getBackground(){
        return background;
    }
}
