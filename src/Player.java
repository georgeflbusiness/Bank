import java.util.Random;


//CLASS ACCOUNT
public class Player {
    int wallet = 0;
    private int health = 0;
    private int power = 0;
    int xp = 0;
    int level = 1;
    int healthpotions = 0;


    //Getter
    public int getPower() {
        return power;
    }
    //Setter
    public void setPower(int power) {
        this.power = power;
    }


    //GETTER
    public int getHealth() {
        return health;
    }
    //SETTER
    public void setHealth(int health) {
        this.health = health;
    }





    //XP METHOD
    public void addXp(int amount) {
        this.xp += amount;


        if (this.level == 1 && xp >= 100) {
            level++;
            xp = 0;
        }

        else if(this.level == 2 && xp >= 200) {
            level++;
            xp = 0;
        }

        else if(this.level == 3 && xp >= 300) {
            level++;
            xp = 0;
        }


    }



    //DEPOSIT METHOD
    public void deposit(Bank funds, int depositamount) {


        if (depositamount <= this.wallet) {
            this.wallet -= depositamount;
            funds.setFunds(depositamount);
        } else {
            System.out.println("Μη έγκυρο ποσό!");
        }
    }





    //LEVEUP POTION METHOD!
    public void levelup() {
        if (wallet >= 200) {
            this.wallet -= 200;
            this.addXp(100);
        } else {
            System.out.println("testing");
        }

    }


    public int BuyHealthPotion() {

        if (wallet >= 100) {
            wallet -= 100;
            healthpotions++;
            return healthpotions;
        }

        else {
            return -1;

        }

    }

    public void UseHealthPotions() {
        if(healthpotions >= 1) {
            this.health += 50;
            healthpotions--;
        }

        else {
            System.out.println("You dont have Health Potions");
        }

    }


    //BET METHOD
    public int bet() {
        Random randombet = new Random();
        if (this.wallet > 100) {
            this.wallet -= 100 ;
            int betting = randombet.nextInt(181) + 20;
            this.wallet += betting;
            if (betting > 100) {
                addXp(20);
            }
            return betting; //επιστρεφει το ποσο
        }

        else {
            return -1; //επιστρεφει -1 αν δεν εχει χρηματα
        }

    }






}