package com.cuantocuesta.domain.meli;

import com.cuantocuesta.android.applicationModels.Displayable;
import com.cuantocuesta.domain.NamedColor;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

import java.util.*;

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
    this.addVariations(Arrays.asList(variations));
  }

  public List<Variation> getVariations() {
    return variations;
  }

  public Set<CombinatedColor> getColors(final List<NamedColor> availableColors) {
    Iterable<CombinatedColor> colors = Iterables.transform(getVariations(), new Function<Variation, CombinatedColor>() {
      @Override
      public CombinatedColor apply(Variation input) {
        return input.getCombinatedColor(Listing.this);
      }
    });

    return ImmutableSet.copyOf(Iterables.transform(ImmutableSet.copyOf(colors), new Function<CombinatedColor, CombinatedColor>() {
      @Override
      public CombinatedColor apply(final CombinatedColor color) {
        return color.getRgbFrom(availableColors);
      }
    }));
  }

  public void addVariations(Collection<Variation> variations) {
    this.getVariations().addAll(variations);
  }

  public Set<String> getSizes() {
    ImmutableSet<AttributeCombination> attributeCombinations = ImmutableSet.copyOf(Iterables.transform(getVariations(), new Function<Variation, AttributeCombination>() {
      @Override
      public AttributeCombination apply(Variation input) {
        return input.getSizeAttribute();
      }
    }));

    Iterable<String> orderedSizes = Iterables.transform(Ordering.natural().sortedCopy(attributeCombinations), new Function<AttributeCombination, String>() {
      @Override
      public String apply(AttributeCombination input) {
        return input.getValueName();
      }
    });

    return ImmutableSet.copyOf(orderedSizes);
  }

  public void addPictures(Picture... pictures) {
    this.pictures.addAll(Arrays.asList(pictures));
  }

  public String getPictureById(final String id) {
    return Iterables.find(getPictures(), new Predicate<Picture>() {
      @Override
      public boolean apply(Picture input) {
        return input.getId().equals(id);
      }
    }).getUrl();
  }
}

