package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;

/**enemy class --boss-- */
public class Boss extends Walker implements StepListener {

    /**implements a step listener that allows it to move between points */
    private float step =0.025f;
    private int bossLife;
    private static Shape shape = new PolygonShape(
            -0.15f,1.32f, -0.65f,-1.87f, 0.62f,-1.82f);
    private static BodyImage image = new BodyImage("data/enemy/boss/walk/walk.gif", 6f);

    public Boss(World world){
        super(world, shape);
        addImage(image);
        Timer timer = new Timer(12000,e -> {step *= -1;});
        timer.start();
        world.addStepListener(this);
        bossLife = 25;
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
    public int getBossLife(){
        return bossLife;
    }
    public void loseBossLife(){
        bossLife--;
    }
}
