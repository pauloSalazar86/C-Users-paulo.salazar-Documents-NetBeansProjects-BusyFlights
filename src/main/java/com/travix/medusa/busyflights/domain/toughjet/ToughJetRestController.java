package com.travix.medusa.busyflights.domain.toughjet;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/toughController/")
public class ToughJetRestController {
	




@RequestMapping(method=RequestMethod.GET)
public @ResponseBody List<ToughJetResponse> getFlights(ToughJetRequest request) {
    //adding for testing
    List<ToughJetResponse> result = new ArrayList<>();  
    ToughJetResponse response_1;
    ToughJetResponse response_2;
    response_1 = new ToughJetResponse();
    response_1.setArrivalAirportName("CUB");
    response_1.setBasePrice(320);
    response_1.setCarrier("RYANAIR");
    response_1.setDepartureAirportName("MAD");
    response_1.setDiscount(10);
    response_1.setInboundDateTime("10-10-2017");
    response_1.setOutboundDateTime("10-10-2018");
    response_1.setTax(0);	
    response_2 = new ToughJetResponse();
    response_2.setArrivalAirportName("BOG");
    response_2.setBasePrice(620);
    response_2.setCarrier("BRITISH");
    response_2.setDepartureAirportName("PAR");
    response_2.setDiscount(0);
    response_2.setInboundDateTime("10-10-2037");
    response_2.setOutboundDateTime("10-10-2088");
    response_2.setTax(1000);
    result.add(response_1);
    result.add(response_2);
    //finishing adding for testing
    return filterData(request,result);
}

 private List<ToughJetResponse> filterData(ToughJetRequest data,List<ToughJetResponse> result){
       List<ToughJetResponse> elementsToRemove = new ArrayList<>();
        for(ToughJetResponse element : result){       
            if(data.getFrom()!=null && !data.getFrom().equals(element.getDepartureAirportName())||
                    data.getTo()!=null && !data.getTo().equals(element.getArrivalAirportName())||
                    data.getInboundDate()!=null && !data.getInboundDate().equals(element.getInboundDateTime()) ||
                    data.getOutboundDate()!=null && !data.getOutboundDate().equals(element.getOutboundDateTime())  )
                elementsToRemove.add(element);
            }
        result.removeAll(elementsToRemove);
        return result;
   }
    

   
   
    
}
