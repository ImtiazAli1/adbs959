package game;

import city.cs.engine.*;
/**class for the players character*/
public class Person extends Walker {

    private static Shape shape = new PolygonShape(
            -0.355f,1.245f, 0.34f,1.245f, 0.395f,-1.245f, -0.4f,-1.245f);
    private static BodyImage image = new BodyImage("data/player/standing.png", 2.5f);

    private int lifeCount;
    private int killCount;
    private int coinCount;

    public Person(World world){
        super(world, shape);
        addImage(image);
        lifeCount = 4; /**if this reaches 0, the game is over*/
        killCount = 0; /**incremented when enemies are defeated*/
        coinCount = 0; /**incremented when coins are collected, required to go to the next level*/
    }
    public int getLifeCount(){
        return lifeCount;
    }
    public void LoseLife(){
        lifeCount--;
    }
    public int getCoinCount(){return coinCount;}
    public void AddCoin(){
        coinCount++;
    }
    public int getKillCount() {return killCount;}
    public void addKill(){
        killCount++;
        System.out.println("Kill Count: " + killCount);
    }
    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }
    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }
}
