package dev.saljuama.katas.refactor.gildedrose

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GildedRoseTest : FreeSpec({
    "The Gilded Rose Inn" - {

        "when updating the quality of the items" - {
            fun updateQuality(item: Item): Item {
                val inventory = GildedRose(arrayOf(item))
                inventory.updateQuality()
                return inventory.items.first()
            }

            "for regular items" - {
                "degrades sellIn and the quality values by 1" {
                    val updatedItem = updateQuality(Item("foo", 10, 10))
                    updatedItem.sellIn shouldBe 9
                    updatedItem.quality shouldBe 9
                }
                "never degrades quality below 0" {
                    val updatedItem = updateQuality(Item("foo", 0, 0))
                    updatedItem.quality shouldBe 0
                }
                "degrades the quality by 2 when the sellIn value is 0 or less" {
                    val updatedItem = updateQuality(Item("foo", 0, 10))
                    updatedItem.quality shouldBe 8
                }
            }

            "for items that age well" - {
                "increase their quality by 1" {
                    val updatedItem = updateQuality(Item("Aged Brie", 10, 10))
                    updatedItem.quality shouldBe 11
                }
                "quality never improves above 50" {
                    val updatedItem = updateQuality(Item("Aged Brie", 10, 50))
                    updatedItem.quality shouldBe 50
                }
            }

            "for backstage passes" - {
                "increase quality by 1 when sellIn is more than 10 days" {
                    val updatedItem = updateQuality(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10))
                    updatedItem.quality shouldBe 11
                }
                "increase quality by 2 when sellIn is 10 days or less" {
                    val updatedItem = updateQuality(Item("Backstage passes to a TAFKAL80ETC concert", 10, 10))
                    updatedItem.quality shouldBe 12
                }
                "increase quality by 3 when sellIn is 5 days or less" {
                    val updatedItem = updateQuality(Item("Backstage passes to a TAFKAL80ETC concert", 5, 10))
                    updatedItem.quality shouldBe 13
                }
                "drop the quality to 0 the day after the concert" {
                    val updatedItem = updateQuality(Item("Backstage passes to a TAFKAL80ETC concert", 0, 10))
                    updatedItem.quality shouldBe 0
                }
            }

            "for legendary items" - {
                "never change their sellIn or quality attributes" {
                    val updatedItem = updateQuality(Item("Sulfuras, Hand of Ragnaros", 10, 10))
                    updatedItem.sellIn shouldBe 10
                    updatedItem.quality shouldBe 10
                }
            }

            "for conjured items" - {
                "degrades sellIn by 1 and quality by 2" {
                    val updatedItem = updateQuality(Item("Conjured foo", 10, 10))
                    updatedItem.sellIn shouldBe 9
                    updatedItem.quality shouldBe 8
                }
                "degrades the quality by 4 when the sellIn is 0 or less" {
                    val updatedItem = updateQuality(Item("Conjured foo", 0, 10))
                    updatedItem.quality shouldBe 6
                }
                "never degrades quality below 0" {
                    val updatedItem = updateQuality(Item("Conjured foo", 0, 3))
                    updatedItem.quality shouldBe 0
                }
            }
        }
    }
})
