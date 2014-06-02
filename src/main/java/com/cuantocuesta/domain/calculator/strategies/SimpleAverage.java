package com.cuantocuesta.domain.calculator.strategies;

import com.cuantocuesta.domain.meli.Listing;

public class SimpleAverage implements CalculationStrategy {
  @Override
  public Boolean isRelevant(Listing listing) {
    return true;
  }
}
