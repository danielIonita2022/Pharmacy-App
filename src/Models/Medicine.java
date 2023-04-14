public class Medicine {
    private String name;
    private int quantity;
    private String type;
    private String price;
    private String brand;
    private String category;
    private int supply;
    private String measureUnit;

    public Medicine()
    {

    }
    public Medicine(String name, int quantity, String type, String price, String brand, String category, int supply, String measureUnit){
        this.name = name;
        this.quantity = quantity;
        this.type = type;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.supply = supply;
        this.measureUnit = measureUnit;
    }

    public String getName(){return name;}
    public int getQuantity(){return quantity;}
    public String getType(){return type;}
    public String getPrice(){return price;}
    public String getBrand(){return brand;}
    public String getCategory(){return category;}
    public int getSupply(){return supply;}
    public String getMeasureUnit(){return measureUnit;}

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public void setSupply(int supply) {
        this.supply = supply;
    }
    public void setType(String type) {
        this.type = type;
    }

}
