package com.cuantocuesta.domain;

import com.cuantocuesta.domain.calculator.MarketPriceCalculator;
import com.cuantocuesta.domain.calculator.strategies.AverageOfCategory;
import com.cuantocuesta.domain.calculator.strategies.CalculationStrategy;
import com.cuantocuesta.domain.calculator.strategies.SimpleAverage;
import com.cuantocuesta.domain.meli.Listing;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class MarketPriceCalculatorTest {

  @Test
  public void should_be_able_to_get_market_price_with_a_simple_average() {
    Double price = getPrice(
      new SimpleAverage(),
      Arrays.asList(new Listing(20.0), new Listing(30.0))
    );

    assertEquals(25.0, price, 0.1);
  }

  @Test
  public void should_be_able_to_get_market_price_ignoring_unimportant_categories() {
    Double price = getPrice(
      new AverageOfCategory("MLA1111"),
      Arrays.asList(new Listing(10.0, "MLA255"), new Listing(100.0, "MLA1111"), new Listing(50.0, "MLA1111"))
    );

    assertEquals(75.0, price, 0.1);
  }

  private Double getPrice(CalculationStrategy strategy, Iterable<Listing> listings) {
    return new MarketPriceCalculator(strategy).getValueFrom(listings);
  }

}
