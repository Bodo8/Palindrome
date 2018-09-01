import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@RunWith(JUnitParamsRunner.class)
public class PrimeNumbersTest {


  @Test
  public void isPalindrome() throws Exception {
  }

  @Test
  public void isPalindrome2() throws Exception {
  }

  @Test
  @Parameters(method = "parameters16to90")
  public void generatePrimeNumberFromRangeTest16to90(List<Integer> expected) throws Exception {
    //given

    //when
    List<Integer> given = new PrimeNumbers()

        .generatePrimeNumberFromRange(16, 90);

    //than
    assertThat(given, is(expected));
  }

  @Test
  @Parameters(method = "parameters")
  public void generatePrimeNumberFromRangeTest(List<Integer> expected) throws Exception {
    //given

    //when
    List<Integer> given = new PrimeNumbers()
        .generatePrimeNumberFromRange(0, 104730);

    //than
    assertThat(given, is(expected));
  }

  @Test
  @Parameters(method = "parameters10000to99999")
  public void generatePrimeNumberFromRangeTest10000to99999(List<Integer> expected)
      throws Exception {
    //given

    //when
    List<Integer> given = new PrimeNumbers()
        .generatePrimeNumberFromRange(10000, 99999);

    //than
    assertThat(given, is(expected));
  }

  private Object parameters16to90() throws FileNotFoundException {
    return new Object[]{
        new Object[]{
            Arrays.asList(17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89)}
    };
  }

  private Object parameters10000to99999() throws FileNotFoundException {
    List<Integer> allPrimeNumbers = get_10000_PrimeNumbers();
    List<Integer> listWithFilter = allPrimeNumbers.stream()
        .filter(number -> number > 10000)
        .filter(number -> number < 99999)
        .collect(Collectors.toList());
    return new Object[]{
        new Object[]{listWithFilter}
    };
  }

  private Object parameters() throws FileNotFoundException {
    return new Object[]{
        new Object[]{get_10000_PrimeNumbers()}
    };
  }


  private List<Integer> get_10000_PrimeNumbers() throws FileNotFoundException {
    List<Integer> fileData = new ArrayList<>();
    try (Scanner scan = new Scanner(new File("src/main/resources/primeNumberList.txt"))) {

      while (scan.hasNextInt()) {
        fileData.add(scan.nextInt());
      }
    }
    return fileData;
  }

}