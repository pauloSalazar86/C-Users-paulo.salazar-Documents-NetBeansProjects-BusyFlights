package com.travix.medusa.busyflights.domain.crazyair;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/crazyAir/")
public class CrazyRestController {
    
   @RequestMapping(method=RequestMethod.GET)
   public @ResponseBody List<CrazyAirResponse> getFlights(CrazyAirRequest crazyFilter) {
       //adding for test
        List<CrazyAirResponse> result = new ArrayList<>();   
        CrazyAirResponse response_1;
        CrazyAirResponse response_2;
        response_1 = new CrazyAirResponse();
        response_1.setPrice(20);
        response_1.setAirline("IBERIA");
        response_1.setArrivalDate("05-02-2018");
        response_1.setCabinclass("B");
        response_1.setDepartureAirportCode("LHR");
        response_1.setDepartureDate("01-02-2017");
        response_1.setDestinationAirportCode("MAD");
        response_2 = new CrazyAirResponse();
        response_2.setPrice(20);
        response_2.setAirline("BA");
        response_2.setArrivalDate("06-02-2018");
        response_2.setCabinclass("E");
        response_2.setDepartureAirportCode("MAD");
        response_2.setDepartureDate("11-02-2018");
        response_2.setDestinationAirportCode("LIM");
       result.add(response_1);
       result.add(response_2);
       //finish adding for test
       return filterData(crazyFilter,result) ;
   }
   
   private List<CrazyAirResponse> filterData(CrazyAirRequest data,List<CrazyAirResponse> result){
       List<CrazyAirResponse> elementsToRemove = new ArrayList<>();
        for(CrazyAirResponse element : result){       
            if(data.getDepartureDate()!=null && !data.getDepartureDate().equals(element.getDepartureDate())||
                    data.getDestination()!=null && !data.getDestination().equals(element.getDestinationAirportCode())||
                    data.getOrigin()!=null && !data.getOrigin().equals(element.getDepartureAirportCode()) ||
                    data.getReturnDate()!=null && !data.getReturnDate().equals(element.getArrivalDate()))
                elementsToRemove.add(element);
            }
        result.removeAll(elementsToRemove);
        return result;
   }
}
