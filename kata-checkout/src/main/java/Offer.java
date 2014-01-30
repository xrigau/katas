public class Offer {

    private final String item;
    private final int quantity;
    private final int offerPrice;

    public Offer(String item, int quantity, int offerPrice) {
        this.item = item;
        this.quantity = quantity;
        this.offerPrice = offerPrice;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOfferPrice() {
        return offerPrice;
    }
}
