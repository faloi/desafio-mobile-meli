package com.cuantocuesta.domain.calculator.strategies;


import com.cuantocuesta.domain.meli.Listing;

public class AverageOfCategory implements CalculationStrategy {
  private final String category;

  public AverageOfCategory(String category) {
    this.category = category;
  }

  @Override
  public Boolean isRelevant(Listing listing) {
    return listing.categoryIs(category);
  }
}
