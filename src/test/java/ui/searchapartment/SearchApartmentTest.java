package ui.searchapartment;

import org.testng.annotations.Test;
import ui.base.BaseTest;

import static constants.Constant.Urls.CIAN_HOMEPAGE;

public class SearchApartmentTest extends BaseTest {

  @Test
  //Проверяем произошел ли переход на страницу листинга
  public void checkIsRedirectToListing(){
    basePage.open(CIAN_HOMEPAGE);

    cianHomePagePage
            .enterCountRooms()
            .clickToFind();

    cianListingPage.checkCountCards();
  }
}
