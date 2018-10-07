package packageNoOne;

import java.util.ArrayList;
import java.util.List;

import org.apache.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class objectClassLogin extends Baseclass {

	@FindBy(xpath="//img[@class ='logo img-responsive']")
	private WebElement pagedisplaysucess;
	
	@FindBy( id = "newsletter-input")
	private WebElement newsletter;
	
	@FindBy( xpath = "//button[@name ='submitNewsletter']")
	private WebElement newsletterokbutton;
	
	@FindBy(xpath = "//p[text() =' Newsletter : Invalid email address.']")
	private WebElement invalidmailidmsg;
	
	@FindBy(xpath = "//p[text() =' Newsletter : You have successfully subscribed to this newsletter.']")
	private WebElement sucessfullysubscribedmsg;
	
	@FindBy(xpath = "//p[text() =' Newsletter : This email address is already registered.']")
	private WebElement mailidallreadysubscribermsg;
	
	@FindBy(xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a")
	private WebElement dressestabinhome;
	
	@FindBy(xpath = "//ul[contains(@class,'submenu')]/li[3]/a")
	private WebElement summerdressestabhome;
	
	@FindBy(xpath = "//span[@class = 'cat-name']")
	private WebElement sucessfullynavigatedtosummerdresspage;
	
	@FindBys(@FindBy(xpath = "//ul[@class ='product_list grid row']/li"))
	private List<WebElement> products;
	
	@FindBy(xpath = "//ul[@class='product_list grid row']/li[1]")
	private WebElement firstproductafterassendingsort;
	
	@FindBy(id = "selectProductSort")
	private WebElement dropdowninsummerdresspage;
	
	@FindBys(@FindBy(xpath="//ul[@class='product_list grid row']//h5/a"))
	private List<WebElement> listofproductnameinsummerdressespageafterassending;
	
	@FindBys(@FindBy(xpath="//ul[@id='homefeatured']/li"))
	private List<WebElement> listofproductsinthehomepage;
	
	@FindBy(xpath="//p/br")
	private WebElement lodingimg;
	
	
	public objectClassLogin() {
		PageFactory.initElements(driver , this);
	}
	
	public WebElement PageDispalySucess(){
		return pagedisplaysucess;
	}
	
	public WebElement NewsLetter(){
		return newsletter;
	}

	public WebElement NewsLetterOkButton(){
		return newsletterokbutton;
	}
	
	public WebElement InvalidMailIdMsg(){
		return invalidmailidmsg;
	}
	
	public WebElement SucessfullySubscribedMsg(){
		return  sucessfullysubscribedmsg;
	}
	
	public WebElement MailIdAllreadySubscribedMsg(){
		return mailidallreadysubscribermsg;
	}
	
	public WebElement DressesTabHomePage(){
		return dressestabinhome;
	}
	
	public WebElement SummerDressesTabHomePage(){
		return summerdressestabhome;
	}
	
	public WebElement SucessfulluNavigatedToSummerDressesPage(){
		return sucessfullynavigatedtosummerdresspage;
	}
	
	public WebElement FirstProductAfterAssendingSort(){
		return firstproductafterassendingsort;
	}
	
	public List<WebElement> ProductList(){
		return products;
	}
	
	public WebElement DropDownInSummerDressesPage(){
		return dropdowninsummerdresspage;
	}
	
	public WebElement lodingImg(){
		return lodingimg;
	}
	
	public WebElement AddTocartTab(WebElement parent){
		return parent.findElement(By.xpath ("//*[text() ='Add to cart']"));
	}
	
	public WebElement ToCheckForAssendingOrderOfProduct(WebElement parent){
		return parent.findElement(By.xpath("//*[@itemprop ='name']/a"));
	}
	
	public List<WebElement> ListOfProductsNameInSummerDressesPageAfterAssending(){
		return listofproductnameinsummerdressespageafterassending;
	}
	
	public List<String> ConvertionOfWebElementToString(List<WebElement> elements){
		ArrayList<String> ListOfProductInDress = new ArrayList<String>();
		for(WebElement j: elements){
			ListOfProductInDress.add(ToGetText(j));
		}
		return ListOfProductInDress;
	}
	
	public WebElement ListOfDiscountProduct(WebElement parentelement){
		return parentelement.findElement(By.xpath("//div[@class = 'product-image-container']//span[@class='old-price product-price']"));
	}
	
	public WebElement ListOfDiscountPercntage(WebElement parentelement){
		return parentelement.findElement(By.xpath("//div[@class = 'product-image-container']//span[@class='price-percent-reduction']"));
	}
	
	public List<WebElement> ListOfProdustInTheHomePage(){
		return listofproductsinthehomepage;
	}
	
	public WebElement ProductNameInHomePage(WebElement parent){
		return parent.findElement(By.xpath("//ul[@id='homefeatured']/li//div/h5/a"));
	}
	
	public WebElement RateOfTheProductsInHomePage(WebElement parent){
		return parent.findElement(By.xpath("//ul[@id='homefeatured']/li//div[@class='right-block']/div[@itemprop='offers']/span[1]"));
	}
	
}
