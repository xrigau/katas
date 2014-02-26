import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class GildedRoseTest {

	@Test
	public void testThatSulfurasSellInAndQualityDoNotDecrease() {
        int expectedQuality = 25;
        int expectedSellIn = 20;
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", expectedSellIn, expectedQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(expectedQuality));
        assertThat(sulfuras.getSellIn(), is(expectedSellIn));

	}

    @Test
    public void testThatAgedBrieIncreasesInQualityAndDecreasesSellIn() {
        int initialQuality = 25;
        int initialSellIn = 20;
        Item sulfuras = new Item("Aged Brie", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(initialQuality + 1));
        assertThat(sulfuras.getSellIn(), is(initialSellIn - 1));

    }

    @Test
    public void testThatAgedBrieKeepsIncreasingQualityAfterExpired() {
        int initialQuality = 25;
        int initialSellIn = 0;
        Item sulfuras = new Item("Aged Brie", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(initialQuality + 1));
        assertThat(sulfuras.getSellIn(), is(initialSellIn - 1));

    }

    @Test
    public void testThatAngedBrieQualityDoesNotGoOver50() {
        int initialQuality = 49;
        Item sulfuras = new Item("Aged Brie", 20, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();
        sut.updateQuality();

        int maxQuality = 50;
        assertThat(sulfuras.getQuality(), is(maxQuality));

    }

    @Test
    public void testThatDexterityVestDecreasesQualityAndSellIn() {
        assertThatDefaultProductValuesDecreaseOnEachUpdate("+5 Dexterity Vest");
        assertThatDefaultProductValuesDecreaseOnEachUpdate("Elixir of the Mongoose");
    }

    private void assertThatDefaultProductValuesDecreaseOnEachUpdate(String name) {
        int initialQuality = 20;
        int initialSellIn = 25;
        Item sulfuras = new Item(name, initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(initialQuality - 1));
        assertThat(sulfuras.getSellIn(), is(initialSellIn - 1));
    }

    @Test
    public void testThatAnItemsQualityDoesNotGoLowerThan0() {
        assertThatQualityDoesNotGoLowerThan0("+5 Dexterity Vest");
        assertThatQualityDoesNotGoLowerThan0("Elixir of the Mongoose");

    }

    private void assertThatQualityDoesNotGoLowerThan0(String name) {
        int initialQuality = 1;
        Item sulfuras = new Item(name, 20, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();
        sut.updateQuality();

        int minQuality = 0;
        assertThat(sulfuras.getQuality(), is(minQuality));
    }

    @Test
    public void testThatBackstagePassesQualityIncreasesWhenSellInGreaterThan10() {
        int initialQuality = 1;
        int initialSellIn = 20;
        Item sulfuras = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(initialQuality + 1));
    }

    @Test
    public void testThatBackstagePassesQualityIncreasesBy2WhenSellInBetween6And10() {
        int initialQuality = 1;
        int initialSellIn = 10;
        Item sulfuras = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(initialQuality + 2));
    }

    @Test
    public void testThatBackstagePassesQualityIncreasesBy3WhenSellInLessThan6() {
        int initialQuality = 1;
        int initialSellIn = 5;
        Item sulfuras = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(initialQuality + 3));
    }


    @Test
    public void testThatBackstagePassesDropsQualityTo0WhenSellInIs0() {
        int initialQuality = 10;
        int initialSellIn = 0;
        Item sulfuras = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        int worthlessProductQuality = 0;
        assertThat(sulfuras.getQuality(), is(worthlessProductQuality));
    }

    @Test
    public void testThatMultipleStandardProductsDecreaseQualityAndSellIn() {
        int initialQuality = 20;
        int initialSellIn = 25;
        Item dexerity = new Item("+5 Dexterity Vest", initialSellIn, initialQuality);
        Item elixir = new Item("Elixir of the Mongoose", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(dexerity, elixir);

        sut.updateQuality();

        assertThat(dexerity.getQuality(), is(initialQuality - 1));
        assertThat(elixir.getSellIn(), is(initialSellIn - 1));
    }

    @Test
    public void testThatConjuredManaDecreasesQualityTwiceAsFast() {
        int initialQuality = 10;
        int initialSellIn = 20;
        Item sulfuras = new Item("Conjured Mana Cake", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(sulfuras);

        sut.updateQuality();

        assertThat(sulfuras.getQuality(), is(initialQuality - 2));
    }

    @Test
    public void testThatWhenExpiredQualityIsDecreasedNormally() {
        int initialQuality = 10;
        int initialSellIn = -1;
        Item dexerity = new Item("+5 Dexterity Vest", initialSellIn, initialQuality);
        GildedRose sut = new GildedRose(dexerity);

        sut.updateQuality();

        assertThat(dexerity.getQuality(), is(initialQuality - 1));
    }

}
