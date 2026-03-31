import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean start_menu = true;
        boolean shop_menu = false;
        Scanner input = new Scanner(System.in);

        Bank ALPHA = new Bank(10000);
        Account Giorgos = new Account();
        String message = "";
        Giorgos.setHealth(50);

        while (start_menu) {
            System.out.println("\n===== MENU =====");
            System.out.println("ΤΡΑΠΕΖΑ: " + ALPHA.funds + "$ | ΛΟΓΑΡΙΑΣΜΟΣ: " + Giorgos.wallet + "$ | HP: " + Giorgos.getHealth() + " | Lvl: " + Giorgos.level + " | XP: " + Giorgos.xp + " / 100 " );
            System.out.println("1. Ανάληψη (+ fees)");
            System.out.println("2. Κατάθεση");
            System.out.println("3. Shop");
            System.out.println("4. Bet (100$)");
            System.out.println("5. ΕΞΟΔΟΣ");

            if (!message.isEmpty()) {
                System.out.println(">>> " + message);
                message = ""; // Καθαρίζουμε το μήνυμα για την επόμενη φορά!
            }

            System.out.print("Επιλογή: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ποσό ανάληψης: ");
                    int requestamount = input.nextInt();
                    if (requestamount <= ALPHA.funds) {
                        message = "Επιτυχής ανάληψη! " + requestamount + "$";
                        ALPHA.Transferto(Giorgos, requestamount);
                    }
                    else {
                        message = "Δεν έχεις αρκετά χρήματα στην τράπεζα";
                    }
                    break;
                case 2:
                    System.out.print("Ποσό κατάθεσης: ");
                    int depositamount3 = input.nextInt();
                    Giorgos.Deposit(ALPHA, depositamount3);
                    message = "Επιτυχής κατάθεση " + depositamount3 + "$";
                    break;
                case 3:
                    shop_menu = true; // Ενεργοποιούμε το Shop
                    // Βγαίνουμε από το switch για να πάμε στο while(menu2) παρακάτω
                    break;
                case 4:
                    int winamount = Giorgos.Bet();
                    if (winamount != -1) {
                        message = "Κέρδισες " + winamount + "$";
                            if (winamount > 100) {
                                Giorgos.addXp(20);
                            }
                    } else {
                        message = "Δεν έχεις αρκετά χρήματα!";
                    }
                    break;
                case 5:
                    start_menu = false;
                    System.out.println("Αντίο!");
                    break;
                default:
                    message = "ΜΗ ΕΓΚΥΡΗ ΕΠΙΛΟΓΗ";
                    break;
            }

            // --- ΕΔΩ ΕΙΝΑΙ ΤΟ SHOP (Έξω από το switch, μέσα στο menu1) ---
            while (shop_menu) {
                System.out.println("\n===== SHOP =====");
                System.out.println("HP: " + Giorgos.getHealth() + " | Υπόλοιπο: " + Giorgos.wallet + "$" + "| Lvl: " + Giorgos.level + " | XP: " + Giorgos.xp + " / 100 " );
                System.out.println("1. Health Potion (100$)");
                System.out.println("2. Level up Potion (200$)");
                System.out.println("3. Επιστροφή");

                if (!message.isEmpty()) {
                    System.out.println(">>> " + message);
                    message = "";
                }

                System.out.print("Επιλογή Shop: ");
                int choice2 = input.nextInt();

                switch (choice2) {
                    case 1:
                        int healing = Giorgos.HealthPotion();
                        if (healing != -1) {
                            message = "Αγόρασες Health Potion! Πήρες " + healing + " HP";
                        }

                        else {
                            message = "Δεν έχεις αρκετά χρήματα!";
                        }

                        break;
                    case 2:
                        Giorgos.levelup();
                        break;
                    case 3:
                    shop_menu = false;

                    default:
                        message = "Μη έγκυρη επιλογή στο Shop";
                }
            }
        }
    }
}