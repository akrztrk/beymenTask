package org.beymentest;

import org.beymentest.base.BaseTest;
import org.beymentest.page.MainPage;
import org.junit.*;

public class AppTest extends BaseTest
{
    MainPage mainPage;

    @Before
    public void before() {
        mainPage = new MainPage(getWebDriver());
    }


    @After
    public void after() {
        tearDown();
    }
}
