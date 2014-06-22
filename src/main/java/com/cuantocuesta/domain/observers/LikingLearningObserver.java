package com.cuantocuesta.domain.observers;

import com.cuantocuesta.domain.meli.Listing;

public interface LikingLearningObserver {
  void notifyListingsLikeThisWillBeExcluded(Listing listing);
}
