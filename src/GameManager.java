
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

    //DISPLAY METHODS - ΜΟΝΟ PRINT
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";


    private void displayMainMenu() {
        String ui = String.format("""
    %s=============================================================%s
                   🏰  TOWN CENTER - MAIN MENU  🏰
    %s=============================================================%s
    
    💰 BANK: %-7d     | 👛 WALLET: %s%d💲%s
    ❤️ HP: %-7d       | ⚔️ PWR: %s%-10d%s
    ⭐ LVL: %-7d      | ✨ XP: %d/%d
    
    %s=============================================================%s
    
      %s1. [🏛️  BANK]%s              %s4. [📦  LOOT BOXES]%s
      %s2. [🔒  COMING SOON]%s       %s5. [🏟️  ARENA]%s
      %s3. [🛒  SHOP]%s              %s6. [🚪  EXIT]%s
    
    %s=============================================================%s
    ➤ Επιλογή:\s""",
                CYAN, RESET,
                CYAN, RESET,
                bank.getFunds(), GREEN, player.getWallet(), RESET,
                player.getHealth(), GREEN, player.getPower(), RESET,
                player.getLevel(), player.getXp(), player.xpForLevelUp,
                CYAN, RESET,
                YELLOW, RESET, MAGENTA, RESET,
                YELLOW, RESET, RED, RESET,
                YELLOW, RESET, WHITE, RESET,
                CYAN, RESET
        );

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print(ui);
    }



    private void displayBankMenu() {
        String ui = String.format("""
        %s=============================================================%s
                          🏦  IRON BANK OF BRAAVOS  🏦
        %s=============================================================%s
        
        💰 BANK: %-7d    | 👛 WALLET: %s%d💲%s
        
        %s=============================================================%s
        
          %s1. [📥  DEPOSIT]%s
          %s2. [📤  WITHDRAW]%s
        
          %s3. [↩️   BACK TO TOWN]%s
        
        %s=============================================================%s
        ➤ Επιλογή:\s""",
                CYAN, RESET,
                CYAN, RESET,
                bank.getFunds(), GREEN, player.getWallet(), RESET,
                CYAN, RESET,
                YELLOW, RESET,
                YELLOW, RESET,
                WHITE, RESET,
                CYAN, RESET
        );

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print(ui);
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
                \s""".formatted(player.getWallet(), player.getHealth(), player.getLevel(), shop.getHealthPotionCost(), player.healthpotions);

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
        String ui = String.format("""
        %s=============================================================%s
                   ⚔️  BATTLE ARENA - SELECT ENEMY  ⚔️
        %s=============================================================%s  
        ❤️ HP: %-7d          | ⚔️ PWR: %s%-10d%s
        ⭐ LVL: %-7d         | 👛 WALLET: %s%d💲%s
        %s=============================================================%s
        
        1.                 👹 [ GOBLIN ]
           ───────────────────────────────────────────────
           STATISTICS:                 | REWARDS:
           ❤️ HP: %-7d              | 💰 %d
           ⚔️ PWR:%-7d              | ✨ XP: %-5d
           ───────────────────────────────────────────────
        
        %s5. [↩️   BACK TO TOWN]%s
        
        %s=============================================================%s
        ➤ Επιλογή:\s""",
                CYAN, RESET,
                CYAN, RESET,
                player.getHealth(), GREEN, player.getPower(), RESET,
                player.getLevel(), GREEN, player.getWallet(), RESET,
                CYAN, RESET,
                // Stats Goblin
                goblin.getHp(), 200,
                goblin.getPower(), 50,
                WHITE, RESET,
                CYAN, RESET
        );

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print(ui);
    }


    /**
     * Εμφανιζει τα Loot Boxes - μονο print
     */

    private void displayLootBoxes() {
        String ui = String.format("""
        %s=============================================================%s
                          🎁  PREMIUM LOOT BOX SHOP  🎁
        %s=============================================================%s
        👛 WALLET: %s%d💲%s               | ✨ STATUS: %sLUCKY%s
        %s=============================================================%s
        
        1. 📦 [ WOODEN CRATE ] - COST: 100$
           ─────────────────────────────────────────────────────────
           POSSIBLE PRIZES:          | CHANCE:
           • 50$  (Common)           | 60%%
           • 150$ (Uncommon)         | 30%%
           • 350$ (RARE! ✨)         | 10%%
           ─────────────────────────────────────────────────────────
        
        %s5. [↩️   BACK TO TOWN]%s
        
        %s=============================================================%s
        ➤ Επιλογή:\s""",
                CYAN, RESET,
                CYAN, RESET,
                GREEN, player.getWallet(), RESET, YELLOW, RESET,
                CYAN, RESET,
                WHITE, RESET,
                CYAN, RESET
        );

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print(ui);
    }

    //Display Arena Goblin Menu - μονο print
    private void displayArenaGoblin() {
        String ui = String.format("""
                        %s=============================================================%s
                                          ⚔️  BATTLE IN PROGRESS  ⚔️
                        %s=============================================================%s
                        
                        ❤️ HP: %s%-5d%s  |  ⚔️ PWR: %s%-5d%s  |  📦 POTIONS: %s%d%s
                        
                        %s%-20s %-15s %-15s%s
                        ─────────────────────────────────────────────────────────────
                        %-20s %-15d %-15d
                        %s%-20s %-15d %-15d%s
                        ─────────────────────────────────────────────────────────────
                        
                        %s1. [⚔️ ATTACK]%s
                        %s2. [🧪 USE POTION]%s
                        %s5. [🏳️ RETREAT]%s
                        
                        =============================================================
                        ➤ Επιλογή:\s""",
                CYAN, RESET, CYAN, RESET,
                RED, player.getHealth(), RESET, GREEN, player.getPower(), RESET, CYAN, player.healthpotions, RESET,
                CYAN, "TARGET", "HEALTH", "POWER", RESET,
                "👤 YOU", player.getHealth(), player.getPower(),
                RED, "👹 GOBLIN", goblin.getHp(), goblin.getPower(), RESET,
                GREEN, RESET, CYAN, RESET, RED, RESET
        );
        System.out.print(ui);
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




