import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class PrimeNumbers {

  void findMaxPalindrome(int rangeMin, int rangeMax) {
    List<Integer> primeNumberList = generatePrimeNumberFromRange(rangeMin, rangeMax);
    TreeSet<Long> palindromes = new TreeSet<>();
    Map<Long, List<Long>> palindromesAndMultiples = new HashMap<>();
    int index = primeNumberList.size();
    for (int i = 1; i < index; i++) {
      Long primeNumberHigh = primeNumberList.get(index - i).longValue();
      for (int k = i; k <= index; k++) {
        Long primeNumberLow = primeNumberList.get(index - k).longValue();
        Long productOfTwoPrimeFiveDigitNumbers = primeNumberHigh * primeNumberLow;
        if (isPalindrome2(productOfTwoPrimeFiveDigitNumbers)) {
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

  boolean isPalindrome(Long productOfTwoPrimeFiveDigitNumbers) {
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

  boolean isPalindrome2(Long productOfTwoPrimeFiveDigitNumbers) {
    Long palindrome = productOfTwoPrimeFiveDigitNumbers;
    String stringPalindrome = palindrome.toString();
    StringBuilder builder = new StringBuilder();
    String stringReverse = builder.append(palindrome).append("").reverse().toString();
    if (stringPalindrome.equals(stringReverse)) {
      return true;
    }
    return false;
  }

  /**
   * The author of the presented algorithm is Chinese computer scientist Xuedong Luo In - odd index,
   * Ip - even index primeNumber[In] → 3*In + 2 primeNumber[Ip] → 3*Ip + 1 minus multiple 11.
   *
   * @param rangeMin - you choice rage (min 0).
   * @param rangeMax - you choice rage (max 5 digit).
   * @return list with prime number
   */
  List<Integer> generatePrimeNumberFromRange(int rangeMin, int rangeMax) {

    if (rangeMin < 0) {
      throw new RuntimeException("Number must be 0 or higher");
    }
    List<Integer> primeNumberList = new ArrayList();
    int minPrimeIndex = rangeMin / 3;
    int maxPrimeIndex = (rangeMax - 1) / 3;

    for (int i = minPrimeIndex; i <= maxPrimeIndex; i++) {
      int primeNumber;
      if (i < 1) {
        primeNumber = 2;
        primeNumberList.add(primeNumber);
      } else {
        if (i % 2 == 0) {
          primeNumber = (i * 3) + 1;
          if (primeNumber % 11 > 0) {
            primeNumberList.add(primeNumber);
          }
        } else {
          primeNumber = (i * 3) + 2;
          if (primeNumber == 11) {
            primeNumberList.add(primeNumber);
          }
          if (primeNumber % 11 > 0) {
            primeNumberList.add(primeNumber);
          }
        }
      }
    }
    return primeNumberList;
  }

  public static void main(String[] args) {
    PrimeNumbers primeNumbers = new PrimeNumbers();
    primeNumbers.findMaxPalindrome(10000, 99999);
    // System.out.println(primeNumbers.generatePrimeNumberFromRange(0, 150));

  }
}
