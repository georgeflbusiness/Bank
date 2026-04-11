
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Random;
import java.util.Scanner;


public class GameManager {
    private Bank bank;
    private Shop shop;
    private Player player;
    private Goblin goblin;
    private Scanner input;
    boolean isRunning = true;
    private GameState currectState = GameState.MAIN_MENU;
    private String shopMessage = "";


    /**
     * Constructor - αρχικοποιει το παιχνιδι - φτιαχνει τα objects
     * Το this. σημαίνει: "τη δική μας μεταβλητή" (όχι κάποια άλλη).
     */
    public GameManager() {

        //CREATE OBJECTS-SCANNER
        this.bank = new Bank();
        this.player = new Player();
        this.shop = new Shop(player);
        player.setShop(shop);
        this.goblin = new Goblin();
        this.input = new Scanner(System.in);



        //SETTINGS
        player.setHealth(20);
        bank.setFunds(5000);
        player.setPower(20);
        goblin.setHp(100);
        goblin.setPower(10);
    }





    //ENUM
    public enum GameState {
        MAIN_MENU,
        BANK,
        SHOP,
        ARENA,
        LOOT_BOXES,
        EXIT
    }



    /**
     * START GAME
     */
    public void start() {


        while (isRunning) {

            switch (currectState) {
                case MAIN_MENU:
                    displayMainMenu();
                    handleMenuChoice();
                    break;
                case BANK:
                    displayBankMenu();
                    handleBankMenu();
                    break;
                case SHOP:
                    displayShopMenu();
                    handleShopMenu();
                    break;
                case ARENA:
                    displayArenaMenu();
                    handleArenaMenu();
                    break;
                case LOOT_BOXES:
                    displayLootBoxes();
                    handleLootBoxes();
                    break;
                case EXIT:
                    isRunning = false;
                    break;
            }
        }
    }


//-----------------------------------------------------------------------------------



    /**
     * Εμφανίζει το κύριο menu - μονο print
     */
    private void displayMainMenu() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("  💰 BANK: " + bank.getFunds() + "  |  👛 WALLET: " + player.getWallet() + "$");
        System.out.println("  ❤️ HP: " + player.getHealth() + "  |  ⭐ LVL: " + player.getLevel() + " " + "[" + player.getXp() + "/" + player.xpForLevelUp + "]" +  " |  ⚔️ PWR: " + player.getPower());
        System.out.println("═".repeat(50));
        System.out.println("  1. [Bank]              4. [Loot Boxes]");
        System.out.println("  2. [Coming Soon]       5. [Arena]");
        System.out.println("  3. [Shop]              6. [Exit]");
        System.out.println("═".repeat(50));
        System.out.print("➤ Επιλογή: ");
    }

    private void displayBankMenu() {
        System.out.println("═".repeat(50));
        System.out.println("                    BANK");
        System.out.println("  💰 BANK: " + bank.getFunds() + "  |  👛 WALLET: " + player.getWallet() + "$");
        System.out.println("═".repeat(50));
        System.out.println("  1. [Deposit]");
        System.out.println("  2. [Withdraw]");
        System.out.println("  3. [ ↩ ΕΠΙΣΤΡΟΦΗ ]");
        System.out.println("═".repeat(50));
        System.out.print("➤ Επιλογή: ");
    }


    /**
     * Εμφανιζει το shop menu - μονο print
     */
   private void displayShopMenu() {
       System.out.print("\033[H\033[2J");
       System.out.flush();

       String ui = """
 ════════════════════════════════════════════════════════════
                                                            
                 🏪 MAGIC SHOP - CLASSIC 🏪
             
 ═════════════════════════════════════════════════════════════
  👛 WALLET: %-5d$  |  ❤️ HP: %d%%   |  ⭐ LVL: %d

  ITEM                    PRICE      Inventory   STATS
  ──────────────────────────────────────────────────────
  1. Health Potion           %d💲     📦 x%d    +50 HP
  2. Level-up Potion         100💲    📦 x0     +1 LVL
  3. Mana Potion             150💲    📦 x0     +30 MP
  ──────────────────────────────────────────────────────
  4. [Back To Town]
═══════════════════════════════════════════════════════════════
\s""".formatted(player.getWallet(), player.getHealth(),player.getLevel(),shop.getHealthPotionCost(),player.healthpotions);

       System.out.println(ui);
       if (!shopMessage.isEmpty()) {
           System.out.println(shopMessage);
           shopMessage = "";
         }

       System.out.print("➤ Επιλογή: ");
   }


    /**
     * Εμφανιζει την Arena menu - μονο print
     */
    private void displayArenaMenu() {
        System.out.println("\n" + "═".repeat(61));
        System.out.println("                  ⚔️  BATTLE ARENA  ⚔️");

// Χρησιμοποιούμε %-10s ή %-5d για να κρατάμε σταθερά κενά
        System.out.printf("  ❤️ HP: %-8d|  ⚔️ PWR: %-7d|  ⭐ LVL: %d\n",
                player.getHealth(), player.getPower(), player.getLevel());
        System.out.println("═".repeat(61));

        System.out.println("1.       👹  [ GOBLIN ] 👹");
        System.out.println("       " + "-".repeat(22));
        System.out.println("        Stats     |     Rewards");

// Εδώ το %-10d εξασφαλίζει ότι το Reward θα μένει πάντα στην ίδια στήλη
        System.out.printf("     ❤️ %-10d|     200💲\n", goblin.getHp());
        System.out.printf("     ⚔️ %-10d|     XP:50\n", goblin.getPower());

        System.out.println("═".repeat(61));

        System.out.println("5. [ ↩ ΕΠΙΣΤΡΟΦΗ ]");
        System.out.println("═".repeat(61));
        System.out.print("➤ Επιλογή: ");
    }


    /**
     * Εμφανιζει τα Loot Boxes - μονο print
     */

    private void displayLootBoxes() {
        System.out.println("\n" + "═".repeat(61));
        System.out.println("                   🎁  LOOT BOX SHOP  🎁");
        System.out.println("                       👛 WALLET:" + player.getWallet() + "$");
        System.out.println("═".repeat(61));
        System.out.println("1. [ WOODEN CRATE ] 📦  Cost: 100$");
        System.out.println("    " + "-".repeat(33));
        System.out.println("    Prizes:    |    Chances:");
        System.out.println("    • 50$      |    60% (Common)");
        System.out.println("    • 150$     |    30% (Uncommon)");
        System.out.println("    • 350$     |    10% (RARE! ✨)");


        System.out.println("\n 5. [ ↩ ΕΠΙΣΤΡΟΦΗ ]");
        System.out.println("═".repeat(61));
        System.out.print("➤ Επιλογή: ");
    }




    //Displa
    private void displayArenaGoblin() {
        System.out.println("\n" + "═".repeat(61));
        System.out.println("                  ⚔️  BATTLE IN PROGRESS  ⚔️");
        System.out.println("═".repeat(61));


        System.out.printf("  YOU:    ❤️ HP: %-10d |  ⚔️ PWR: %-10d\n",
                player.getHealth(), player.getPower());
        System.out.printf("  GOBLIN: ❤️ HP: %-10d |  ⚔️ PWR: %-10d\n",
                goblin.getHp(), goblin.getPower());

        System.out.println("─".repeat(61));

        System.out.println(" 1. [⚔️ATTACK]");
        System.out.printf(" 2. [🧪USE  HEALTH POTION] (📦 x%d)\n", player.healthpotions);
        System.out.println(" 5. [🏳️RETREAT] (Ποινή: -50 XP)");

        System.out.println("═".repeat(61));
        System.out.print("➤ Επιλογή: ");
    }



//------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /** Χειριζεται τις επιλογες του menu
     *
     */
    private void handleMenuChoice() {
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                currectState = GameState.BANK;
                break;
            case 2:
                break;
            case 3:
                currectState = GameState.SHOP;
                break;
            case 4:
                currectState = GameState.LOOT_BOXES;
                break;
            case 5:
                currectState = GameState.ARENA;
                break;
            case 6:
                currectState = GameState.EXIT;
                break;
            default:
                System.out.println("Μη εγκυρη επιλογή");
        }
    }

    /**
     * Χειριζεται τις επιλογες του shop
     */
    private void handleBankMenu() {
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                handleDeposit();
                break;
            case 2:
                handleWithdraw();
                break;
            case 3:
                currectState = GameState.MAIN_MENU;
                break;
            default:
                System.out.println("Μη έγκυρη επιλογή!");
        }
    }

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
                currectState = GameState.MAIN_MENU;
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
                currectState = GameState.MAIN_MENU;
                break;

        }

    }


    /**
     * Χειριζεται τις επιλογες του LootBoxes
     */
    private void handleLootBoxes() {
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                player.woodenCrate();
                break;
            case 5:
                currectState = GameState.MAIN_MENU;
                break;
            default:
                System.out.println("Μη έγκυρη επιλογή!");
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
            bank.transferTo(player, amount);
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

        player.deposit(bank,amount);
        System.out.println("✓ Επιτυχής κατάθεση " + amount + "$");
    }


    /**
     * Bet method
     */
    public void handleBet() {
        player.woodenCrate();

    }


    public void handleHealthPotion() {
        int result = shop.buyHealthPotion();

        if (result == -1) {
            shopMessage = "Δεν έχεις αρκετά χρήματα για να αγοράσεις ένα Health Potion!";
        }
        else {
            shopMessage = "Αγόρασες ένα Health Potion! Έχεις " + player.healthpotions + " στο inventory σου.";
        }

    }


    public void handleLevelPotion() {
        player.levelUpPotion();

    }



    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void Goblin() {
        boolean fighting = true;
        System.out.println("Μπήκες στην Arena με το Goblin");

        while (fighting) {
                displayArenaGoblin();
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    int actualdamage = goblin.takeDamage(player);
                    int actualhit = goblin.AttackPlayer(player);
                    System.out.println("Έκανες " + actualdamage +  " ζημιά");
                    System.out.println("Σου έκανε " + actualhit + " ζημιά");


                    //Check if player die
                    if (player.getHealth() <= 0) {
                        System.out.println("💀 Πέθανες! Έχασες όλο το πορτοφόλι σου.");
                        fighting = false;
                        currectState = GameState.MAIN_MENU;

                        //Resets
                        player.setHealth(50); //Reset HP
                        player.setWallet(0);//Loss Player Wallet
                        goblin.setHp(100);
                        break;
                    }

                    //Check if Goblin die
                    else if (goblin.getHp() <= 0) {
                            System.out.println("🏆 Κέρδισες τη μάχη! +200$ και +50 XP");
                            player.setWallet(player.getWallet() + 200); // Player Reward
                            player.addXp(50);

                           //Resets
                            goblin.setHp(100);
                            fighting = false;
                            currectState = GameState.MAIN_MENU;
                        }
                    break;
                case 2:
                    player.useHealthPotions();
                    break;
                case 5:
                    player.setXp(player.getXp() - 50); //Penalty
                    currectState = GameState.MAIN_MENU;
                    fighting = false;
                    break;
            }
        }
    }


}




