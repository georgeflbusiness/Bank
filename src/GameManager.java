import java.util.Scanner;

public class GameManager {
    private Bank bank;
    private Account playerAccount;
    private Scanner input;
    private boolean start_menu;
    private boolean shop_menu;


    /**
     * Constructor - αρχικοποιει το παιχνιδι - φτιαχνει τα objects
     * Το this. σημαίνει: "τη δική μας μεταβλητή" (όχι κάποια άλλη).
     */

    public GameManager() {
        this.bank = new Bank(10000);
        this.playerAccount = new Account();
        this.input = new Scanner(System.in);
        this.start_menu = false;
        this.shop_menu = false;

        playerAccount.setHealth(50);
    }

    public void start() {
        start_menu = true;
        shop_menu = false;

        while (start_menu) {
            displayMainMenu();
            handleMenuChoice();
        while (shop_menu) {
            displayShopMenu();
            handleShopMenu();
        }
        }
    }





    /**
     * Εμφανίζει το κύριο menu - μονο print
     */
    private void displayMainMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("ΤΡΑΠΕΖΑ: " + bank.funds + "$ | ΛΟΓΑΡΙΑΣΜΟΣ: " + playerAccount.wallet + "$ | HP: " + playerAccount.getHealth() + " | Lvl: " + playerAccount.level + " | XP: " + playerAccount.xp + " / 100");
        System.out.println("1. Ανάληψη (+ fees)");
        System.out.println("2. Κατάθεση");
        System.out.println("3. Shop");
        System.out.println("4. Bet (100$)");
        System.out.println("5. ΕΞΟΔΟΣ");
        System.out.print("Επιλογή: ");
    }


    /**
     * Εμφανιζει το shop menu - μονο print
     */
   private void displayShopMenu() {
        System.out.println("\n===== SHOP =====");
     System.out.println("HP: " + playerAccount.getHealth() + " | Υπόλοιπο: " + playerAccount.wallet + "$" + "| Lvl: " + playerAccount.level + " | XP: " + playerAccount.xp + " / 100 ");
     System.out.println("1. Health Potion (100$)");
     System.out.println("2. Level up Potion (200$)");
     System.out.println("3. Επιστροφή");
   }


    /**
     * Χειριζεται τις επιλογες του shop
     */

    private void handleShopMenu() {
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                handleHealthPotion();
                break;
            case 2:
                handleLevelPotion();
                break;
            case 3:
                shop_menu = false;
                start_menu = true;
        }
    }


    /** Χειριζεται τις επιλογες του menu
     *
     */
    private void handleMenuChoice() {
        int choice = input.nextInt();
        String message = "";

        switch (choice) {
            case 1:
                handleWithdraw();
                message = "test";
                System.out.println(message);
                break;
            case 2:
                handleDeposit();
                break;
            case 3:
                shop_menu = true;
                start_menu = false;
                handleShop();
                break;
            case 4:
                handleBet();
            case 5:
                shop_menu = false;
                break;
            default:
                System.out.println("Μη εγκυρη επιλογή");
        }
    }


    /**
     * withdraw method
     */
    private void handleWithdraw() {
        System.out.print("Ποσό ανάληψης ");
        int amount = input.nextInt();

        if (amount > 0 && amount <= bank.funds) {
            bank.Transferto(playerAccount, amount);
            System.out.println("Επιτυχής ανάληψη " + amount + " $");
        }
        else {
            System.out.println("✗ Δεν έχεις αρκετά χρήματα στην τράπεζα");
        }
    }


    /**
     * deposit method
     */
    public void handleDeposit() {
        System.out.print("Ποσό κατάθεσης ");
        int amount = input.nextInt();

        playerAccount.deposit(bank,amount);
        System.out.println("✓ Επιτυχής κατάθεση " + amount + "$");
    }




    /**
     * Χειρίζεται το shop (placeholder για τώρα
     */
    public void handleShop() {
        System.out.println("Coming Soon");
    }




    /**
     * Bet method
     */
    public void handleBet() {
        int winamount = playerAccount.Bet();
        if (winamount != -1) {
            System.out.println("✓ Κέρδισες " + winamount + "$");
        } else {
            System.out.println("✗ Δεν έχεις αρκετά χρήματα!");
        }
    }


    public void handleHealthPotion() {
        playerAccount.HealthPotion();

    }


    public void handleLevelPotion() {
        playerAccount.levelup();

    }


}