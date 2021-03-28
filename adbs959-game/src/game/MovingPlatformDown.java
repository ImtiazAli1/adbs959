package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
/**creates a 'moving' platform using a step listener*/
public class MovingPlatformDown extends StaticBody implements StepListener {

    private float step =0.025f;
    private static final BoxShape platform = new BoxShape(3.5f,0.5f);
    private static BodyImage platformmedium = new BodyImage("data/1platform.png", 2f);

    public MovingPlatformDown(World world){
        super(world, platform);
        addImage(platformmedium);
        Timer timer = new Timer(8000, e -> {step *= -1;});
        timer.start();
        world.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        setPosition(getPosition().add(new Vec2(0,-step)));
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
