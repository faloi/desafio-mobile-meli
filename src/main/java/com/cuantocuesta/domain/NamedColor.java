package com.cuantocuesta.domain;

public class NamedColor {
  public String name;
  public String rgb;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NamedColor that = (NamedColor) o;

    if (!rgb.equals(that.rgb)) return false;
    if (!name.equals(that.name)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + rgb.hashCode();
    return result;
  }

  public NamedColor(String name, String rgb) {
    this.name = name;
    this.rgb = rgb;
  }

  public String getName() {
    return name;
  }

  public String getRgb() {
    return rgb;
  }
}
