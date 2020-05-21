package dev.saljuama.katas.refactor.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        val legendaryItems = listOf("Sulfuras, Hand of Ragnaros")
        val wellAgingItems = listOf("Aged Brie")

        for (i in items.indices) {
            if (legendaryItems.contains(items[i].name))
                continue
            if (wellAgingItems.contains(items[i].name)) {
                items[i].sellIn = items[i].sellIn - 1
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1
                }
                continue
            }

            if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) {
                    items[i].quality = items[i].quality - 1
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            items[i].sellIn = items[i].sellIn - 1
            if (items[i].sellIn < 0) {

                if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (items[i].quality > 0) {
                        items[i].quality = items[i].quality - 1
                    }
                } else {
                    items[i].quality = items[i].quality - items[i].quality
                }

            }
        }
    }

}
