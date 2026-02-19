package gildedrose;

abstract class ItemUpdater {

    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;

    public void update(Item item) {
        updateQuality(item);
        decreaseSellIn(item);
        if (isExpired(item)) {
            updateExpiredQuality(item);
        }
    }

    protected abstract void updateQuality(Item item);

    protected abstract void updateExpiredQuality(Item item);

    protected void decreaseSellIn(Item item) {
        item.sellIn--;
    }

    protected boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    protected void increaseQuality(Item item, int amount) {
        item.quality = Math.min(item.quality + amount, MAX_QUALITY);
    }

    protected void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, MIN_QUALITY);
    }
}
