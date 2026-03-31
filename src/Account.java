import java.util.Random;


//CLASS ACCOUNT
class Account {
    int balance;
    private int health;

    public int getHealth() {
        return health;
    }


    //DEPOSIT METHOD
    public void Deposit(Bank funds, int depositamount) {

        if (depositamount <= this.balance) {
            this.balance -= depositamount;
            funds.funds += depositamount;
        } else {
            System.out.println("Μη έγκυρο ποσό!");
        }
    }



    //HEALTH POTION METHOD
    public void setHealth(int health) {
        this.health = health;
    }


    public void HealthPotion() {
        Random randomhealth = new Random();

        if (balance >= 100) {
            balance -= 100;
            int extrahealth = randomhealth.nextInt(11) + 10;
            health += extrahealth;
            System.out.println("Πήρες " + extrahealth + " ζωή");
        }

        else {
            System.out.println("Δεν έχεις αρκετά χρήματα");

        }

    }


    //BET METHOD
    public void Bet() {
        Random randombet = new Random();
        if (this.balance > 100) {
            this.balance -= 100;
            int betting = randombet.nextInt(100) + 20;
            this.balance += betting;
            System.out.println("Κέρδισες" + betting + "$");
        }

        else {
            System.out.println("Δεν έχεις αρκετα χρήματα");
        }

    }


}
