public class Order {
    private String dateOrdered;
    private String dateDelivered;
    Order(String dateOrdered){
        this.dateOrdered = dateOrdered;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public String getDateDelivered() {
        return dateDelivered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public void setDateDelivered(String dateDelivered) {
        this.dateDelivered = dateDelivered;
    }
}
