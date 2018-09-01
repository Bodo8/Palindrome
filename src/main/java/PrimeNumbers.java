import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

class PrimeNumbers {

  void findMaxPalindrome(Integer rangeMin, Integer rangeMax) {
    List<Integer> primeNumberList = generatePrimeNumberFromRange(rangeMin, rangeMax);
    TreeSet<Long> palindromes = new TreeSet<>();
    Map<Long, List<Long>> palindromesAndMultiples = new HashMap<>();
    int index = primeNumberList.size();
    for (int i = 1; i < index; i++) {
      Long primeNumberHigh = primeNumberList.get(index - i).longValue();
      for (int k = i; k <= index; k++) {
        Long primeNumberLow = primeNumberList.get(index - k).longValue();
        Long productOfTwoPrimeFiveDigitNumbers = primeNumberHigh * primeNumberLow;
        if (isPalindrome(productOfTwoPrimeFiveDigitNumbers)) {
          List<Long> multiples = new ArrayList<>();
          palindromes.add(productOfTwoPrimeFiveDigitNumbers);
          multiples.add(primeNumberHigh);
          multiples.add(primeNumberLow);
          palindromesAndMultiples.put(productOfTwoPrimeFiveDigitNumbers, multiples);
        }
      }
    }
    if (palindromes.isEmpty()) {
      System.out.println("There are no palindromes in the given range");
    } else {
      Long maxPalindrome = palindromes.last();
      System.out.println(maxPalindrome);
      System.out.println(palindromesAndMultiples.get(maxPalindrome));
    }
  }

  boolean isPalindrome2(Long productOfTwoPrimeFiveDigitNumbers) {
    long palindrome = productOfTwoPrimeFiveDigitNumbers;
    Long revers = 0L;
    while (palindrome != 0) {
      Long remainder = palindrome % 10;
      revers = revers * 10 + remainder;
      palindrome = palindrome / 10;
    }
    if (revers.equals(productOfTwoPrimeFiveDigitNumbers)) {
      return true;
    }
    return false;
  }

  boolean isPalindrome(Long productOfTwoPrimeFiveDigitNumbers) {
    Long palindrome = productOfTwoPrimeFiveDigitNumbers;
    String stringPalindrome = palindrome.toString();
    StringBuilder builder = new StringBuilder();
    String stringReverse = builder.append(palindrome).append("").reverse().toString();
    if (stringPalindrome.equals(stringReverse)) {
      return true;
    }
    return false;
  }

  public List<Integer> generatePrimeNumberFromRange(Integer rangeMin, Integer rangeMax) {
    Integer[] tab = new Integer[rangeMax];

    for (int i = 0; i < rangeMax; i++) {
      tab[i] = i;
    }

    for (int x = 2; x < rangeMax; x++) {
      int index = x;
      while (true) {
        index += x;
        if (index < rangeMax) {
          tab[index] = -1;
        } else {
          break;
        }
      }
    }
    return Arrays.stream(tab).filter(number -> number > 1)
        .filter(number -> number > rangeMin)
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    PrimeNumbers primeNumbers = new PrimeNumbers();
    primeNumbers.findMaxPalindrome(10000, 99999);
  }
}
