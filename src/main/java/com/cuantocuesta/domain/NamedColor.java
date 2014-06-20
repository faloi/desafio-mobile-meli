package com.cuantocuesta.domain;

public class NamedColor {
  public String name;
  public String color;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NamedColor that = (NamedColor) o;

    if (!color.equals(that.color)) return false;
    if (!name.equals(that.name)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + color.hashCode();
    return result;
  }

  public NamedColor(String name, String color) {
    this.name = name;
    this.color = color;
  }
}
