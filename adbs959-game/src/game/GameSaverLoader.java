package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Integer.parseInt;
/**Creating a game save and loader**/
public class GameSaverLoader {

    public static void save(GameLevel level, String fileName) throws IOException {

            boolean append = false;
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileName, append);
                writer.write(level.getLevelName()+"\n");

                for(StaticBody body : level.getStaticBodies()){
                    /**saving the coins and hearts in the file -- these are the only static bodies worth saving */
                    if(body instanceof Coin){
                        writer.write("Coin,"+body.getPosition().x+","+body.getPosition().y+"\n");
                    }
                    if(body instanceof Heart){
                        writer.write("Heart,"+body.getPosition().x+","+body.getPosition().y+"\n");
                    }
                }
                for(DynamicBody body : level.getDynamicBodies()){
                    /**all dynamic body information is saved, for the player, more information is saved such as their health and coincount etc */

                    if(body instanceof Person){
                        writer.write("Character,"+body.getPosition().x+","+body.getPosition().y+","
                                +((Person) body).getLifeCount()+"," +((Person) body).getCoinCount()+","
                                +((Person) body).getKillCount());
                    }
                    else if(body instanceof Enemy){
                        writer.write("Enemy,"+body.getPosition().x+"," +body.getPosition().y+"\n");
                    }
                    else if(body instanceof Slime){
                        writer.write("Slime,"+body.getPosition().x+","+body.getPosition().y+"\n");
                    }
                    else if(body instanceof Boss){
                        writer.write("Boss,"+body.getPosition().x+","+body.getPosition().y+"\n");
                    }
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
    }

    public static GameLevel load(Game game, String fileName) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            GameLevel level = null;
            if(line.equals("Level 1"))
                level = new Level1(game);
            else if(line.equals("Level 2"))
                level = new Level2(game);
            else if(line.equals("Level 3"))
                level = new Level3(game);
            else if(line.equals("Level 4"))
                level = new Level4(game);
            line = reader.readLine();

            while(line != null){

                String[] tokens = line.split(",");

                /**uses tokens to set the values of the x and y position of various bodies in the level
                collision listeners have to be added again, since the old listeners referenced the old bodies */

                if(tokens[0].equals("Coin")){
                    Coin c = new Coin(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    c.setPosition(new Vec2(x,y));
                    ShootCollision shootCollision = new ShootCollision(level.getPerson());
                    c.addCollisionListener(shootCollision);
                }
                if(tokens[0].equals("Heart")){
                    Heart h = new Heart(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    h.setPosition(new Vec2(x,y));
                }
                else if(tokens[0].equals("Character")){
                    Person p = new Person(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    p.setPosition(new Vec2(x,y));
                    p.setLifeCount(Integer.parseInt(tokens[3]));
                    p.setCoinCount(Integer.parseInt(tokens[4]));
                    p.setKillCount(Integer.parseInt(tokens[5]));
                    level.setPerson(p);

                    LifeCollision lifeCollision = new LifeCollision(p, game.getLevel());
                    p.addCollisionListener(lifeCollision);

                    CoinCollision coinCollision = new CoinCollision(p);
                    p.addCollisionListener(coinCollision);

                    LevelCollision levelCollision = new LevelCollision(level, game);
                    level.getGate().addCollisionListener(levelCollision);

                    ShootCollision shootCollision = new ShootCollision(p);
                    level.getGate().addCollisionListener(shootCollision);
                    level.addStepListener(new Tracker(game, p));
                }
                else if(tokens[0].equals("Enemy")){
                    Enemy e = new Enemy(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x,y));
                    ShootCollision shootCollision = new ShootCollision(game.getLevel().getPerson());
                    e.addCollisionListener(shootCollision);
                }
                else if(tokens[0].equals("Slime")){
                    Slime s = new Slime(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    s.setPosition(new Vec2(x,y));
                    ShootCollision shootCollision = new ShootCollision(game.getLevel().getPerson());
                    s.addCollisionListener(shootCollision);
                }
                else if(tokens[0].equals("Boss")){
                    Boss b = new Boss(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    b.setPosition(new Vec2(x,y));
                    ShootCollision shootCollision = new ShootCollision(game.getLevel().getPerson());
                    b.addCollisionListener(shootCollision);
                }

                line = reader.readLine();
            }
            return level;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
