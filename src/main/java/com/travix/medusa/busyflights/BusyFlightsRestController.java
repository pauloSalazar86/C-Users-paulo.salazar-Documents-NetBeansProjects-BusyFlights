package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyRestController;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRestController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class BusyFlightsRestController {
    
    @Autowired
    CrazyRestController crazyController;    
    @Autowired
    ToughJetRestController toughController;
       
   @RequestMapping(method=RequestMethod.GET)
   public @ResponseBody List<BusyFlightsResponse> getVuelos(@RequestParam(value="origin", required=false) String origin,
                                                            @RequestParam(value="destination", required=false) String destination,
                                                            @RequestParam(value="departureDate", required=false) String departureDate,
                                                            @RequestParam(value="returnDate", required=false) String returnDate,
                                                            @RequestParam(value="numberOfPassengers", required=false) String numberOfPassengers) {
       List<BusyFlightsResponse> result = new ArrayList<>();

       CrazyAirRequest filterCrazy = new CrazyAirRequest();
       filterCrazy.setOrigin(origin);
       filterCrazy.setDestination(destination);
       filterCrazy.setDepartureDate(departureDate);
       filterCrazy.setReturnDate(returnDate);
       if(numberOfPassengers!=null)
        filterCrazy.setPassengerCount(Integer.parseInt(numberOfPassengers));
       
       List <CrazyAirResponse> listCrazy = crazyController.getFlights(filterCrazy);
       for(CrazyAirResponse element : listCrazy){       
           int i=0;
           if (result.isEmpty()){
               result.add(new CrazyAirResponseAdapter(element));
           }else{    
                while (element.getPrice()>result.get(i).getFare() && i++<result.size()-1);        
                result.add(i, new CrazyAirResponseAdapter(element));
           }
       }
       
       ToughJetRequest filterToug = new ToughJetRequest();
       filterToug.setFrom(origin);
       filterToug.setInboundDate(returnDate);
       if(numberOfPassengers!=null)
        filterToug.setNumberOfAdults(Integer.parseInt(numberOfPassengers));
       filterToug.setOutboundDate(departureDate);
       filterToug.setTo(destination);
       
       List<ToughJetResponse> listTough = toughController.getFlights(filterToug);
       for (ToughJetResponse  element : listTough ){
           int i=0;
           if (result.isEmpty()){
               result.add(new ToughtJetResponseAdapter(element));
           }else{   
            while (element.getBasePrice()+element.getTax()>result.get(i).getFare() && i++<result.size()-1);
            result.add(i, new ToughtJetResponseAdapter(element));
           }         
       }
       
       
       return result;
   }
    
}
