package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;


/**enemy class*/
public class Enemy extends Walker implements StepListener {
    /**step listener is implemented that makes the body move, the body is also given several images that give it the appearance of walking */
    private float step =0.025f;
    private int enemyLife;
    private static Shape shape = new PolygonShape(
            -0.412f,-1.071f, -0.403f,1.125f, 0.389f,1.107f, 0.434f,-1.107f);
    private static BodyImage image = new BodyImage("data/enemy/walk/walk.gif", 2.25f);

    public Enemy(World world){
        super(world, shape);
        addImage(image);
        Timer timer = new Timer(3500,e -> {step *= -1;});
        timer.start();
        world.addStepListener(this);
        enemyLife = 3;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(step<=0){
            removeAllImages();
            addImage(image).flipHorizontal();
        }else if(step>0){
            removeAllImages();
            addImage(image);
        }
        setPosition(getPosition().add(new Vec2(step,0)));
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }

    public int getEnemyLife(){
        return enemyLife;
    }
    public void loseEnemyLife(){
        enemyLife--;
    }
}
