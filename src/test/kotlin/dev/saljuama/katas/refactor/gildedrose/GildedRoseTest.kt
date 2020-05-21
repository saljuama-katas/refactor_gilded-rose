package dev.saljuama.katas.refactor.gildedrose

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GildedRoseTest : FreeSpec({

    "The Gilded Rose Inn" - {

        "updating the quality of the items" - {

            "for regular items" - {
                "degrades sellIn and the quality values by 1" {
                    val inventory = GildedRose(arrayOf(Item("foo", 10, 10)))
                    inventory.updateQuality()
                    inventory.items.first().sellIn shouldBe 9
                    inventory.items.first().quality shouldBe 9

                }
                "never degrades quality below 0" {
                    val inventory = GildedRose(arrayOf(Item("foo", 0, 0)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 0
                }
                "degrades the quality by 2 when the sellIn value is 0 or less" {
                    val inventory = GildedRose(arrayOf(Item("foo", 0, 10)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 8
                }
            }

            "for items that age well" - {
                "increase their quality over time" {
                    val inventory = GildedRose(arrayOf(Item("Aged Brie", 10, 10)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 11
                }
                "quality never goes above 50" {
                    val inventory = GildedRose(arrayOf(Item("Aged Brie", 10, 50)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 50
                }
            }

            "for backstage passes" - {
                "increase quality by 1 when sellIn is more than 10 days" {
                    val inventory = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 11
                }
                "increase quality by 2 when sellIn is 10 days or less" {
                    val inventory = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 12
                }
                "increase quality by 3 when sellIn is 5 days or less" {
                    val inventory = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 13
                }
                "drop the quality to 0 the day after the concert" {
                    val inventory = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)))
                    inventory.updateQuality()
                    inventory.items.first().quality shouldBe 0
                }
            }

            "for legendary items" - {
                "never change their sellIn or quality attributes" {
                    val inventory = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", 10, 10)))
                    inventory.updateQuality()
                    inventory.items.first().sellIn shouldBe 10
                    inventory.items.first().quality shouldBe 10
                }
            }

        }

    }

})

