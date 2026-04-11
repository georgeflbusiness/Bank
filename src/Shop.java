public class Shop {
    private Player player;
    private  int healthPotionCost = 100;
    private static int levelPotionCost = 150;


    //Constructor
    public Shop(Player player) {
        this.player = player;
    }


    //Getter
    public int getHealthPotionCost() {
        return healthPotionCost;
    }
    //Getter
    public int getLevelPotionCost() {
        return levelPotionCost;
    }



    //Setter
    public void setHealthPotionCost(int price) {
        this.healthPotionCost = price;
    }


    //Buy health Potion
    public int buyHealthPotion() {

        if (player.getWallet() < healthPotionCost) {
            return -1;//not enough money
        }

        player.setWallet(player.getWallet() - healthPotionCost);
        player.setHealthpotions(player.getHealthpotions() + 1);
        return 0; //success
    }


}