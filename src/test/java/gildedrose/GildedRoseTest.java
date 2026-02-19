package gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String NORMAL_ITEM = "Normal Item";
    private static final int MAX_QUALITY = 50;
    private static final int SULFURAS_QUALITY = 80;

    private Item updateSingleItem(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app.items[0];
    }

    @Test
    public void normalItemDecreaseQualityByOneBeforeSellDate() {
        Item item = updateSingleItem(NORMAL_ITEM, 10, 20);

        assertEquals(19, item.quality);
    }

    @Test
    public void normalItemDecreasesSellInByOne() {
        Item item = updateSingleItem(NORMAL_ITEM, 10, 20);

        assertEquals(9, item.sellIn);
    }

    @Test
    public void normalItemDegradesTwiceAsFastAfterSellDate() {
        Item item = updateSingleItem(NORMAL_ITEM, 0, 20);

        assertEquals(18, item.quality);
    }

    @Test
    public void normalItemQualityNeverGoesNegative() {
        Item item = updateSingleItem(NORMAL_ITEM, 10, 0);

        assertEquals(0, item.quality);
    }

    @Test
    public void normalItemQualityNeverGoesNegativeAfterSellDate() {
        Item item = updateSingleItem(NORMAL_ITEM, 0, 0);

        assertEquals(0, item.quality);
    }

    @Test
    public void normalItemQualityDoesNotDropBelowZeroWhenDegradingTwice() {
        Item item = updateSingleItem(NORMAL_ITEM, 0, 1);

        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieIncreasesQualityByOneBeforeSellDate() {
        Item item = updateSingleItem(AGED_BRIE, 10, 20);

        assertEquals(21, item.quality);
    }

    @Test
    public void agedBrieDecreasesSellInByOne() {
        Item item = updateSingleItem(AGED_BRIE, 10, 20);

        assertEquals(9, item.sellIn);
    }

    @Test
    public void agedBrieIncreasesQualityByTwoAfterSellDate() {
        Item item = updateSingleItem(AGED_BRIE, 0, 20);

        assertEquals(22, item.quality);
    }

    @Test
    public void agedBrieQualityNeverExceedsFifty() {
        Item item = updateSingleItem(AGED_BRIE, 10, MAX_QUALITY);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void agedBrieQualityNeverExceedsFiftyAfterSellDate() {
        Item item = updateSingleItem(AGED_BRIE, 0, MAX_QUALITY);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void agedBrieQualityIncreasesFromFortyNineToFiftyAfterSellDate() {
        Item item = updateSingleItem(AGED_BRIE, 0, 49);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void sulfurasQualityNeverChanges() {
        Item item = updateSingleItem(SULFURAS, 10, SULFURAS_QUALITY);

        assertEquals(SULFURAS_QUALITY, item.quality);
    }

    @Test
    public void sulfurasSellInNeverChanges() {
        Item item = updateSingleItem(SULFURAS, 10, SULFURAS_QUALITY);

        assertEquals(10, item.sellIn);
    }

    @Test
    public void sulfurasQualityNeverChangesAfterSellDate() {
        Item item = updateSingleItem(SULFURAS, -1, SULFURAS_QUALITY);

        assertEquals(SULFURAS_QUALITY, item.quality);
    }

    @Test
    public void sulfurasSellInNeverChangesAfterSellDate() {
        Item item = updateSingleItem(SULFURAS, -1, SULFURAS_QUALITY);

        assertEquals(-1, item.sellIn);
    }

    @Test
    public void backstagePassesIncreaseQualityByOneWhenMoreThanTenDays() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 11, 20);

        assertEquals(21, item.quality);
    }

    @Test
    public void backstagePassesIncreaseQualityByTwoAtTenDays() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 10, 20);

        assertEquals(22, item.quality);
    }

    @Test
    public void backstagePassesIncreaseQualityByTwoWhenSixToTenDays() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 8, 20);

        assertEquals(22, item.quality);
    }

    @Test
    public void backstagePassesIncreaseQualityByThreeAtFiveDays() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 5, 20);

        assertEquals(23, item.quality);
    }

    @Test
    public void backstagePassesIncreaseQualityByThreeWhenOneToFiveDays() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 3, 20);

        assertEquals(23, item.quality);
    }

    @Test
    public void backstagePassesIncreaseQualityByThreeAtOneDay() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 1, 20);

        assertEquals(23, item.quality);
    }

    @Test
    public void backstagePassesQualityDropsToZeroAfterConcert() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 0, 20);

        assertEquals(0, item.quality);
    }

    @Test
    public void backstagePassesQualityRemainsZeroAfterConcert() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, -1, 20);

        assertEquals(0, item.quality);
    }

    @Test
    public void backstagePassesDecreasesSellInByOne() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 10, 20);

        assertEquals(9, item.sellIn);
    }

    @Test
    public void backstagePassesQualityNeverExceedsFiftyWithSingleIncrement() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 11, MAX_QUALITY);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void backstagePassesQualityNeverExceedsFiftyWithDoubleIncrement() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 10, 49);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void backstagePassesQualityNeverExceedsFiftyWithTripleIncrement() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 5, 49);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void backstagePassesQualityAtFiftyWithDoubleIncrement() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 10, MAX_QUALITY);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void backstagePassesQualityAtFiftyWithTripleIncrement() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 5, MAX_QUALITY);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void emptyItemsArrayDoesNotThrow() {
        Item[] items = new Item[]{};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items.length);
    }

    @Test
    public void multipleItemsAreAllUpdated() {
        Item[] items = new Item[]{
                new Item(NORMAL_ITEM, 10, 20),
                new Item(AGED_BRIE, 5, 30),
                new Item(SULFURAS, 0, SULFURAS_QUALITY)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(19, app.items[0].quality);
        assertEquals(31, app.items[1].quality);
        assertEquals(SULFURAS_QUALITY, app.items[2].quality);
    }

    @Test
    public void normalItemSellInBecomesNegative() {
        Item item = updateSingleItem(NORMAL_ITEM, 0, 20);

        assertEquals(-1, item.sellIn);
    }

    @Test
    public void normalItemQualityAtOneBeforeSellDateGoesToZero() {
        Item item = updateSingleItem(NORMAL_ITEM, 5, 1);

        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieQualityAtFortyNineBeforeSellDateGoesToFifty() {
        Item item = updateSingleItem(AGED_BRIE, 5, 49);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void backstagePassesQualityAtFortyEightWithTripleIncrementGoesToFifty() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 5, 48);

        assertEquals(MAX_QUALITY, item.quality);
    }

    @Test
    public void normalItemWithHighSellInDegradesNormally() {
        Item item = updateSingleItem(NORMAL_ITEM, 100, 30);

        assertEquals(29, item.quality);
    }

    @Test
    public void backstagePassesAtSellInSixIncreaseByTwo() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 6, 20);

        assertEquals(22, item.quality);
    }

    @Test
    public void backstagePassesAtSellInElevenIncreaseByOne() {
        Item item = updateSingleItem(BACKSTAGE_PASSES, 11, 20);

        assertEquals(21, item.quality);
    }

    @Test
    public void normalItemAtSellInOneDecreasesByOne() {
        Item item = updateSingleItem(NORMAL_ITEM, 1, 10);

        assertEquals(9, item.quality);
        assertEquals(0, item.sellIn);
    }

    @Test
    public void normalItemAtSellInNegativeOneDecreasesByTwo() {
        Item item = updateSingleItem(NORMAL_ITEM, -1, 10);

        assertEquals(8, item.quality);
    }

    @Test
    public void agedBrieAtSellInNegativeOneIncreasesQualityByTwo() {
        Item item = updateSingleItem(AGED_BRIE, -1, 10);

        assertEquals(12, item.quality);
    }
}
