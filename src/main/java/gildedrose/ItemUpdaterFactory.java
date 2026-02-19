package gildedrose;

import java.util.Map;

class ItemUpdaterFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED_PREFIX = "Conjured";

    private static final Map<String, ItemUpdater> UPDATERS = Map.of(
            AGED_BRIE, new AgedBrieUpdater(),
            SULFURAS, new SulfurasUpdater(),
            BACKSTAGE_PASS, new BackstagePassUpdater()
    );

    private static final ItemUpdater NORMAL_UPDATER = new NormalItemUpdater();
    private static final ItemUpdater CONJURED_UPDATER = new ConjuredItemUpdater();

    static ItemUpdater updaterFor(Item item) {
        if (UPDATERS.containsKey(item.name)) {
            return UPDATERS.get(item.name);
        }
        if (item.name.startsWith(CONJURED_PREFIX)) {
            return CONJURED_UPDATER;
        }
        return NORMAL_UPDATER;
    }
}
