package dev.saljuama.katas.refactor.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items = items
            .map { itemFactory(it) }
            .map { it.updateQuality() }
            .toTypedArray()
    }

}
