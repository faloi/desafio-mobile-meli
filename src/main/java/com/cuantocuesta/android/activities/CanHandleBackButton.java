package com.cuantocuesta.android.activities;

public interface CanHandleBackButton {
  /**
   * retornar false si se debe propagar el evento
   * o true si pudo consumirlo
   * @return
   */
  public boolean onBackPressed();
}
