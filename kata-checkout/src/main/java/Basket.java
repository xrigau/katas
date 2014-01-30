import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Basket {

    private final List<String> items = new ArrayList<String>();

    private final Map<String, Integer> prices;
    private final List<Offer> offers;

    public Basket(Map<String, Integer> prices, List<Offer> offers) {
        this.prices = prices;
        this.offers = offers;
    }

    public void addItem(String itemName) {
        items.add(itemName);
    }

    public int getTotal() {
        int total = 0;

        for (String item : items) {
            total += prices.get(item);
        }

        for (Offer offer: offers) {
            int itemCount = countInstancesOfItem(offer.getItem());

            if (canApplyOffer(offer, itemCount)) {
                total = applyOffer(total, offer, itemCount);
            }
        }
        return total;
    }

    private int applyOffer(int total, Offer offer, int itemCount) {
        int numberOfOfferOccurrences = itemCount / offer.getQuantity();
        int totalAfterOffer = total;

        for (int i = 0; i < numberOfOfferOccurrences; i++) {
            totalAfterOffer -= offer.getQuantity() * prices.get(offer.getItem());
            totalAfterOffer += offer.getOfferPrice();
        }
        return totalAfterOffer;
    }

    private boolean canApplyOffer(Offer offer, int itemCount) {
        return itemCount >= offer.getQuantity();
    }

    private int countInstancesOfItem(String itemToFind) {
        int itemCount = 0;
        for (String item: items) {
            if (item.equals(itemToFind)) {
                itemCount++;
            }
        }
        return itemCount;
    }
}
