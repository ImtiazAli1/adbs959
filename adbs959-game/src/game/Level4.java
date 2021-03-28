package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Level4 extends GameLevel implements ActionListener {

    private static BodyImage platformmedium = new BodyImage("data/platform4.png", 2f);
    private static BodyImage oldportal = new BodyImage("data/portal.gif", 3.5f);
    private Image background;

    public Level4(Game game){
        super(game);

        background = new ImageIcon("data/bg4.gif").getImage();

        ShootCollision shootCollision = new ShootCollision(person);
        Gate gate1 = new Gate(this);
        gate1.removeAllImages();
        gate1.addImage(oldportal).flipHorizontal();
        gate1.setPosition(new Vec2(-24f,9f));
        gate1.addCollisionListener(shootCollision);

        Color color = new Color(0, 0, 0, 1);

        /**platforms*/
        Shape PlatformShape = new BoxShape(3.5f, 1f);

        for(int i=0;i<4;i++) {
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-21+i*14f, 7f));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<4;i++) {
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-21+i*14f, 0f));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<3;i++) {
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-7+i*7, -10f));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }
        for(int i=0;i<2;i++) {
            Body platforms = new StaticBody(this, PlatformShape);
            platforms.setPosition(new Vec2(-21+i*42, -10f));
            platforms.addImage(platformmedium);
            platforms.setFillColor(color);
            platforms.setLineColor(color);
            platforms.addCollisionListener(shootCollision);
        }

        /**timer created that creates the 'meteor' after every fire*/
        Timer timer = new Timer(5000,this);
        timer.start();
    }

    @Override
    public void Populate(Game game) {
        super.Populate(game);

        /**gravity is changed to match the feel of the level*/
        person.setGravityScale(0.5f);

        ShootCollision shootCollision = new ShootCollision(person);

        for(int i=0;i<2;i++){
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(5.5f-i*14,3.5f));
            enemy.addCollisionListener(shootCollision);
        }
        Boss boss = new Boss(this);
        boss.setPosition(new Vec2(-7,-6.5f));
        boss.addCollisionListener(shootCollision);

    }
    /**creates the meteors every 5 seconds*/
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<2;i++){
        Meteor m = new Meteor(this);
        m.setPosition(new Vec2(-14+28*i,15+30*i));
        ShootCollision shootCollision = new ShootCollision(person);
        m.addCollisionListener(shootCollision);
        }
    }
    @Override
    public boolean isComplete() {
        if (getPerson().getKillCount() >= 3)
            return true;
        else
            return false;
    }
    @Override
    public Vec2 startPosition(){
        return new Vec2(-22.5f, 9f);
    }
    @Override
    public Vec2 gatePosition(){
        return new Vec2(24f, 9.5f);
    }
    @Override
    public String getLevelName() {
        return "Level 4";
    }
    @Override
    public Image getBackground(){
        return background;
    }
}
