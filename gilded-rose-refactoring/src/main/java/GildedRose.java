import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GildedRose {

    private static List<Item> items = null;

    public GildedRose(Item... items) {
        GildedRose.items = new ArrayList<Item>();
        GildedRose.items.addAll(Arrays.asList(items));
    }

    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
            update(items.get(i));
        }
    }

    private void update(Item item) {
        if (isBackstagePasses(item)) {
            new BackstagePassesRules(item).updateQuality();
            new BackstagePassesRules(item).updateSellIn();
            return;
        }
        updateQuality(item);
        updateSellIn(item);
    }

    private void updateQuality(Item item) {
        if (itemWithDecreasingQuality(item)) {
            updateDecreasingQualityItem(item);
        } else {
            ItemUpdater.increaseQuality(item);
        }
    }

    private void updateSellIn(Item item) {
        if (isSulfuras(item)) {
            return;
        }
        ItemUpdater.decreaseSellIn(item);
    }

    private void updateDecreasingQualityItem(Item item) {
        if (item.getQuality() <= 0 || isSulfuras(item)) {
            return;
        }

        if (isConjuredMana(item)) {
            updateConjuredMana(item);
        } else {
            ItemUpdater.decreaseQuality(item);
        }
    }

    private void updateConjuredMana(Item item) {
        ItemUpdater.decreaseQuality(item);
        ItemUpdater.decreaseQuality(item);
    }

    private boolean itemWithDecreasingQuality(Item item) {
        return (!isAgedBrie(item));
    }

    private boolean isSulfuras(Item item) {
        return itemNameIs("Sulfuras, Hand of Ragnaros", item);
    }

    private boolean isBackstagePasses(Item item) {
        return itemNameIs("Backstage passes to a TAFKAL80ETC concert", item);
    }

    private boolean isAgedBrie(Item item) {
        return itemNameIs("Aged Brie", item);
    }

    private boolean isConjuredMana(Item item) {
        return itemNameIs("Conjured Mana Cake", item);
    }

    private boolean itemNameIs(String name, Item item) {
        return name.equals(item.getName());
    }

}