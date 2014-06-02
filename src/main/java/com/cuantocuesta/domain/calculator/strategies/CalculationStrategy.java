package com.cuantocuesta.domain.calculator.strategies;

import com.cuantocuesta.domain.meli.Listing;

public interface CalculationStrategy {
  Boolean isRelevant(Listing listing);
}
