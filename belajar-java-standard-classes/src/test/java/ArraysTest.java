import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArraysTest {

  @Test
  void create() {

    int[] randomArray = {3, 6, 9, 12, 15, 18, 21, 2, 4, 6, 8, 10, 12, 14};

    System.out.println("Default : " + Arrays.toString(randomArray));
    Arrays.sort(randomArray);
    System.out.println("Sorted  : " + Arrays.toString(randomArray));

    // binary search
    int findAtIndex = Arrays.binarySearch(randomArray, 10);
    Assertions.assertEquals(7, findAtIndex);


  }
}
