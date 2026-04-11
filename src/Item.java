public class Item {
    private String name;
    private String description;
    private int price;

    public Item(String name, String Description, int price)  {
        this.name = name;
        this.description = Description;
        this.price = price;
    }


    //Getter
    public String getName() {
        return name;
    }
    //Getter
    public String getDescription() {
        return description;
    }
    //Getter
    public int getPrice() {
        return price;
    }


    //Setter
    public void setPrice(int price) {
        this.price = price;
    }
}
