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


    public int  HealthPotion() {
        Random randomhealth = new Random();

        if (balance >= 100) {
            balance -= 100;
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
        if (this.balance > 100) {
            this.balance -= 100 ;
            int betting = randombet.nextInt(181) + 20;
            this.balance += betting;
            return betting; //επιστρεφει το ποσο
        }

        else {
            return -1; //επιστρεφει -1 αν δεν εχει χρηματα
        }

    }


}
