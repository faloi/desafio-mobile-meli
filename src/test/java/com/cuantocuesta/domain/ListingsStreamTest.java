package com.cuantocuesta.domain;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.ListingsStream;
import com.cuantocuesta.domain.meli.Example;
import com.cuantocuesta.domain.meli.Listing;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class ListingsStreamTest {

  private Meli service;

  @Before
  public void setUp() {
    service = mock(Meli.class);
    when(service.searchByCategory(anyString(), anyString(), anyInt(), anyInt())).thenReturn(new Example());
  }

  @Test
  public void getMoreListings_gets_LIMIT_listings_of_each_category() throws Exception {
    ListingsStream stream = new ListingsStream(
      service,
      "MLA",
      Arrays.asList("MLA109276", "MLA109085")
    );

    stream.getMoreListings();

    verify(service).searchByCategory("MLA", "MLA109276", 0, ListingsStream.LIMIT);
    verify(service).searchByCategory("MLA", "MLA109085", 0, ListingsStream.LIMIT);
  }

  @Test
  public void When_called_more_than_one_time_getMoreListings_remembers_the_offset() throws Exception {
    ListingsStream stream = new ListingsStream(
      service,
      "MLA",
      Arrays.asList("MLA109276")
    );

    stream.getMoreListings();
    verify(service).searchByCategory("MLA", "MLA109276", 0, ListingsStream.LIMIT);

    stream.getMoreListings();
    verify(service).searchByCategory("MLA", "MLA109276", ListingsStream.LIMIT, ListingsStream.LIMIT);
  }

}
