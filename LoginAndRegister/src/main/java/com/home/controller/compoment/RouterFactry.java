package com.home.controller.compoment;

import com.alibaba.fastjson.JSONObject;
import com.home.entity.Router;
import java.util.*;

public class RouterFactry {

    public static List<Object> RouterMapFactry(List<Router> routers){
        Map<String,Object> maps = new LinkedHashMap<>();
        List<Object> lists = new LinkedList<>();
        for (Router router : routers)
        {
            maps.put("name",router.getName());
            String INFO = router.getRoutertem();
            JSONObject json = JSONObject.parseObject(INFO);
            Map<String,Object> map1 = (Map<String,Object>)json;
            maps.putAll(map1);
            maps.put("children",ToJson(router));
            lists.add(maps);
        }
        return lists;
    }

    public static List<Map<String,Object>> ToJson(Router router) {
        List<Map<String,Object>> list = new LinkedList<>();
        for (Router Tem : router.getChildren()) {
            if (Tem.getChildren() != null && Tem.getChildren().size() != 0){
                for (Router routerp : Tem.getChildren()) {
                    if (routerp.getChildren() != null && routerp.getChildren().size() != 0) {
                        //如果该节点还有子节点，则递归
                        ToJson(Tem);
                        break;
                    }
                }
            }
            // StringEscapeUtils.unescapeJson(Tem.getRoutertem());
            //该节点没有子节点了,将该节点加入子节点队列
            Map<String,Object> map = new HashMap<>();
            map.put("name",Tem.getName());
            String INFO = Tem.getRoutertem();
            // StringEscapeUtils.unescapeJson(INFO);
            JSONObject json = JSONObject.parseObject(INFO);
            Map<String,Object> map1 = (Map<String,Object>)json;
            map.putAll(map1);
            list.add(map);
        }
        return list;
    }
}
