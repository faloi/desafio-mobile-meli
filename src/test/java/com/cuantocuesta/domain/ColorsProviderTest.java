package com.cuantocuesta.domain;

import com.cuantocuesta.domain.meli.Attribute;
import com.cuantocuesta.domain.meli.AttributeValue;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColorsProviderTest {

  @Test
  public void Can_make_colors_list_from_category_attributes() {
    List<NamedColor> colors = new ColorsProvider().getColorsFromAttributes(Arrays.asList(
      new Attribute("season"),
      new Attribute("color",
        AttributeValue.color("Vermelho", "#FF0000"),
        AttributeValue.color("Rosa", "#F4CCCC")
      ),
      new Attribute("color",
        AttributeValue.color("Vermelho", "#FF0000"),
        AttributeValue.color("Rosa", "#F4CCCC")
      )
    ));

    assertEquals(
      Arrays.asList(new NamedColor("Vermelho", "#FF0000"), new NamedColor("Rosa", "#F4CCCC")),
      colors
    );
  }
}