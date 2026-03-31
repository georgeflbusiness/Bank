import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean menu1 = true;
        boolean menu2 = false;
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Random rand = new Random();
        Bank ALPHA = new Bank(10000);
        Account Giorgos = new Account();
        Giorgos.setHealth(50);

        while (menu1) {
            System.out.println("=====MENU=====");
            System.out.println("ΤΡΑΠΕΖΑ " + ALPHA.funds + "$" + " -- " + "ΛΟΓΑΡΙΑΣΜΟΣ " + Giorgos.balance + "$" + " -- " + "Health " + Giorgos.getHealth());
            System.out.println("1.Ανάληψη(+ fees)");
            System.out.println("2.Κατάθεση");
            System.out.println("3.Shop");
            System.out.println("4.Bet(100$) and win up to 200$");
            System.out.println("5.ΕΞΟΔΟΣ");
            int choice = input.nextInt();

            if (choice == 1) {
                System.out.print("Επέλεξε ποσό μεταφοράς ");
                int requestamount = input.nextInt();
                ALPHA.Transferto(Giorgos, requestamount);
            } else if (choice == 2) {
                System.out.print("Επέλεξε ποσό μεταφοράς ");
                int depositamount3 = input.nextInt();
                Giorgos.Deposit(ALPHA, depositamount3);
            } else if (choice == 3) {
                menu1 = false;
                menu2 = true;
            } else if (choice == 4) {
                Giorgos.Bet();
            } else if (choice == 5) {
                menu1 = false;
            } else {
                System.out.println("ΜΗ ΕΓΚΥΡΗ ΕΠΙΛΟΓΗ");
            }

            while (menu2) {
                System.out.println("=====SHOP=====");
                System.out.println("ΤΡΑΠΕΖΑ " + ALPHA.funds + "$" + " -- " + "ΛΟΓΑΡΙΑΣΜΟΣ " + Giorgos.balance + "$" + " -- " + "Health " + Giorgos.getHealth());
                System.out.println("1.Health Potion(100$)");
                System.out.println("5.Επιστροφή");
                int choice2 = input2.nextInt();

                if (choice2 == 1) {
                    Giorgos.HealthPotion();
                } else if (choice2 == 5) {
                    menu2 = false;
                    menu1 = true;
                }

            }
        }
    }

}


