package packageNoTwo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import packageNoOne.Baseclass;
import packageNoOne.objectClassLogin;

public class TestClassLogin {
	Baseclass bc;
	objectClassLogin obln;

	public TestClassLogin() {
		bc = new Baseclass();
		obln = new objectClassLogin();
	}

	@Test
	public void TestToChecHomePageLaunchedSucessfully() {
		Assert.assertTrue(bc.elementFound(obln.PageDispalySucess()));
	}

	@Test
	public void TestToCheckNewsLetterFungtion() {
		String Mail = bc.RandomeEmailId(5000);
		Assert.assertTrue(bc.elementFound(obln.NewsLetter()));
		bc.Toclick(obln.NewsLetterOkButton());
		bc.ExplicitWait(obln.InvalidMailIdMsg());
		Assert.assertTrue(bc.elementFound(obln.InvalidMailIdMsg()));
		bc.Toclick(obln.NewsLetter());
		bc.ToSendKeys(obln.NewsLetter(), Mail);
		bc.Toclick(obln.NewsLetterOkButton());
		bc.ExplicitWait(obln.SucessfullySubscribedMsg());
		Assert.assertTrue(bc.elementFound(obln.SucessfullySubscribedMsg()));
		bc.Toclick(obln.NewsLetter());
		bc.ToSendKeys(obln.NewsLetter(), Mail);
		bc.Toclick(obln.NewsLetterOkButton());
		bc.ExplicitWait(obln.MailIdAllreadySubscribedMsg());
		Assert.assertTrue(bc.elementFound(obln.MailIdAllreadySubscribedMsg()));
	}

	@Test
	public void TestToPerformMouseHoverAndNavigateToSummerDressPage() {
		bc.MouseHower(obln.DressesTabHomePage());
		bc.ExplicitWait(obln.SummerDressesTabHomePage());
		bc.Toclick(obln.SummerDressesTabHomePage());
		Assert.assertTrue(bc.elementFound(obln.SucessfulluNavigatedToSummerDressesPage()));
	}

	@Test
	public void TestToCheckIfAllTheProductHasAddToCartButton() {
		for (WebElement e : obln.ProductList()) {
			bc.MouseHower(e);
			Assert.assertTrue(bc.elementFound(obln.AddTocartTab(e)));
		}

	}

	@Test
	public void TestToCheckIfTheProductsAreInAssendingOrder() throws InterruptedException {
		bc.MouseHower(obln.DressesTabHomePage());
		bc.ExplicitWait(obln.SummerDressesTabHomePage());
		bc.Toclick(obln.SummerDressesTabHomePage());
		bc.DropDown(obln.DropDownInSummerDressesPage(), "Product Name: A to Z");
		Thread.sleep(1000);
		//bc.ExplicitWait(obln.lodingImg());
		//bc.ExplicitWaitForInvisibility(obln.lodingImg());
		for (String jr : obln.ConvertionOfWebElementToString(obln.ListOfProductsNameInSummerDressesPageAfterAssending())) {
			System.out.println(jr);
			Assert.assertTrue(jr.compareTo(jr + 1) <= 0);
		}
	}
	@Test
	public void TestToCheckIfDiscountIsDisplayedForRedusedProducts(){
		bc.MouseHower(obln.DressesTabHomePage());
		bc.ExplicitWait(obln.SummerDressesTabHomePage());
		bc.Toclick(obln.SummerDressesTabHomePage());
		for(WebElement f : obln.ProductList()){
			bc.MouseHower(f);
			if(bc.elementFound(obln.ListOfDiscountProduct(f))){
				Assert.assertTrue(bc.elementFound(obln.ListOfDiscountPercntage(f)));
			}
		}
	}
	
	@Test
	public void TestToReadTheProductAndWriteTheRateinXl() throws IOException{
		for(WebElement k : obln.ListOfProdustInTheHomePage()){
			bc.MouseHower(k);
			System.out.println(obln.ToReadFromTheXl(1, 0));
			System.out.println(bc.ToGetText(obln.ProductNameInHomePage(k)));
			System.out.println(bc.ToGetText(obln.RateOfTheProductsInHomePage(k)));
			if(bc.ToGetText(obln.ProductNameInHomePage(k)).equalsIgnoreCase(obln.ToReadFromTheXl(1, 0))){
				
				Assert.assertTrue(obln.ToWriteTheTextToTheXl(1, 1, bc.ToGetText(obln.RateOfTheProductsInHomePage(k))));
			}
		}
	}
	
	

}
