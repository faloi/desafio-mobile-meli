package com.cuantocuesta.domain;

import com.cuantocuesta.domain.meli.Attribute;
import com.cuantocuesta.domain.meli.AttributeValue;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

public class ColorsProvider {
  private String clothingCategoryExample;

  public ColorsProvider(String clothingCategoryExample) {
    this.clothingCategoryExample = clothingCategoryExample;
  }

  public List<NamedColor> getColorsFromAttributes(List<Attribute> attributes) {
    Attribute colorAttribute = Iterables.find(attributes, new Predicate<Attribute>() {
      @Override
      public boolean apply(Attribute input) {
        return input.isColor();
      }
    });

    return Lists.newArrayList(Iterables.transform(colorAttribute.getValues(), new Function<AttributeValue, NamedColor>() {
      @Override
      public NamedColor apply(AttributeValue input) {
        return new NamedColor(input.getName(), input.getRgb());
      }
    }));
  }
}
