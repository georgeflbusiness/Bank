

import java.util.Random;

public class Goblin {
    static String name = "Goblin";
    private int hp;
    private int power;


    //GETTER
    public int getHp() {
        return this.hp;
    }
    //Getter
    public int getPower() {
        return this.power;
    }


    //Setter
    public void setHp(int hp) {
        this.hp = hp;
    }
    //Setter
    public void setPower(int power) {
        this.power = power;
    }


    public int takeDamage(Player player) {
        Random randomattack = new Random();
        int randomnumber = randomattack.nextInt(5);
        int playerpower = player.getPower() - randomnumber;
        this.hp -= playerpower;
        return playerpower;
    }


    public int AttackPlayer(Player player) {
        Random attackingpower = new Random();
        int power = attackingpower.nextInt(4) + 6;
        player.setHealth((player.getHealth()) - power);
        return power;

    }





}
