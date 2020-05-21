package dev.saljuama.katas.refactor.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        val legendaryItems = listOf("Sulfuras, Hand of Ragnaros")
        val wellAgingItems = listOf("Aged Brie")

        for (i in items.indices) {
            if (legendaryItems.contains(items[i].name))
                continue
            if (wellAgingItems.contains(items[i].name)) {
                items[i].sellIn -= 1
                if (items[i].quality < 50) {
                    items[i].quality += 1
                }
                continue
            }
            if (items[i].name.contains("Backstage passes")) {
                items[i].sellIn -= 1
                when {
                    items[i].sellIn > 10 -> items[i].quality += 1
                    items[i].sellIn in 6..10 -> items[i].quality += 2
                    items[i].sellIn in 0..5 -> items[i].quality += 3
                    else -> items[i].quality = 0
                }
                continue
            }

            items[i].sellIn = items[i].sellIn - 1
            if (items[i].quality > 0) {
                items[i].quality -= 1
            }
            if (items[i].sellIn < 0 && items[i].quality > 0) {
                items[i].quality -= 1
            }
        }
    }

}
