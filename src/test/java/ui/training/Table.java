package ui.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
  private WebElement tableElement;
  private WebDriver driver;

  public Table(WebElement tableElement, WebDriver driver) {
    this.tableElement = tableElement;
    this.driver = driver;
  }

  //Получить строки таблица
  public List<WebElement> getRows(){
    List<WebElement> rows = tableElement.findElements(By.xpath(".//tr"));
    rows.remove(0);//удаляем 1 строку с заголовками
    return rows;
  }

  //Получить заголовки
  public List<WebElement> getHeadings(){
    WebElement headings = tableElement.findElement(By.xpath(".//tr[1]"));//получаем строку с заголовками
    List<WebElement> headingColumns = headings.findElements(By.xpath(".//th"));//находим заголовки
    return headingColumns;
  }

  //получить все строки таблицы с колонками
  public List<List<WebElement>> getRowsWithColumns(){
    List<WebElement> rows = getRows();//получаем все строки
    List<List<WebElement>> rowsWithColumns = new ArrayList<>();//создаем список строк с списками колонок
    for(WebElement row : rows){
      List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));//получаем колонки в одной строке
      rowsWithColumns.add(rowWithColumns);
    }
    return rowsWithColumns;
  }

  //Получить значение ячейки таблицы
  public String getValueFromCell(int rowNumber, int columnNumber){
    List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
    WebElement cell = rowsWithColumns.get(rowNumber-1).get(columnNumber-1);
    return cell.getText();
  }

  /**
   * получить все строки таблицы с колонками по заголовку
   * возвращаем список мапов, где String это заголовок, конкретная ячейка котороя будет возвращаться по заголовку
   */

  public List<Map<String, WebElement>> getRowsWithColumnsByHeading(){
    List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
    List<Map<String, WebElement>> rowsWithColumnsByHeading = new ArrayList<>();
    Map<String, WebElement> rowByHeadings; //1 строка разбитая на столбцы
    List<WebElement> headingsColumns = getHeadings();

    for(List<WebElement> row : rowsWithColumns){//пробегаемся по всем строкам
      rowByHeadings = new HashMap<String, WebElement>();//создаем новый хешмап для разбитой строки
      for (int i = 0; i < headingsColumns.size(); i++) {//перебираем все колонки каждой строки
        String heading = headingsColumns.get(i).getText();//привязываем заголовок к конкретной колонке
        WebElement cell = row.get(i);//получаем ячейку из строки под таким же номером
        rowByHeadings.put(heading, cell);//помещаем в мапу ячейку и заголовок
      }
      rowsWithColumnsByHeading.add(rowByHeadings);
    }
    return rowsWithColumnsByHeading;
  }

  public String getValueFromCell(int rowNumber, String columnName){
    List<Map<String, WebElement>> rowsWithColumnsByHeading = getRowsWithColumnsByHeading();
    return rowsWithColumnsByHeading.get(rowNumber-1).get(columnName).getText();
  }
}
