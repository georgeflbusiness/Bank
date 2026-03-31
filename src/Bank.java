public class Bank {
    int funds;

    Bank(int funds) {
        this.funds = funds;
    }

    public void Transferto(Account amount, int funds) {
        if (funds <= this.funds) {
            if (funds > 0 && funds < 1000) {
                amount.balance += funds;
                this.funds -= funds;
                amount.balance -= 2;
                System.out.println("2$ fees ");
            }
            else if (funds >= 1000) {
                amount.balance += funds;
                this.funds -= funds;
                amount.balance -= 10;
                System.out.println("10$ fees");
            }
            else {
                System.out.println("Μη έγκυρο ποσό");

            }

        }

    }
}