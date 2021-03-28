package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
/**creates a 'moving' platform using a step listener*/
public class MovingPlatformSide extends StaticBody implements StepListener {

    private float step =0.025f;
    private static final BoxShape platform = new BoxShape(1.5f,0.5f);
    private static BodyImage platformsmall = new BodyImage("data/1platform.png", 1f);

    public MovingPlatformSide(World world){
        super(world, platform);
        addImage(platformsmall);
        Timer timer = new Timer(5000, e -> {step *= -1;});
        timer.start();
        world.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        setPosition(getPosition().add(new Vec2(step,0)));
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
