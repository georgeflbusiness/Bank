

import java.util.Random;

public class Goblin {
    String name = "Goblin";
    private int hp;
    int attackPower;


    //GETTER
    public int getHp() {
        return this.hp;
    }
    //Getter
    public int getAttackPower() {
        return this.attackPower;
    }


    //Setter
    public void setHp(int hp) {
        this.hp = hp;
    }
    //Setter
    public void setAttackPower(int power) {
        this.attackPower = power;
    }


    public int takeDamage(Player player) {
        Random randomattack = new Random();
        int randomnumber = randomattack.nextInt(10);
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
