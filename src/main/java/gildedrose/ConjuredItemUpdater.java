package gildedrose;

class ConjuredItemUpdater extends ItemUpdater {

    private static final int DAILY_DEGRADATION = 2;

    @Override
    protected void updateQuality(Item item) {
        decreaseQuality(item, DAILY_DEGRADATION);
    }

    @Override
    protected void updateExpiredQuality(Item item) {
        decreaseQuality(item, DAILY_DEGRADATION);
    }
}
