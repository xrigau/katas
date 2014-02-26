public class ItemUpdater {

    private static final int MAX_QUALITY = 50;
    private static final int WORTHLESS_PRODUCT_QUALITY = 0;

    public static void makeItemWorthless(Item item) {
        item.setQuality(WORTHLESS_PRODUCT_QUALITY);
    }

    public static void decreaseSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    public static void decreaseQuality(Item item) {
        item.setQuality(item.getQuality() - 1);
    }

    public static void increaseQuality(Item item) {
        if (maxQualityNotReached(item)) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private static boolean maxQualityNotReached(Item item) {
        return item.getQuality() < MAX_QUALITY;
    }
}
