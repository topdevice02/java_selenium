import java.util.Arrays;

public class FizzBizz {

  public static void main(String[] args) {

    int[] arr = new int[100];
    String[] arrStr = new String[100];

    for (int i = 0; i < arr.length; i++) {
      arr[i] = i + 1;
      arrStr[i] = Integer.toString(arr[i]);
      if(arr[i] % 3 == 0)
      {
        if (arr[i] % 5 == 0)
        {
        arrStr[i] = "FillBuzz";
        }
        else arrStr[i] = "Fill";
      }
      else if (arr[i] % 5 == 0)
      {
        arrStr[i] = "Buzz";
      }

    }

    System.out.println(Arrays.toString(arrStr));
  }
}
