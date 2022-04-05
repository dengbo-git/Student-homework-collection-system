package fun.china1.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

//json 数据解析工具类
public class JsonUtils {
    //一般情况下工具类中方法都为static修饰的

    public static void getJsonElementForKey(String str, String searchKey, HashMap map){
        JSONObject jsonObject = JSON.parseObject(str);
        //2.循环遍历当前的jsonObject对象
         final BigDecimal C=new BigDecimal(70);
        //1.将str转换成JSONObject 对象
        for(String key:jsonObject.keySet()){
            //3.判断当前key是否为我们需要查询的key值
            if(key.equals(searchKey)){
                BigDecimal b=(BigDecimal)jsonObject.get(searchKey);
                if(b.compareTo(C)>=0){
                map.put("Score",jsonObject.get(searchKey));
                map.put("PersonId",jsonObject.get("PersonId"));
                }
            }else if(jsonObject.get(key) instanceof JSONObject){
                getJsonElementForKey(String.valueOf(jsonObject.get(key)),searchKey,map);
            }else if(jsonObject.get(key) instanceof JSONArray){
                JSONArray jsonArray = (JSONArray) jsonObject.get(key);// [{},[],"","",22,true]
                for(int i = 0 ; i < jsonArray.size();i++){
                    //如果当前对象属于数组类型或者对象类型，再调用递归方法，如果不属于,就不进行其他操作
                    if(jsonArray.get(i) instanceof JSONArray || jsonArray.get(i) instanceof JSONObject){
                        getJsonElementForKey(String.valueOf(jsonArray.get(i)),searchKey,map);
                    }
                }
            }
        }
    }
}
