import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean menu1 = true;
        boolean menu2 = false;
        Scanner input = new Scanner(System.in);

        Bank ALPHA = new Bank(10000);
        Account Giorgos = new Account();
        String message = "";
        Giorgos.setHealth(50);

        while (menu1) {
            System.out.println("\n===== MENU =====");
            System.out.println("ΤΡΑΠΕΖΑ: " + ALPHA.funds + "$ | ΛΟΓΑΡΙΑΣΜΟΣ: " + Giorgos.balance + "$ | HP: " + Giorgos.getHealth());
            System.out.println("1. Ανάληψη (+ fees)");
            System.out.println("2. Κατάθεση");
            System.out.println("3. Shop");
            System.out.println("4. Bet (100$)");
            System.out.println("5. ΕΞΟΔΟΣ");

            if (!message.isEmpty()) {
                System.out.println(">>> " + message);
                message = ""; // Καθαρίζουμε το μήνυμα για την επόμενη φορά
            }

            System.out.print("Επιλογή: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ποσό ανάληψης: ");
                    int requestamount = input.nextInt();
                    ALPHA.Transferto(Giorgos, requestamount);
                    message = "Επιτυχής ανάληψη! " + requestamount + "$";
                    break;
                case 2:
                    System.out.print("Ποσό κατάθεσης: ");
                    int depositamount3 = input.nextInt();
                    Giorgos.Deposit(ALPHA, depositamount3);
                    message = "Επιτυχής κατάθεση " + depositamount3 + "$";
                    break;
                case 3:
                    menu2 = true; // Ενεργοποιούμε το Shop
                    // Βγαίνουμε από το switch για να πάμε στο while(menu2) παρακάτω
                    break;
                case 4:
                    int winamount = Giorgos.Bet();
                    if (winamount != -1) {
                        message = "Κέρδισες " + winamount + "$";
                    } else {
                        message = "Δεν έχεις αρκετά χρήματα!";
                    }
                    break;
                case 5:
                    menu1 = false;
                    System.out.println("Αντίο!");
                    break;
                default:
                    message = "ΜΗ ΕΓΚΥΡΗ ΕΠΙΛΟΓΗ";
                    break;
            }

            // --- ΕΔΩ ΕΙΝΑΙ ΤΟ SHOP (Έξω από το switch, μέσα στο menu1) ---
            while (menu2) {
                System.out.println("\n===== SHOP =====");
                System.out.println("HP: " + Giorgos.getHealth() + " | Υπόλοιπο: " + Giorgos.balance + "$");
                System.out.println("1. Health Potion (100$)");
                System.out.println("2. Επιστροφή");

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
                        } else {
                            message = "Δεν έχεις αρκετά χρήματα!";
                        }
                        break;
                    case 2:
                        menu2 = false;
                        break;
                    default:
                        message = "Μη έγκυρη επιλογή στο Shop";
                }
            }
        }
    }
}