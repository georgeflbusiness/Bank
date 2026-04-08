import java.util.Random;


//CLASS ACCOUNT
public class Player {
    private int wallet = 0;
    private int health = 0;
    private int power = 0;
    private int healthPotionHp = 50;
    private static final int HEALTH_POTION_COST = 100;
    private static final int MAX_HEALTH = 100;
    int xp = 0;
    int level = 1;
    int healthpotions = 0;


    //Getter
    public int getWallet(){
        return wallet;
    }
    //Getter
    public int getPower() {
        return power;
    }
    //Getter
    public int getHealth() {
        return health;
    }


    //Setter
    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
    //Setter
    public void setPower(int power) {
        this.power = power;
    }

    //Setter
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


    public int buyHealthPotion() {

        if (wallet >= HEALTH_POTION_COST) {
            wallet -= HEALTH_POTION_COST;
            healthpotions++;
            return healthpotions;

        }

        else {
            return -1;

        }

    }

    public void useHealthPotions() {

        if(healthpotions <= 0) {
            System.out.println("Δεν έχεις φίλτρα");
            return;
        }

        this.health = Math.min(this.health + healthPotionHp, MAX_HEALTH);
        healthpotions--;


    }


    //WOODEN CRATE METHOD
    public void woodenCrate() {
        final int MIN_REWARD = 50;
        final int MIDDLE_REWARD = 150;
        final int MAX_REWARD = 350;

        Random randombet = new Random();
        int randomnumber = randombet.nextInt(100);


        if (this.wallet > 100) {
            this.wallet -= 100;

                if (randomnumber < 10) {
                    this.wallet += MAX_REWARD;
                    System.out.println("✨JACKPOT! Κέριδες 350$!");
                }

                else if (randomnumber < 30) {
                    this.wallet += MIDDLE_REWARD;
                    System.out.println("✅Καλά Πήγε! Κερδισες 150$");

                }

                else {
                    this.wallet += MIN_REWARD;
                    System.out.println("☠️Ατυχία... Κέρδισες μόνο 50$.");
                }
        }


    }






}