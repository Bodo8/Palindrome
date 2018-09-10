package palinrome;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MaxPalindrome {

  private final PrimeNumbersGenerator primeNumbersGenerator;
  private final MultipliersFromPrimeNumber multipliers;

  public MaxPalindrome(PrimeNumbersGenerator primeNumbersGenerator,
      MultipliersFromPrimeNumber multipliers) {
    this.primeNumbersGenerator = primeNumbersGenerator;
    this.multipliers = multipliers;
  }


  public Long findMaxPalindrome(Integer rangeMin, Integer rangeMax) throws Exception {
    if (rangeMin < 0 | rangeMax < 0 | rangeMax < rangeMin) {
      throw new Exception("Range minimum must will be 0, max 99999");
    }
    List<Integer> primeNumberList = primeNumbersGenerator
        .getPrimeNumbers(rangeMin, rangeMax);
    TreeSet<Long> palindromes = new TreeSet<>();
    Long maxPalindrome = 0L;
    int index = primeNumberList.size();
    for (int i = 1; i < index; i++) {
      Long primeNumberHigh = primeNumberList.get(index - i).longValue();
      for (int k = i; k <= index; k++) {
        List<Long> onePairMultipliers = new ArrayList<>();
        Long primeNumberLow = primeNumberList.get(index - k).longValue();
        Long productOfTwoPrimeFiveDigitNumbers = primeNumberHigh * primeNumberLow;
        if (isPalindrome(productOfTwoPrimeFiveDigitNumbers)) {
          palindromes.add(productOfTwoPrimeFiveDigitNumbers);
          onePairMultipliers.add(primeNumberHigh);
          onePairMultipliers.add(primeNumberLow);
          multipliers.addPairToMap(productOfTwoPrimeFiveDigitNumbers, onePairMultipliers);
        }
      }
    }
    if (palindromes.isEmpty()) {
      throw new Exception("There are no palindromes in the given range");
    } else {
      maxPalindrome = palindromes.last();
    }
    return maxPalindrome;
  }

  public List<Long> getMultipliersForMaxPalindrome(Long maxPalindrome) {
    return multipliers.getOnePairPalindromeAndMultipliers(maxPalindrome);
  }

  public List<Integer> getPrimeNumberList(Integer rangeMin, Integer rangeMax) {
    return primeNumbersGenerator.getPrimeNumbers(rangeMin, rangeMax);
  }

  private boolean isPalindrome(Long productOfTwoPrimeFiveDigitNumbers) {
    String stringPalindrome = productOfTwoPrimeFiveDigitNumbers.toString();
    StringBuilder builder = new StringBuilder();
    String stringReverse = builder.append(productOfTwoPrimeFiveDigitNumbers).append("").reverse()
        .toString();
    if (stringPalindrome.equals(stringReverse)) {
      return true;
    }
    return false;
  }
}
