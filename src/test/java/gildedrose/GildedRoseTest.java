package gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private static final String NORMAL_ITEM = "+5 Dexterity Vest";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED_ITEM = "Conjured Mana Cake";

    private Item updateItem(String name, int sellIn, int quality) {
        Item item = new Item(name, sellIn, quality);
        new GildedRose(new Item[]{item}).updateQuality();
        return item;
    }

    @Test
    public void normalItemDecreasesQualityByOneBeforeSellByDate() {
        Item item = updateItem(NORMAL_ITEM, 10, 20);

        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    public void normalItemDecreasesQualityByTwoAfterSellByDate() {
        Item item = updateItem(NORMAL_ITEM, 0, 20);

        assertEquals(-1, item.sellIn);
        assertEquals(18, item.quality);
    }

    @Test
    public void normalItemQualityNeverGoesNegative() {
        Item item = updateItem(NORMAL_ITEM, 5, 0);

        assertEquals(0, item.quality);
    }

    @Test
    public void normalItemQualityNeverGoesNegativeAfterSellByDate() {
        Item item = updateItem(NORMAL_ITEM, 0, 1);

        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieIncreasesQualityByOneBeforeSellByDate() {
        Item item = updateItem(AGED_BRIE, 10, 20);

        assertEquals(9, item.sellIn);
        assertEquals(21, item.quality);
    }

    @Test
    public void agedBrieIncreasesQualityByTwoAfterSellByDate() {
        Item item = updateItem(AGED_BRIE, 0, 20);

        assertEquals(-1, item.sellIn);
        assertEquals(22, item.quality);
    }

    @Test
    public void agedBrieQualityNeverExceedsFifty() {
        Item item = updateItem(AGED_BRIE, 10, 50);

        assertEquals(50, item.quality);
    }

    @Test
    public void agedBrieQualityNeverExceedsFiftyAfterSellByDate() {
        Item item = updateItem(AGED_BRIE, 0, 49);

        assertEquals(50, item.quality);
    }

    @Test
    public void sulfurasNeverChangesQuality() {
        Item item = updateItem(SULFURAS, 10, 80);

        assertEquals(80, item.quality);
    }

    @Test
    public void sulfurasNeverChangesSellIn() {
        Item item = updateItem(SULFURAS, 10, 80);

        assertEquals(10, item.sellIn);
    }

    @Test
    public void backstagePassIncreasesQualityByOneWhenMoreThanTenDays() {
        Item item = updateItem(BACKSTAGE_PASS, 11, 20);

        assertEquals(21, item.quality);
    }

    @Test
    public void backstagePassIncreasesQualityByTwoWhenTenDaysOrLess() {
        Item item = updateItem(BACKSTAGE_PASS, 10, 20);

        assertEquals(22, item.quality);
    }

    @Test
    public void backstagePassIncreasesQualityByTwoAtSixDays() {
        Item item = updateItem(BACKSTAGE_PASS, 6, 20);

        assertEquals(22, item.quality);
    }

    @Test
    public void backstagePassIncreasesQualityByThreeWhenFiveDaysOrLess() {
        Item item = updateItem(BACKSTAGE_PASS, 5, 20);

        assertEquals(23, item.quality);
    }

    @Test
    public void backstagePassQualityDropsToZeroAfterConcert() {
        Item item = updateItem(BACKSTAGE_PASS, 0, 20);

        assertEquals(0, item.quality);
    }

    @Test
    public void backstagePassQualityNeverExceedsFifty() {
        Item item = updateItem(BACKSTAGE_PASS, 5, 49);

        assertEquals(50, item.quality);
    }

    @Test
    public void conjuredItemDecreasesQualityByTwoBeforeSellByDate() {
        Item item = updateItem(CONJURED_ITEM, 10, 20);

        assertEquals(9, item.sellIn);
        assertEquals(18, item.quality);
    }

    @Test
    public void conjuredItemDecreasesQualityByFourAfterSellByDate() {
        Item item = updateItem(CONJURED_ITEM, 0, 20);

        assertEquals(-1, item.sellIn);
        assertEquals(16, item.quality);
    }

    @Test
    public void conjuredItemQualityNeverGoesNegative() {
        Item item = updateItem(CONJURED_ITEM, 5, 1);

        assertEquals(0, item.quality);
    }

    @Test
    public void conjuredItemQualityNeverGoesNegativeAfterSellByDate() {
        Item item = updateItem(CONJURED_ITEM, 0, 3);

        assertEquals(0, item.quality);
    }

    @Test
    public void multipleItemsAreUpdatedIndependently() {
        Item normalItem = new Item(NORMAL_ITEM, 10, 20);
        Item agedBrie = new Item(AGED_BRIE, 10, 20);
        new GildedRose(new Item[]{normalItem, agedBrie}).updateQuality();

        assertEquals(19, normalItem.quality);
        assertEquals(21, agedBrie.quality);
    }
}
