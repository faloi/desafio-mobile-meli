package com.cuantocuesta.domain;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.Example;
import com.cuantocuesta.domain.meli.Listing;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

  @Test
  public void Should_get_the_listings_details_using_items_multiget() throws Exception {
    when(service.searchByCategory("MLA", "MLA109276", 0, ListingsStream.LIMIT)).thenReturn(
      new Example(
        Arrays.asList(new Listing("MLA1111"), new Listing("MLA2222"))
      )
    );

    ListingsStream stream = new ListingsStream(
      service,
      "MLA",
      Arrays.asList("MLA109276")
    );

    stream.getMoreListings();
    verify(service).getListingsDetails("MLA1111,MLA2222");
  }

  @Test
  public void Should_return_listings_with_pictures() throws Exception {
    when(service.searchByCategory("MLA", "MLA109276", 0, ListingsStream.LIMIT)).thenReturn(
      new Example(
        Arrays.asList(new Listing("MLA1111"))
      )
    );

    Listing listingWithDetails = new Listing("MLA1111");
    listingWithDetails.addPictures("1.jpeg", "2.jpeg");

    when(service.getListingsDetails("MLA1111")).thenReturn(
      Arrays.asList(listingWithDetails)
    );

    ListingsStream stream = new ListingsStream(
      service,
      "MLA",
      Arrays.asList("MLA109276")
    );

    assertEquals(listingWithDetails, stream.getMoreListings().get(0));
  }

}
