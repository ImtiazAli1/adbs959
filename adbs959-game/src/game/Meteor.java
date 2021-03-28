package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Meteor extends Walker{
    private static Shape shape = new PolygonShape(
            -0.682f,-1.017f, 0.047f,-1.111f, 1.538f,-0.248f, 0.796f,0.922f, -1.164f,1.143f, -1.451f,0.106f);
    private static BodyImage image = new BodyImage("data/meteor.png", 2.5f);

    public Meteor(World world){
        super(world, shape);
        addImage(image);
    }

}
