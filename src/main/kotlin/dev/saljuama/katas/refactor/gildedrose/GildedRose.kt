package dev.saljuama.katas.refactor.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        fun isLegendaryItem(item: String) = listOf("Sulfuras, Hand of Ragnaros").contains(item)
        fun isWellAgingItem(item:String) = listOf("Aged Brie").contains(item)
        fun isBackstagePass(item:String) = item.contains("Backstage passes")

        for (i in items.indices) {
            if (isLegendaryItem(items[i].name))
                continue

            items[i].sellIn -= 1

            if (isWellAgingItem(items[i].name)) {
                if (items[i].quality < 50)
                    items[i].quality += 1
            }
            else if (isBackstagePass(items[i].name)) {
                when {
                    items[i].sellIn > 10 -> items[i].quality += 1
                    items[i].sellIn in 6..10 -> items[i].quality += 2
                    items[i].sellIn in 0..5 -> items[i].quality += 3
                    else -> items[i].quality = 0
                }
            }
            else {
                if (items[i].quality > 0)
                    items[i].quality -= 1
                if (items[i].sellIn < 0 && items[i].quality > 0)
                    items[i].quality -= 1
            }
        }
    }

}
