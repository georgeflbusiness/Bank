
import java.util.Scanner;

public class GameManager {
    private Bank bank;
    private Player playerAccount;
    private Goblin goblin;
    private Scanner input;
    private boolean start_menu;
    private boolean shop_menu;
    private boolean arena_menu;
    boolean isRunning = true;


    /**
     * Constructor - αρχικοποιει το παιχνιδι - φτιαχνει τα objects
     * Το this. σημαίνει: "τη δική μας μεταβλητή" (όχι κάποια άλλη).
     */
    public GameManager() {
        this.bank = new Bank();
        this.playerAccount = new Player();
        this.goblin = new Goblin();
        this.input = new Scanner(System.in);
        this.start_menu = false;
        this.shop_menu = false;
        this.arena_menu = false;



        //SETTINGS
        playerAccount.setHealth(50);
        bank.setFunds(5000);
        playerAccount.setPower(20);
    }

    /**
     * START GAME
     */
    public void start() {
        isRunning = true;
        start_menu = true;
        shop_menu = false;
        arena_menu = false;


        while (isRunning) {

            while (start_menu) {
                displayMainMenu();
                handleMenuChoice();
            }

            while (shop_menu){
                displayShopMenu();
                handleShopMenu();
            }

            while (arena_menu) {
                displayArenaMenu();
                handleArenaMenu();
            }

            if (!start_menu) {
                isRunning = false;
            }

        }
    }


//-----------------------------------------------------------------------------------



    /**
     * Εμφανίζει το κύριο menu - μονο print
     */
    private void displayMainMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("Bank: " + bank.getFunds() + "$ | Wallet: " + playerAccount.wallet + "$ | ❤\uFE0F: " + playerAccount.getHealth() + " | Lvl: " + playerAccount.level + " | XP: " + playerAccount.xp + " | ⚔\uFE0F " + playerAccount.getPower());
        System.out.println("1. Withdraw (+ fees)");
        System.out.println("2. Deposit");
        System.out.println("3. Shop");
        System.out.println("4. Bet (100$)");
        System.out.println("5. Arena");
        System.out.println("6. Shop");
        System.out.print("Επιλογή: ");
    }


    /**
     * Εμφανιζει το shop menu - μονο print
     */
   private void displayShopMenu() {
        System.out.println("\n===== SHOP =====");
     System.out.println("❤\uFE0F: " + playerAccount.getHealth() + " | Wallet: " + playerAccount.wallet + "$" + "| Lvl: " + playerAccount.level + " | XP: " + playerAccount.xp + " / 100 ");
     System.out.println("1. Buy Health Potion (100$)" + " | You have: " + playerAccount.healthpotions);
     System.out.println("2. Level-up Potion (200$)");
     System.out.println("3. Επιστροφή");
     System.out.print("Επιλογή: ");
   }


    /**
     * Εμφανιζει την Arena menu - μονο print
     */
    private void displayArenaMenu() {
        System.out.println("\n===== ARENA =====");
        System.out.println("1.| Monster: " + goblin.name + " | Health: " + goblin.getHp() + " | AttackPower: " + goblin.attackPower + " | Reward : 200$");
        System.out.println("5. Επιστροφή");
        System.out.print("Επιλογή: ");
    }



//------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /** Χειριζεται τις επιλογες του menu
     *
     */
    private void handleMenuChoice() {
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                handleWithdraw();
                break;
            case 2:
                handleDeposit();
                break;
            case 3:
                shop_menu = true;
                start_menu = false;
                break;
            case 4:
                handleBet();
                break;
            case 5:
                start_menu = false;
                shop_menu = false;
                arena_menu = true;
                break;
            case 6:
                start_menu = false;
                break;


            default:
                System.out.println("Μη εγκυρη επιλογή");
        }
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
                break;
        }
    }


    /**
     * Χειριζεται τις επιλογες της Arena
     */

    private void handleArenaMenu() {
        int choice = input.nextInt();

        switch (choice) {
            case 1:
               Goblin();
                break;
            case 5:
                arena_menu = false;
                start_menu = true;
                break;

        }

    }



    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * withdraw method
     */
    private void handleWithdraw() {
        System.out.print("Ποσό ανάληψης ");
        int amount = input.nextInt();

        if (amount > 0 && amount <= bank.getFunds()) {
            bank.transferTo(playerAccount, amount);
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
     * Bet method
     */
    public void handleBet() {
        int winamount = playerAccount.bet();
        if (winamount != -1) {
            System.out.println("✓ Κέρδισες " + winamount + "$");
        } else {
            System.out.println("✗ Δεν έχεις αρκετά χρήματα!");
        }
    }


    public void handleHealthPotion() {
        playerAccount.BuyHealthPotion();

    }


    public void handleLevelPotion() {
        playerAccount.levelup();

    }

    public void Goblin() {
        boolean fighting = true;
        System.out.println("Μπήκες στην Arena με το Goblin");

        while (fighting) {
            System.out.println("=====FIGHTING=====");
            System.out.println("Your health: " + playerAccount.getHealth() + " Power: " + playerAccount.getPower());
            System.out.println("----------------------------------------");
            System.out.println("Goblin health: " + goblin.getHp() + " Enemy Power: " + goblin.attackPower);
            System.out.println("----------------------------------------");
            System.out.println("1. Attack");
            System.out.println("2. Use Health Potion " + "(" + playerAccount.healthpotions + ")" );
            System.out.println("5. Επιστροφή");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    int actualdamage = goblin.takeDamage(playerAccount);
                    int actualhit = goblin.AttackPlayer(playerAccount);
                    System.out.println("Έκανες " + actualdamage +  " ζημιά");
                    System.out.println("Σου έκανε " + actualhit + " ζημιά");

                    if (playerAccount.getHealth() <= 0 && goblin.getHp() > 0) {
                        System.out.println("Πέθανες");
                        fighting = false;
                        arena_menu = false;
                        start_menu = true;
                        playerAccount.setHealth(50);
                        playerAccount.wallet = 0;
                    }

                    else if (playerAccount.getHealth() > 0 && goblin.getHp() <= 0) {
                        System.out.println("Κέρδισες την Μάχη + 200$");
                        fighting = false;
                        arena_menu = false;
                        start_menu = true;
                        playerAccount.wallet += 200;
                    }
                    break;
                case 2:
                    playerAccount.UseHealthPotions();
                    break;
                case 5:
                    arena_menu = false;
                    start_menu = true;
                    fighting = false;
                    break;
            }
        }
    }


}




