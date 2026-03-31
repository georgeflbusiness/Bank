import java.util.Random;


//CLASS ACCOUNT
 public class Account {
    int wallet;
    private int health;
    int xp = 0;
    int level = 1;


    //GETTER
    public int getHealth() {
        return health;
    }

    //XP METHOD
    public void addXp(int amount) {
        this.xp += amount;
        if (xp >= 100) {
            level++;
            xp = 0;
        }
    }


    //DEPOSIT METHOD
    public void Deposit(Bank funds, int depositamount) {

        if (depositamount <= this.wallet) {
            this.wallet -= depositamount;
            funds.funds += depositamount;
        } else {
            System.out.println("Μη έγκυρο ποσό!");
        }
    }



    //SETTER
    public void setHealth(int health) {
        this.health = health;
    }

    //LEVEUP POTION METHOD
    public void levelup() {
        if (wallet >= 200) {
            this.wallet -= 200;
            addXp(100);
        } else {
            System.out.println("testing");
        }

    }


    public int HealthPotion() {
        Random randomhealth = new Random();

        if (wallet >= 100) {
            wallet -= 100;
            int extrahealth = randomhealth.nextInt(11) + 10;
            health += extrahealth;
            return extrahealth;
        }

        else {
            return -1;

        }

    }


    //BET METHOD
    public int Bet() {
        Random randombet = new Random();
        if (this.wallet > 100) {
            this.wallet -= 100 ;
            int betting = randombet.nextInt(181) + 20;
            this.wallet += betting;
            return betting; //επιστρεφει το ποσο
        }

        else {
            return -1; //επιστρεφει -1 αν δεν εχει χρηματα
        }

    }


}
