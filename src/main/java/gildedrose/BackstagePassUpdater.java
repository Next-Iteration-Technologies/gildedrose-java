package gildedrose;

class BackstagePassUpdater extends ItemUpdater {

    private static final int DOUBLE_INCREASE_THRESHOLD = 11;
    private static final int TRIPLE_INCREASE_THRESHOLD = 6;

    @Override
    protected void updateQuality(Item item) {
        if (item.sellIn < TRIPLE_INCREASE_THRESHOLD) {
            increaseQuality(item, 3);
        } else if (item.sellIn < DOUBLE_INCREASE_THRESHOLD) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }

    @Override
    protected void updateExpiredQuality(Item item) {
        item.quality = MIN_QUALITY;
    }
}
