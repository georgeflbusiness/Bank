import java.util.ArrayList;
import java.util.Random;



//CLASS ACCOUNT
public class Player {
    ArrayList<Item> inventory = new ArrayList<>();

    private Shop shop;

    private int level = 1;
    private int xp = 0;
    private int wallet = 0;
    private int health = 0;
    private int power = 0;
    private int healthPotionHp = 50;
    private int XpPotion = 25;

    private static final int MAX_HEALTH = 100;
    private static int keys = 0;
    int xpForLevelUp = 100;





    //Getter
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    //Getter
    public int getXp() {
        return xp;
    }
    //Getter
    public int getLevel() {
        return level;
    }
    //Getter
    public int getKeys() {
        return keys;
    }
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
    public void setXp(int xp) {
        this.xp = xp;
    }
    //Setter
    public void setLevel(int level) {
        this.level = level;
    }
    //Setter
    public void setKeys(int keys) {
        this.keys = keys;
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
    //Setter
    public void setShop(Shop shop) {
        this.shop = shop;
    }


    public void addItem(Item item) {
        this.inventory.add(item);
    }


    public void levelup() {
        this.level++;
        System.out.println("Συγχαρητήρια! Έχεις ανέβει στο επίπεδο " + this.level + "!");

        switch (this.level) {
            case 2:
                this.power += 5;
                xpForLevelUp = 200;
                shop.setHealthPotionCost(shop.getHealthPotionCost() + 20);
                System.out.println("Η δύναμή σου αυξήθηκε κατά 5!");
                break;
            case 3:
                this.power += 10;
                xpForLevelUp = 300;
                System.out.println("Η δύναμή σου αυξήθηκε κατά 10!");
                break;
            case 4:
                this.power += 15;
                xpForLevelUp = 400;
                System.out.println("Η δύναμή σου αυξήθηκε κατά 15!");
                break;
        }
    }




    //XP METHOD
    public void addXp(int amount) {
        this.xp += amount;

        int xpRequired = 100 * this.level;
        this.xpForLevelUp += xpRequired;
        while (this.xp >= xpRequired) {
            this.xp -= xpRequired;
            this.levelup();


            //update the xprequired for the next level
            xpRequired = 100 * this.level;
        }


    }



    //DEPOSIT METHOD
    public void deposit(Bank funds, int depositamount) {


        if (depositamount <= this.wallet) {
            this.wallet -= depositamount;
            funds.setFunds(funds.getFunds() + depositamount);
        } else {
            System.out.println("Μη έγκυρο ποσό!");
        }
    }





    public void useHealthPotions() {
        // Ψάχνουμε να βρούμε αν υπάρχει φίλτρο στο inventory
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);

            // Αν το όνομα του αντικειμένου είναι Health Potion
            if (item.getName().equals("Health Potion")) {
                this.health = Math.min(this.health + healthPotionHp, MAX_HEALTH);
                inventory.remove(i); // Το βγάζουμε από το σακίδιο (το ήπιαμε!)
                System.out.println("🧪 Ήπιες ένα Health Potion! Έγινες καλά.");
                return; // Σταματάμε το ψάξιμο, κάναμε τη δουλειά μας
            }
        }

        // Αν τελειώσει το loop και δεν έχουμε βρει τίποτα:
        System.out.println("❌ Δεν έχεις Health Potions στο σακίδιό σου!");
    }


    public void useXpPotion() {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);

            if(item.getName().equals("XP Potion")) {
                this.xp += this.XpPotion;
                inventory.remove(i);
                return;
            }
        }


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