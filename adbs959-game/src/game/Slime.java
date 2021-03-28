package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;

/**enemy class that moves with a step listener*/
public class Slime extends Walker implements StepListener {


    private float step =0.025f;
    private static Shape shape = new PolygonShape(
            -0.62f,-2.43f, -0.86f,-2.2f, -0.86f,-1.78f, -0.22f,-1.29f, 0.39f,-1.28f, 1.03f,-1.78f, 1.03f,-2.18f, 0.81f,-2.4f);
    private static BodyImage image = new BodyImage("data/enemy/SlimeAnimations/Red/Idle/SlimeRed_Idle.gif", 5f);

    public Slime(World world){
        super(world, shape);
        addImage(image);
        Timer timer = new Timer(4000,e -> {step *= -1;});
        timer.start();
        world.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(step<=0){
            removeAllImages();
            addImage(image);
        }else if(step>0){
            removeAllImages();
            addImage(image).flipHorizontal();
        }
        setPosition(getPosition().add(new Vec2(step,0)));
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
