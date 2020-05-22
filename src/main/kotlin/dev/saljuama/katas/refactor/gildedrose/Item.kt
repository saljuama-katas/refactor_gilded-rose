package dev.saljuama.katas.refactor.gildedrose

open class Item(var name: String, var sellIn: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }
}

interface SmarterItem {
    fun updateQuality(): Item
}

fun itemFactory(item: Item): SmarterItem {
    fun isLegendaryItem(item: String) = listOf("Sulfuras, Hand of Ragnaros").contains(item)
    fun isWellAgingItem(item:String) = listOf("Aged Brie").contains(item)
    fun isBackstagePass(item:String) = item.contains("Backstage passes")
    fun isConjuredItem(item:String) = item.contains("Conjured")

    if (isLegendaryItem(item.name))
        return LegendaryItem(item.name, item.sellIn, item.quality)
    if (isWellAgingItem(item.name))
        return WellAgingItem(item.name, item.sellIn, item.quality)
    if (isBackstagePass(item.name))
        return BackstagePassesItem(item.name, item.sellIn, item.quality)
    if (isConjuredItem(item.name))
        return ConjuredItem(item.name, item.sellIn, item.quality)
    return RegularItem(item.name, item.sellIn, item.quality)
}

class LegendaryItem(val name: String, val sellIn: Int, val quality: Int) : SmarterItem {
    override fun updateQuality(): Item = Item(name, sellIn, quality)
}

class WellAgingItem(val name: String, val sellIn: Int, val quality: Int) : SmarterItem {
    override fun updateQuality(): Item = Item(name, sellIn - 1, when {
        quality < 50 -> quality + 1
        else -> quality
    })
}

class BackstagePassesItem(val name: String, val sellIn: Int, val quality: Int) : SmarterItem {
    override fun updateQuality(): Item = Item(name, sellIn - 1, when {
        sellIn - 1 > 10 -> quality + 1
        sellIn - 1 in 6..10 -> quality + 2
        sellIn - 1 in 0..5 -> quality + 3
        else ->  0
    })
}

class RegularItem(val name: String, val sellIn: Int, val quality: Int) : SmarterItem {
    override fun updateQuality(): Item {
        var newQuality = quality
        if (quality > 0 ) newQuality -= 1
        if (sellIn - 1 < 0 && newQuality > 0 ) newQuality -= 1
        return Item(name, sellIn - 1, newQuality)
    }
}

class ConjuredItem(val name: String, val sellIn: Int, val quality: Int) : SmarterItem {
    override fun updateQuality(): Item {
        var newQuality = quality
        if (quality > 0 ) newQuality -= 2
        if (sellIn - 1 < 0 && newQuality > 0 ) newQuality -= 2
        if (newQuality < 0 ) newQuality = 0
        return Item(name, sellIn - 1, newQuality)
    }
}
