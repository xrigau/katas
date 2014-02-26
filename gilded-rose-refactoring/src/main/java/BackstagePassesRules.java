
public class BackstagePassesRules implements ItemRules {

    private final Item item;

    public BackstagePassesRules(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (itemSellInExpired(item)) {
            ItemUpdater.makeItemWorthless(item);
            return;
        }
        updateNonExpired();
    }

    private boolean itemSellInExpired(Item item) {
        return item.getSellIn() <= 0;
    }

    private void updateNonExpired() {
        ItemUpdater.increaseQuality(item);

        if (item.getSellIn() < 11) {
            ItemUpdater.increaseQuality(item);
        }

        if (item.getSellIn() < 6) {
            ItemUpdater.increaseQuality(item);
        }
    }


    @Override
    public void updateSellIn() {
        ItemUpdater.decreaseSellIn(item);
    }
}
