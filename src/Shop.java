public class Shop {
    private Player player;

    //Costs
    private  int healthPotionCost = 100;
    private  int xpPotionCost = 150;


    //Constructor
    public Shop(Player player) {
        this.player = player;
    }


    //------------------------------------------------------------------------------------------------------------------
    //Getter
    public int getHealthPotionCost() {
        return healthPotionCost;
    }
    //Getter
    public int getXpPotionCost() {
        return xpPotionCost;
    }



    //Setter
    public void setHealthPotionCost(int price) {
        this.healthPotionCost = price;
    }


    //------------------------------------------------------------------------------------------------------------------


    //Buy health Potion
    public int buyHealthPotion() {

        if (player.getWallet() < healthPotionCost) {
            return -1;//not enough money
        }

        //Buy the health potion
        player.setWallet(player.getWallet() - healthPotionCost);
        //Create the object of the health potion and add it to the inventory
        Item HealthPotion = new Item("Health Potion", "Restores 50 HP", healthPotionCost);
        //Input at inventory
        player.addItem(HealthPotion);
        return 0; //success


    }



    //Buy XP Potion
    public int buyXpPotion() {

        if (player.getWallet() < this.xpPotionCost) {
            return -1;
        }

        player.setWallet(player.getWallet() - xpPotionCost);
        Item xpPotion = new Item("XP Potion","Gain 25 XP",xpPotionCost);
        player.addItem(xpPotion);
        return 0;//Success Buy

    }










































}