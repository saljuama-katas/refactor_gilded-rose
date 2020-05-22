package dev.saljuama.katas.refactor.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items = items
            .map { detectItemType(it) }
            .map { it.updateQuality() }
            .toTypedArray()
    }

}
