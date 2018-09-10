package palinrome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MultipliersFromPrimeNumber {

  private final Map<Long, List<Long>> pairPalindromeAndMultipliers = new HashMap<>();

  void addPairToMap(Long palindrome, List<Long> listMultipliers) {
    pairPalindromeAndMultipliers.put(palindrome, listMultipliers);
  }

  List<Long> getOnePairPalindromeAndMultipliers(Long palindrome) {
    return pairPalindromeAndMultipliers.get(palindrome);
  }
}
