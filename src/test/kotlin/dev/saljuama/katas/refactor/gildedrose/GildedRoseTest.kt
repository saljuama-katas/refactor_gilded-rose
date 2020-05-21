package dev.saljuama.katas.refactor.gildedrose

import io.kotest.core.spec.style.WordSpec
import org.junit.jupiter.api.Assertions.assertEquals

class GildedRoseTest : WordSpec({

    "Guilded Rose" should {
        "foo" {
            val items = arrayOf<Item>(Item("foo", 0, 0))
            val app = GildedRose(items)
            app.updateQuality()
            assertEquals("fixme", app.items[0].name)
        }
    }

})
