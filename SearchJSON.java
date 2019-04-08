package ticket.util;

import java.io.Serializable;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SearchJSON implements Serializable {
	public String from;
	public String to;
	public String date;
	public String method;//成人：ADULT 学生：0X00
	 
	 public ArrayList<String> result;
	 public HashMap<String, String> statMap;
	 
	 public SearchJSON(String from, String to, String date, String method) {
		this.from = from;
		this.to = to;
		this.date = date;
		this.method = method;
	}
	public static Object getKey(Map map, Object value){
	 	List<Object> keyList = new ArrayList<>();
	 	for(Object key: map.keySet()){
	 		if(map.get(key).equals(value)){
	 			keyList.add(key);
	 		}
	 	}
	 	return keyList;
	 }


	public void search() {
		String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" + date
				+ "&leftTicketDTO.from_station=" + from + "&leftTicketDTO.to_station=" + to + "&purpose_codes="
				+ method;
		// String url
		// ="https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2019-02-11&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH&purpose_codes=ADULT";
		System.out.println(url);
		String jsonStr = ConnGet.doGet(url);
		System.out.println(jsonStr);
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		JSONObject jsonDataObject = jsonObject.getJSONObject("data");
		JSONArray resultArray = jsonDataObject.getJSONArray("result");
		JSONObject stationMap = jsonDataObject.getJSONObject("map");
		result = new ArrayList<>();
		Iterator<Object> iterator = resultArray.iterator();
		while (iterator.hasNext()) {
			String re = (String) iterator.next();
			result.add(re);
		}
		
		statMap = new HashMap<>();
		 Iterator<String> it = stationMap.keySet().iterator(); 
		   while (it.hasNext()){ 
		    String key; 
		    key=(String)it.next(); 
		    statMap.put(key,stationMap.getString(key));
		   } 
	}

		

}
