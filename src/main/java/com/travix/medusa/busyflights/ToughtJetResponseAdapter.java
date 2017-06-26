package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

public class ToughtJetResponseAdapter extends BusyFlightsResponse{

private ToughJetResponse response;

public ToughtJetResponseAdapter(final ToughJetResponse response){
this.response=response;
}

  public String getAirline() {
      return response.getCarrier();
  }


  public double getFare() {
      return response.getBasePrice();
  }


  public String getDepartureAirportCode() {
      return response.getDepartureAirportName();
  }

  public String getDestinationAirportCode() {
      return response.getArrivalAirportName();
  }


  public String getDepartureDate() {
      return response.getInboundDateTime();
  }


  public String getArrivalDate() {
      return response.getOutboundDateTime();
  }

  
  public String getSupplier() {
      return "ToughtJet";
  }


}
