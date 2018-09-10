package palinrome;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@RunWith(JUnitParamsRunner.class)
public class MaxPalindromeTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  MaxPalindrome maxPalindrome;

  @Before
  public void setUp() throws Exception {
    maxPalindrome = new MaxPalindrome(new PrimeNumbersGenerator(),
        new MultipliersFromPrimeNumber());
  }

  @Test
  public void shouldExceptionRangeInFindMaxPalindrome() throws Exception {
    exception.expect(Exception.class);
    exception.expectMessage("Range minimum must will be 0, max 99999");
    maxPalindrome.findMaxPalindrome(-2, 5);
  }

  @Test
  public void shouldExceptionNoPalindrome() throws Exception {
    exception.expect(Exception.class);
    exception.expectMessage("There are no palindromes in the given range");
    maxPalindrome.findMaxPalindrome(0, 1);

  }

  @Test
  public void shouldFindMaxPalindrome999949999() throws Exception {
    //given
    Long expectedPalindrome = 999949999L;
    Long expectedMultiplier1 = 33211L;
    Long expectedMultiplier2 = 30109L;

    //when
    Long actualPalindrome = maxPalindrome.findMaxPalindrome(10000, 99999);
    List<Long> actualMultipliers = maxPalindrome.getMultipliersForMaxPalindrome(actualPalindrome);
    Long actualMultiplier1 = actualMultipliers.get(0);
    Long actualMultiplier2 = actualMultipliers.get(1);

    //than
    assertEquals(expectedPalindrome, actualPalindrome);
    assertEquals(expectedMultiplier1, actualMultiplier1);
    assertEquals(expectedMultiplier2, actualMultiplier2);
  }

  @Test
  @Parameters(method = "parameters16to90")
  public void generatePrimeNumberFromRangeTest16to90(List<Integer> expected) throws Exception {
    //given

    //when
    List<Integer> given = maxPalindrome.getPrimeNumberList(16, 90);

    //than
    assertThat(given, is(expected));
  }

  @Test
  @Parameters(method = "parameters")
  public void generatePrimeNumberFromRangeTest(List<Integer> expected) throws Exception {
    //given

    //when
    List<Integer> given = maxPalindrome.getPrimeNumberList(0, 104730);

    //than
    assertThat(given, is(expected));
  }

  @Test
  @Parameters(method = "parameters10000to99999")
  public void generatePrimeNumberFromRangeTest10000to99999(List<Integer> expected)
      throws Exception {
    //given

    //when
    List<Integer> given = maxPalindrome.getPrimeNumberList(10000, 99999);

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