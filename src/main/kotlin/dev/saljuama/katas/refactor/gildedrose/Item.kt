package dev.saljuama.katas.refactor.gildedrose

open class Item(var name: String, var sellIn: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }
}

interface SmarterItem {
    fun updateQuality(): Item
}

fun detectItemType(item: Item): SmarterItem {
    fun isLegendaryItem(item: String) = listOf("Sulfuras, Hand of Ragnaros").contains(item)
    fun isWellAgingItem(item:String) = listOf("Aged Brie").contains(item)
    fun isBackstagePass(item:String) = item.contains("Backstage passes")
    fun isConjuredItem(item:String) = item.contains("Conjured")

    return when {
        isLegendaryItem(item.name) -> LegendaryItem(item)
        isWellAgingItem(item.name) -> WellAgingItem(item)
        isBackstagePass(item.name) -> BackstagePassesItem(item)
        isConjuredItem(item.name) -> ConjuredItem(item)
        else -> RegularItem(item)
    }
}

class LegendaryItem(val item: Item) : SmarterItem {
    override fun updateQuality(): Item = item
}

class WellAgingItem(val item: Item) : SmarterItem {
    override fun updateQuality(): Item = Item(item.name, item.sellIn - 1, when {
        item.quality < 50 -> item.quality + 1
        else -> item.quality
    })
}

class BackstagePassesItem(val item: Item) : SmarterItem {
    override fun updateQuality(): Item = Item(item.name, item.sellIn - 1, when {
        item.sellIn - 1 > 10 -> item.quality + 1
        item.sellIn - 1 in 6..10 -> item.quality + 2
        item.sellIn - 1 in 0..5 -> item.quality + 3
        else ->  0
    })
}

class RegularItem(val item: Item) : SmarterItem {
    override fun updateQuality(): Item {
        var newQuality = item.quality
        if (item.quality > 0 ) newQuality -= 1
        if (item.sellIn - 1 < 0 && newQuality > 0 ) newQuality -= 1
        return Item(item.name, item.sellIn - 1, newQuality)
    }
}

class ConjuredItem(val item: Item) : SmarterItem {
    override fun updateQuality(): Item {
        var newQuality = item.quality
        if (item.quality > 0 ) newQuality -= 2
        if (item.sellIn - 1 < 0 && newQuality > 0 ) newQuality -= 2
        if (newQuality < 0 ) newQuality = 0
        return Item(item.name, item.sellIn - 1, newQuality)
    }
}
