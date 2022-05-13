package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleTest {

  List<String> names = Arrays.asList("Карты", "Счета", "Сбережения", "Кредиты");
  List<String> products = Arrays.asList("Пластиковая карта","МИР карта","Текущий счет","Накопительный счет","Пенсионный плюс",
                                  "Доходный","Комфортный", "Ипотечный кредит","Потребительский кредит");

  public void getCards(){
    List<String> cards = new ArrayList<>();

    for (int i = 0; i < products.size(); i++) {
      if(products.get(i).contains("карта")){
        cards.add(products.get(i));
      }
    }
    System.out.println(cards);
  }

  public void getAccounts(){
    List<String> accounts = new ArrayList<>();

    for (int i = 0; i < products.size(); i++) {
      if(products.get(i).equals("Текущий счет") || products.get(i).equals("Накопительный счет")){
        accounts.add(products.get(i));
      }
    }
    System.out.println(accounts);
  }

  public void getSavings(){
    List<String> savings = new ArrayList<>();

    for (int i = 0; i < products.size(); i++) {
      if(products.get(i).equals("Пенсионный плюс") || products.get(i).equals("Доходный") || products.get(i).equals("Комфортный")){
        savings.add(products.get(i));
      }
    }
    System.out.println(savings);
  }

  public void getCredits(){
    List<String> credits = new ArrayList<>();

    for (int i = 0; i < products.size(); i++) {
      if(products.get(i).equals("Ипотечный кредит") || products.get(i).equals("Потребительский кредит")){
        credits.add(products.get(i));
      }
    }
    System.out.println(credits);
  }

  @Test
  public void printNames(){
    for(String name : names){
      System.out.println(name);
    }
  }

  @Test
  public void printProducts(){
    products.forEach(product -> System.out.println(product));
  }

  @Test
  public void printNamesAndProducts(){
    for (int i = 0; i < names.size(); i++) {
      switch (i){
        case 0:
          System.out.println("==" + names.get(0) + "==");
          getCards();
          break;
        case 1:
          System.out.println("==" + names.get(1) + "==");
          getAccounts();
          break;
        case 2:
          System.out.println("==" + names.get(2) + "==");
          getSavings();
          break;
        case 3:
          System.out.println("==" + names.get(3) + "==");
          getCredits();
          break;

      }
    }
  }
}

