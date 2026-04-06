public class Bank {
    private int funds;
    public final int SMALL_FEE = 2;
    public final int LARGE_FEE = 10;

    //GETTER
    public int getFunds() {
        return this.funds;
    }
    //SETTER
    public void setFunds(int funds) {
        this.funds = funds;
    }


    public void transferTo(Player amount, int funds) {
        if (funds <= this.getFunds()) {
            if (funds > 0 && funds < 1000) {
                amount.wallet += funds;
                this.setFunds(this.getFunds() - funds);
                amount.wallet -= SMALL_FEE;
                System.out.println("2$ fees! ");
            }
            else if (funds >= 1000) {
                amount.wallet += funds;
                this.setFunds(this.getFunds() - funds);
                amount.wallet -= LARGE_FEE;
                System.out.println("10$ fees");
            }
            else {
                System.out.println("Μη έγκυρο ποσό");

            }

        }

    }
}