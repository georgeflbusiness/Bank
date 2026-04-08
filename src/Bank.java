public class Bank {
    private int funds;
    private final int MIN_FEES = 2;
    private final int MAX_FEES = 10;

    //Getter
    public int getFunds() {
        return this.funds;
    }
    //Setter
    public void setFunds(int funds) {
        this.funds = funds;
    }


    public void transferTo(Player player, int funds) {
        if (funds <= this.getFunds()) {
            if (funds > 0 && funds < 1000) {
                player.setWallet((player.getWallet() + funds) - MIN_FEES);
                this.setFunds(this.getFunds() - funds);
                System.out.println("2$ fees! ");
            }
            else if (funds >= 1000) {
                player.setWallet((player.getWallet() + funds) - MAX_FEES);
                this.setFunds(this.getFunds() - funds);
                System.out.println("10$ fees");
            }
            else {
                System.out.println("Μη έγκυρο ποσό");

            }

        }

    }
}