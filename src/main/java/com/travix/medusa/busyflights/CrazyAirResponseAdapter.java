package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

public class CrazyAirResponseAdapter extends BusyFlightsResponse{

private CrazyAirResponse response;

public CrazyAirResponseAdapter(final CrazyAirResponse response){
this.response=response;
}

  public String getAirline() {
      return response.getAirline();
  }


  public String getSupplier() {
      return "CrazyAir";
  }

  public double getFare() {
      return response.getPrice();
  }


  public String getDepartureAirportCode() {
      return response.getDepartureAirportCode();
  }


  public String getDestinationAirportCode() {
     return response.getDestinationAirportCode();
  }


  public String getDepartureDate() {
      return response.getDepartureDate();
  }


  public String getArrivalDate() {
     return response.getArrivalDate();
  }
  



}


