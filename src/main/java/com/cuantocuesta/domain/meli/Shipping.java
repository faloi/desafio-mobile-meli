package com.cuantocuesta.domain.meli;

public class Shipping {
  public boolean freeShipping;

  public Shipping() {
  }

  public Shipping(boolean isFree) {
    freeShipping = isFree;
  }

  public boolean isFree() {
    return freeShipping;
  }
}
