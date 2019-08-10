package com.whetherapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@GetMapping("/Whether")
	public List<Whether> getWhether(@RequestParam("id") String id,@RequestParam("appid") String ppid) {
		
		Map temparatureHigMap = new HashMap<String, Double>();
		Map temparatureLowMap = new HashMap<String, Double>();
		Map rainsMap = new HashMap<String, String>();
		Map applyLotionMap = new HashMap<String, String>();
		Map day = new HashMap<String, String>();
		
		List<Whether> whetherList = new ArrayList<Whether>();
		
		/*
		 * ResponseEntity<CurrencyConversionBean> responseEntity = new
		 * RestTemplate().getForEntity(
		 * "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
		 * CurrencyConversionBean.class, uriVariables);
		 * 
		 * CurrencyConversionBean response = responseEntity.getBody();
		 */
		
		//?id=524901&appid=b6907d289e10d714a6e88b30761fae22
		
		
		Map<String, String> uriVariables = new HashMap<>();
		
		ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
				"https://samples.openweathermap.org/data/2.5/forecast?id="+id+"&appid="+ppid, String.class,
				uriVariables);

		
		String response = responseEntity.getBody();
		
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject)parser.parse(response);
			
			//JSONArray array = (JSONArray) jsonObject.get("list");
			
			JSONArray msg = (JSONArray) jsonObject.get("list");
			
			
            Iterator<JSONObject> iterator = msg.iterator();
            
            while (iterator.hasNext()) {
            	
            	System.out.println("fddfgf");
            	JSONObject jsonObjectDay  = ((JSONObject)iterator.next());
            	
            	System.out.println("fddfgf"  + jsonObjectDay);
            	
            	Double temp_min = (Double ) ((JSONObject)jsonObjectDay.get("main")).get("temp_min") ;
            	Double temp_max = (Double )  ((JSONObject)jsonObjectDay.get("main")).get("temp_max") ;
				String weather_rain = (String) jsonObjectDay.get("weather_rain");
				String dt_txt = (String) jsonObjectDay.get("dt_txt");
				dt_txt = (String)dt_txt.split(" ")[0];
				
				day.put(dt_txt, new Object());
				
				if(temparatureHigMap.containsKey(dt_txt)) {
					
					if((Double)temparatureHigMap.get(dt_txt) < temp_max) {
						temparatureHigMap.put(dt_txt, temp_max);
						if(temp_max > 200) {
							applyLotionMap.put(dt_txt, "Apply Lotion");
						}
					}
						
				} else {
					temparatureHigMap.put(dt_txt, temp_max);
					if(temp_max > 200) {
						applyLotionMap.put(dt_txt, "Apply Lotion");
					}
				}
				
				if(temparatureLowMap.containsKey(temp_min)) {
					
					if((Double)temparatureLowMap.get(dt_txt) < temp_min) {
						temparatureLowMap.put(dt_txt, temp_min);
					}
						
				} else {
					temparatureLowMap.put(dt_txt, temp_min);
				
				}

				if(weather_rain != null) {
					
					applyLotionMap.put(dt_txt, "Rains Today Bring Jacket");
					
					
				}
				System.out.println(jsonObjectDay.get("dt_txt"));
            	
				
            }
        	            
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<String> keySet = day.keySet();
		
		for(String key : keySet) {
			//	public Whether(Double temp_min, Double temp_max, String weather_rain, String date, String carryUnbrella) {
			Whether whethertmp = new Whether((Double)temparatureHigMap.get(key),
					
					(Double)temparatureLowMap.get(key),
					(String)rainsMap.get(key),
					key,
					(String)applyLotionMap.get(key));
	        
			whetherList.add(whethertmp);
		}
		
		
		return whetherList;
	}
}
