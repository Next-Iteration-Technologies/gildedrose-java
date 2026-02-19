package gildedrose;

class AgedBrieUpdater extends ItemUpdater {

    private static final int DAILY_APPRECIATION = 1;

    @Override
    protected void updateQuality(Item item) {
        increaseQuality(item, DAILY_APPRECIATION);
    }

    @Override
    protected void updateExpiredQuality(Item item) {
        increaseQuality(item, DAILY_APPRECIATION);
    }
}
