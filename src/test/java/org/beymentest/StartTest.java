package org.beymentest;

import org.junit.*;

public class StartTest extends AppTest {

    public static String PRODUCT = "Pantolon";

    @Test
    public void mainTest() throws InterruptedException {
        mainPage.anaSayfaKontrol().
                hesabımKontrol().
                favorilerimKontrol().
                sepetimKontrol().
                urunArama(PRODUCT).
                sayfaSonuScroll().
                beklemeSuresi(2).
                rastgeleUrunSec().
                rastgeleBedenSec().
                beklemeSuresi(2).
                sepeteEkle().
                fiyatKarsılastır().
                urunArtır().
                beklemeSuresi(2).
                urunSil();
    }
}
