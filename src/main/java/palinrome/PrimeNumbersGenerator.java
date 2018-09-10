package palinrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class PrimeNumbersGenerator {

  private List<Integer> primeNumbers;

  private void generatePrimeNumbersFromRange(Integer rangeMin, Integer rangeMax) {
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
    primeNumbers = Arrays.stream(tab).filter(number -> number > 1)
        .filter(number -> number > rangeMin)
        .collect(Collectors.toList());
  }

  public List<Integer> getPrimeNumbers(Integer rangeMin, Integer rangeMax) {
    primeNumbers = new ArrayList<>();
    generatePrimeNumbersFromRange(rangeMin, rangeMax);
    return primeNumbers;
  }
}
