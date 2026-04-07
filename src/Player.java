import java.util.Random;


//CLASS ACCOUNT
public class Player {
    private int wallet = 0;
    private int health = 0;
    private int power = 0;
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

        if (this.health > 100) {
            this.health = 100;
        }

        else {
            System.out.println("You dont have Health Potions");
        }

    }


    //BET METHOD
    public void Treasure1() {
        Random randombet = new Random();
        int randomnumber = randombet.nextInt(100);
        if (this.wallet > 100) {
            this.wallet -= 100;

                if (randomnumber < 10) {
                    this.wallet += 500;
                    System.out.println("✨JACKPOT! Κέριδες 500$!");
                }

                else if (randomnumber < 30) {
                    this.wallet += 150;
                    System.out.println("✅Καλά Πήγε! Κερδισες 150$");

                }

                else {
                    this.wallet += 50;
                    System.out.println("☠️Ατυχία... Κέρδισες μόνο 50$.");
                }
        }


    }






}