public class Bank {
    private int funds;

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
                int playerwallet = player.getWallet();
                int newplayerwallet = playerwallet + funds;
                player.setWallet(newplayerwallet - 2);
                this.setFunds(this.getFunds() - funds);
                System.out.println("2$ fees! ");
            }
            else if (funds >= 1000) {
                int playerwallet = player.getWallet();
                int newplayewallet = playerwallet + funds;
                player.setWallet(newplayewallet - 10);
                this.setFunds(this.getFunds() - funds);
                System.out.println("10$ fees");
            }
            else {
                System.out.println("Μη έγκυρο ποσό");

            }

        }

    }
}