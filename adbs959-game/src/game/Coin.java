package game;

import city.cs.engine.*;
/**coin class, used as a collectible */
public class Coin extends StaticBody {

    private static Shape shape = new PolygonShape(
            -0.174f,0.5f, -0.488f,0.156f,
            -0.494f,-0.186f, -0.168f,-0.504f,
            0.156f,-0.512f, 0.466f,-0.164f,
            0.47f,0.178f, 0.154f,0.5f);
    private static BodyImage image = new BodyImage("data/coins.gif", 1f);

    public Coin(World w) {
        super(w, shape);
        addImage(image);
    }
}
