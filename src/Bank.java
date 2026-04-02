public class Bank {
    int funds;
    public final int SMALL_FEE = 2;
    public final int LARGE_FEE = 10;


    public Bank(int funds) {
        this.funds = funds;
    }


    public void Transferto(Account amount, int funds) {
        if (funds <= this.funds) {
            if (funds > 0 && funds < 1000) {
                amount.wallet += funds;
                this.funds -= funds;
                amount.wallet -= SMALL_FEE;
                System.out.println("2$ fees! ");
            }
            else if (funds >= 1000) {
                amount.wallet += funds;
                this.funds -= funds;
                amount.wallet -= LARGE_FEE;
                System.out.println("10$ fees");
            }
            else {
                System.out.println("Μη έγκυρο ποσό");

            }

        }

    }
}