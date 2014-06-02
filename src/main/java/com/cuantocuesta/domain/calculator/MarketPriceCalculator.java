package com.cuantocuesta.domain.calculator;

import com.cuantocuesta.domain.calculator.strategies.CalculationStrategy;
import com.cuantocuesta.domain.meli.Listing;

public class MarketPriceCalculator {
  private final CalculationStrategy strategy;

  public MarketPriceCalculator(CalculationStrategy strategy) {
    this.strategy = strategy;
  }

  public Double getValueFrom(Iterable<Listing> listings)  {
    return relevantListings(listings).map(_.price).avg;
  }

  private Iterable<Listing> relevantListings(Iterable<Listing> listings) {
    return listings.filter(strategy.isRelevant);
  }
}


