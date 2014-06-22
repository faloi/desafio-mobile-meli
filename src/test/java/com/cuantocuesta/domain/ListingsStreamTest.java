package com.cuantocuesta.domain;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.meli.ResultContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ListingsStreamTest {

  private Meli service;

  @Before
  public void setUp() {
    service = mock(Meli.class);
    when(service.searchByCategory(anyString(), anyString(), anyInt(), anyInt())).thenReturn(new ResultContainer());
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
    ListingsStream stream = createStream("MLA109276");

    stream.getMoreListings();
    verify(service).searchByCategory("MLA", "MLA109276", 0, ListingsStream.LIMIT);

    stream.getMoreListings();
    verify(service).searchByCategory("MLA", "MLA109276", ListingsStream.LIMIT, ListingsStream.LIMIT);
  }

  @Test
  public void Should_get_the_listings_details_using_items_multiget() throws Exception {
    when(service.searchByCategory("MLA", "MLA109276", 0, ListingsStream.LIMIT)).thenReturn(
      new ResultContainer(
        Arrays.asList(new Listing("MLA1111"), new Listing("MLA2222"))
      )
    );

    ListingsStream stream = createStream("MLA109276");

    stream.getMoreListings();
    verify(service).getListingsDetails("MLA1111,MLA2222");
  }

  @Test
  public void Should_return_listings_with_pictures() throws Exception {
    setupListingsResponse("MLA109276", new Listing("MLA1111"));

    Listing listingWithDetails = new Listing("MLA1111");
    listingWithDetails.addPictures("1.jpeg", "2.jpeg");

    when(service.getListingsDetails("MLA1111")).thenReturn(
      Arrays.asList(listingWithDetails)
    );

    ListingsStream stream = createStream("MLA109276");

    assertEquals(listingWithDetails, stream.getMoreListings().get(0));
  }

  @Test
  public void Should_exclude_disliked_categories() {
    Listing calzasListing = new Listing("MLA1112", "calzas");
    List<Listing> listings = Arrays.asList(calzasListing, new Listing("MLA1111", "shorts"));

    when(service.searchByCategory(eq("MLA"), eq("MLA1234"), anyInt(), anyInt())).thenReturn(
      new ResultContainer(listings)
    );

    setupListingDetailsResponse(calzasListing);

    ListingsStream stream = createStream("MLA1234");

    for (int i = 0; i <= ListingsStream.DISLIKE_FACTOR; i++)
      stream.registerDislike(new Listing("MLA1111", "shorts"));

    assertEquals(Arrays.asList(calzasListing), stream.getMoreListings());
  }

  private void setupListingsResponse(String category, Listing... listings) {
    when(service.searchByCategory("MLA", category, 0, ListingsStream.LIMIT)).thenReturn(
      new ResultContainer(Arrays.asList(listings))
    );
  }

  private void setupListingDetailsResponse(Listing listing) {
    when(service.getListingsDetails(listing.getId())).thenReturn(Arrays.asList(listing));
  }

  private ListingsStream createStream(String... categories) {
    return new ListingsStream(
      service,
      "MLA",
      Arrays.asList(categories)
    );
  }
}
