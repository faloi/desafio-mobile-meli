package com.cuantocuesta.domain;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.Attribute;
import com.cuantocuesta.domain.meli.AttributeValue;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

public class ColorsProvider {
  private List<NamedColor> availableColors;

  private static ColorsProvider instance;

  public static ColorsProvider getInstance() {
    if (instance == null)
      instance = new ColorsProvider();

    return instance;
  }

  public void loadColors(SpiceManager spiceManager, String clothingCategoryExample) {
    spiceManager.execute(getRequest(clothingCategoryExample), getListener());
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

  private RetrofitSpiceRequest<Attribute.List, Meli> getRequest(final String clothingCategoryExample) {
    return new RetrofitSpiceRequest<Attribute.List, Meli>(Attribute.List.class, Meli.class) {
      @Override
      public Attribute.List loadDataFromNetwork() throws Exception {
        return getService().getAttributesOfCategory(clothingCategoryExample);
      }
    };
  }

  public RequestListener<Attribute.List> getListener() {
    return new RequestListener<Attribute.List>() {
      @Override
      public void onRequestFailure(SpiceException spiceException) {

      }

      @Override
      public void onRequestSuccess(Attribute.List attributes) {
        setAvailableColors(getColorsFromAttributes(attributes));
      }
    };
  }

  public void setAvailableColors(List<NamedColor> availableColors) {
    this.availableColors = availableColors;
  }

  public List<NamedColor> getAvailableColors() {
    return availableColors;
  }
}
