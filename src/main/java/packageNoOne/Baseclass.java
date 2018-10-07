package packageNoOne;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Baseclass {
	public static WebDriver driver;

	public Baseclass() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "D:\\tharique testing\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://automationpractice.com/index.php");
			driver.manage().window().maximize();
			driver.navigate().refresh();
		}
	}

	public boolean elementFound(WebElement element) {
		boolean res = false;
		try {
			res = element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String RandomeEmailId(int a) {
		int value = new Random().nextInt(a);
		String emailid = "Tharique" + value + "@gmail.com";
		return emailid;
	}

	public void Toclick(WebElement element) {
		element.click();
	}

	public void ToSendKeys(WebElement element, String key) {
		element.sendKeys(key);
	}

	public void ExplicitWait(WebElement element) {
		WebDriverWait wb = new WebDriverWait(driver, 15);
		wb.until(ExpectedConditions.visibilityOf(element));
	}

	public void ExplicitWaitForInvisibility(WebElement element) {
		WebDriverWait wb = new WebDriverWait(driver, 15);
		wb.until(ExpectedConditions.invisibilityOf(element));
	}

	public void MouseHower(WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element).build().perform();
	}

	public void NavigateToUrl(String URL) {
		driver.navigate().to(URL);
	}

	public void DropDown(WebElement element, String text) {
		Select sc = new Select(element);
		sc.selectByVisibleText(text);
		;
	}

	public String ToGetText(WebElement element) {
		return element.getText();

	}

	public String ToReadFromTheXl(int r, int c) throws IOException {
		File FileLocation = new File("D:\\tharique testing\\data\\datainput.xlsx");
		FileInputStream stream = new FileInputStream(FileLocation);
		Workbook wb = new XSSFWorkbook(stream);
		Sheet sh = wb.getSheet("Sheet2");
		Row rowvalue = null;
		Cell cellvalue = null;
		try {
			if (r <= sh.getPhysicalNumberOfRows()) {
				rowvalue = sh.getRow(r);
				try {
					if (c <= rowvalue.getPhysicalNumberOfCells()) {
						cellvalue = rowvalue.getCell(c);
					}
				} catch (Exception a) {
					System.out.println("Enetr a valid cell/ column value");
				}
			}
		} catch (Exception e) {
			System.out.println("Enter the correct row value");
		}
		return cellvalue.getStringCellValue();
	}

	public boolean ToWriteTheTextToTheXl(int r, int c, String retr) throws IOException {
		File filelocation = new File("D:\\tharique testing\\data\\datainput.xlsx");
		FileInputStream stream = new FileInputStream(filelocation);
		Workbook wb = new XSSFWorkbook(stream);
		Sheet sh = wb.getSheet("Sheet2");
		Row rowvalue = sh.getRow(r);
		boolean res = false;
		if (rowvalue == null) {
			sh.createRow(r);
		}
		Cell cellvalue = sh.getRow(r).getCell(c);
		if (cellvalue == null) {
			sh.getRow(r).createCell(c).setCellValue(retr);
			res = true;
		} else {
			sh.getRow(r).getCell(c).setCellValue(retr);
			res = true;
		}
		FileOutputStream outputvalue = new FileOutputStream("D:\\tharique testing\\data\\datainput.xlsx");
		wb.write(outputvalue);
		outputvalue.close();
		return res;

	}

	public List<HashMap<String, String>> readValueFromExcelSheet() {
		List<HashMap<String, String>>mapDatasList = new ArrayList<HashMap<String, String>>();
		try {
			File excelLocaltion = new File("D:\\tharique testing\\data\\datainput.xlsx");
			String sheetName = "Sheet2";
			FileInputStream f = new FileInputStream(excelLocaltion.getAbsolutePath());
			Workbook w = new XSSFWorkbook(f);
			Sheet sheet = w.getSheet(sheetName);
			Row headerRow = sheet.getRow(0);
for (int i = 0; i<sheet.getPhysicalNumberOfRows(); i++) {
	Row currentRow = sheet.getRow(i);
HashMap<String, String>mapDatas = new HashMap<String, String>();
for (int j = 0; j<headerRow.getPhysicalNumberOfCells(); j++) {
	Cell currentCell = currentRow.getCell(j);
	try {
	switch (currentCell.getCellType()) {
     case Cell.CELL_TYPE_STRING:
							mapDatas.put(headerRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							mapDatas.put(headerRow.getCell(j).getStringCellValue(),
									String.valueOf(currentCell.getNumericCellValue()));
							break;
						}
					} catch (NullPointerException e) {
						mapDatas.put(headerRow.getCell(j).getStringCellValue(), null);
					}

				}
				mapDatasList.add(mapDatas);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return mapDatasList;
	}
	
	public void writeValueExcelSheet(String header, String data, int index) throws Exception {

		File excelLocaltion = new File("D:\\tharique testing\\data\\datainput.xlsx");
		String sheetName = "Sheet2";
		FileInputStream f = new FileInputStream(excelLocaltion.getAbsolutePath());
		Workbook w = new XSSFWorkbook(f);
		Sheet sheet = w.getSheet(sheetName);
		Row headerRow = sheet.getRow(0);
		for (int j = 0; j<headerRow.getPhysicalNumberOfCells(); j++) {
			String headerValue = headerRow.getCell(j).getStringCellValue();
			if (headerValue.equals(header)) {
				Cell currentCell = sheet.getRow(index).getCell(j);
				if (currentCell == null) {
					sheet.getRow(index).createCell(j).setCellValue(data);
				} else {
					sheet.getRow(index).getCell(j).setCellValue(data);
				}
				currentCell.setCellValue(data);
			}
		}

		FileOutputStream out = new FileOutputStream(excelLocaltion);
		w.write(out);
		out.close();

	}


}
