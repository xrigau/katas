import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    @Test
    public void emptyBasketIsZero() throws Exception {
        Basket basket = new Basket(Collections.<String, Integer>emptyMap(), Collections.<Offer>emptyList());

        assertEquals(0, basket.getTotal());
    }

    @Test
    public void whenOneItemInBasket_totalIsTheItemPrice() {
        Map<String, Integer> prices = new HashMap<String, Integer>();
        prices.put("A", 50);

        Basket basket = new Basket(prices, Collections.<Offer>emptyList());
        basket.addItem("A");

        assertEquals(50, basket.getTotal());
    }

    @Test
    public void whenTwoDifferentItemsInBasket_totalIsSumOfPrices() {

        Map<String, Integer> prices = new HashMap<String, Integer>();
        prices.put("A", 50);
        prices.put("B", 30);

        Basket basket = new Basket(prices, Collections.<Offer>emptyList());

        basket.addItem("A");
        basket.addItem("B");

        assertEquals(80, basket.getTotal());
    }

    @Test
    public void whenInAMultipricedItemInBasket_applyMultipriceOffer() {
        Map<String, Integer> prices = new HashMap<String, Integer>();
        prices.put("A", 50);

        List<Offer> offers = new ArrayList<Offer>();
        offers.add(new Offer("A", 3, 130));

        Basket basket = new Basket(prices, offers);

        basket.addItem("A");
        basket.addItem("A");
        basket.addItem("A");

        assertEquals(130, basket.getTotal());
    }

    @Test
    public void whenInAnotherMultipricedItemInBasket_applyMultipriceOffer() {
        Map<String, Integer> prices = new HashMap<String, Integer>();
        prices.put("B", 30);

        List<Offer> offers = new ArrayList<Offer>();
        offers.add(new Offer("B", 2, 45));

        Basket basket = new Basket(prices, offers);

        basket.addItem("B");
        basket.addItem("B");

        assertEquals(45, basket.getTotal());
    }

    @Test
    public void whenMultipleOccurrencesOfOfferInBasket_applyMultipriceOfferMultipleTimes() {
        Map<String, Integer> prices = new HashMap<String, Integer>();
        prices.put("B", 30);

        List<Offer> offers = new ArrayList<Offer>();
        offers.add(new Offer("B", 2, 45));

        Basket basket = new Basket(prices, offers);

        basket.addItem("B");
        basket.addItem("B");
        basket.addItem("B");
        basket.addItem("B");
        basket.addItem("B");

        assertEquals(120, basket.getTotal());
    }

    @Test
    public void whenMixingOffersAndNoOffers_applyOffersAndNormalPrice() {
        Map<String, Integer> prices = new HashMap<String, Integer>();
        prices.put("A", 50);
        prices.put("B", 30);
        prices.put("C", 20);

        List<Offer> offers = new ArrayList<Offer>();
        offers.add(new Offer("A", 3, 130));
        offers.add(new Offer("B", 2, 45));

        Basket basket = new Basket(prices, offers);

        basket.addItem("C");
        basket.addItem("A");
        basket.addItem("B");
        basket.addItem("B");
        basket.addItem("B");
        basket.addItem("A");
        basket.addItem("B");
        basket.addItem("A");

        assertEquals(240, basket.getTotal());
    }
}
