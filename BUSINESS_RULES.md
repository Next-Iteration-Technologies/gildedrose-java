# Gilded Rose - Business Rules

The Gilded Rose is an inn that buys and sells goods. Each item in the inventory has the following properties:

- **Name** - the name of the item
- **SellIn** - the number of days remaining to sell the item
- **Quality** - the current quality value of the item

At the end of each day, the system updates the `sellIn` and `quality` values for every item in the inventory.

## General Rules

- The `quality` of an item is never negative.
- The `quality` of an item never exceeds 50, except for "Sulfuras" which always has a quality of 80.
- The `sellIn` value decreases by 1 each day for all items except "Sulfuras".

## Item-Specific Rules

### Normal Items

- Quality decreases by 1 each day.
- Once the sell-by date has passed (`sellIn` < 0), quality decreases by 2 each day.

### Aged Brie

- Quality increases by 1 each day, instead of decreasing.
- Once the sell-by date has passed (`sellIn` < 0), quality increases by 2 each day.

### Backstage Passes (Backstage passes to a TAFKAL80ETC concert)

- Quality increases by 1 each day when there are more than 10 days until the concert.
- Quality increases by 2 each day when there are 10 days or fewer until the concert.
- Quality increases by 3 each day when there are 5 days or fewer until the concert.
- Quality drops to 0 after the concert (`sellIn` < 0).

### Sulfuras (Sulfuras, Hand of Ragnaros)

- This is a legendary item.
- The `sellIn` value never changes.
- The `quality` value never changes.
