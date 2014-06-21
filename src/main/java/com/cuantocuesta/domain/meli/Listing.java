package com.cuantocuesta.domain.meli;

import com.cuantocuesta.android.applicationModels.Displayable;
import com.cuantocuesta.domain.NamedColor;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;

public class Listing implements Displayable {
  public String id;
  public String title;
  public java.lang.Double price;
  public List<Picture> pictures;
  public Shipping shipping;
  public Long officialStoreId;
  public List<Variation> variations;

  public Listing() {
    this.pictures = new ArrayList<Picture>();
    this.variations = new ArrayList<Variation>();
  }

  public Listing(String id) {
    this();
    this.id = id;
  }

  public String getPermalink() {
    return permalink;
  }

  public String permalink;

  public List<Picture> getPictures() {
    return pictures;
  }

  @Override
  public String getThumbnail() {
    return getPictures().get(0).url;
  }

  @Override
  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Double getPrice() {
    return price;
  }

  public void addPictures(String... pictures) {
    for (String p : pictures) {
      this.getPictures().add(new Picture(p));
    }
  }

  public boolean hasFreeShipping() {
    return this.shipping.isFree();
  }

  public boolean isFromOfficialStore() {
    return this.officialStoreId != null;
  }

  public void addVariations(Variation... variations) {
    for (Variation v : variations) {
      this.getVariations().add(v);
    }
  }

  public List<Variation> getVariations() {
    return variations;
  }

  public List<CombinatedColor> getColorsRgbs(final List<NamedColor> availableColors) {
    Iterable<CombinatedColor> colors = Iterables.transform(getVariations(), new Function<Variation, CombinatedColor>() {
      @Override
      public CombinatedColor apply(Variation input) {
        return input.getCombinatedColor();
      }
    });

    return Lists.newArrayList(Iterables.transform(Sets.newHashSet(colors), new Function<CombinatedColor, CombinatedColor>() {
      @Override
      public CombinatedColor apply(final CombinatedColor color) {
        return color.getRgbFrom(availableColors);
      }
    }));
  }

}

