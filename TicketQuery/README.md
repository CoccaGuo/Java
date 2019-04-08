
打开12306查询界面，右键查看元素，转到网络部分。为了方便看清查询过程，我们清空现有的记录。
![](https://img-blog.csdnimg.cn/20190213194414101.JPG)
 查询后可以看到如下信息：![](https://img-blog.csdnimg.cn/2019021319461542.JPG)
 发现是通过get方法请求的数据，返回类型为json数据，点开后可以看到请求的地址：https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2019-02-14&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH&purpose_codes=ADULT
 返回的文件为：
 ![](https://img-blog.csdnimg.cn/20190213195012508.JPG)
 可以看到车票信息就在返回的json中。
 
同时发现map中列出的车站和城市是用三个字母表示的，如北京：BJP，上海：SHH等，如果想在客户端中直接查询城市的话还要知道城市和车站的代码。通过这个网址可以得到：
https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9091
因为数据比较乱，我整理之后放到了HashMap里面序列化保存到本地，并在程序启动时读取。
这样就可以模拟get请求获取数据了。

 关键代码如下（这里用到了fastjson 可以直接百度的到）：
 

```java
import java.io.Serializable;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SearchJSON implements Serializable {
	public String from;//三个字母的代码
	public String to;//代码
	public String date;//整理成yyyy-MM-dd的形式
	public String method;//成人：ADULT 学生：0X00
	 
	 public ArrayList<String> result;//用于存放列车的信息
	 public HashMap<String, String> statMap;//用于存放车站信息
	 //init
	 public SearchJSON(String from, String to, String date, String method) {
		this.from = from;
		this.to = to;
		this.date = date;
		this.method = method;
	}
	//一个通过value查key的方法
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
		//System.out.println(url);
		String jsonStr = ConnGet.doGet(url);
		//System.out.println(jsonStr);
		//使用fastjson
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


```
整理获得的ArrayList并打印出来：

```java
public static String[][] parseResult(ArrayList<String> result){
        String[] temp_list[];
        try {
             temp_list = new String[result.size()][];
        }catch (Exception e){
            System.out.println("err in parsing json");
            temp_list =null;
        }
    	System.out.println("车次\t出发\t到达\t历时\t一等\t二等\t软卧\t硬卧\t硬座\t无座\t高级软卧");
    	System.out.println("--------------------------------------------------------------------------------------");
        for (int i = 0; i <result.size() ; i++){
            temp_list[i] = result.get(i).split("\\|");
            System.out.print(temp_list[i][3]+"\t");// 车次 出发时间 到达时间 历时 一等 二等 软卧 硬卧 硬座 无座 高级软卧 tmp_list[3],tmp_list[8],tmp_list[9],tmp_list[10],tmp_list[31],tmp_list[30],tmp_list[23],tmp_list[28],tmp_list[29],tmp_list[26],tmp_list[21] 
            System.out.print(temp_list[i][8]+"\t");
            System.out.print(temp_list[i][9]+"\t");
            System.out.print(temp_list[i][10]+"\t");
            System.out.print(temp_list[i][31]+"\t");
            System.out.print(temp_list[i][30]+"\t");
            System.out.print(temp_list[i][23]+"\t");
            System.out.print(temp_list[i][28]+"\t");
            System.out.print(temp_list[i][29]+"\t");
            System.out.print(temp_list[i][26]+"\t");
            System.out.print(temp_list[i][21]+"\t");
            System.out.println();
            
        }
        return temp_list;
    }
```
至此，查询的功能部分已经完成。
剩下的只剩用户界面设计。
