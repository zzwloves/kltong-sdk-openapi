package com.githup.zzwloves.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * JSON 工具类
 *
 * @author zhuzw
 * @version <b>1.0.0</b>
 */
public final class JsonUtils {

    /**
     * 对json对象以ASCII码为依据进行字典排序
     *
     * @param json 待排序的json对象
     * @return 排序后的json对象
     */
    public static JSONObject sort(JSONObject json) {
        return dropObject(json);
    }

    private static JSONObject dropObject(JSONObject json) {
        String key;
        Object value;
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            // 判断value的类型是对象还是数组，然后依照value的类型进行递归调用
            if (value instanceof JSONArray) {
                JSONArray jsonArray = dropArray((JSONArray) value);
                json.put(key, jsonArray);
            }
            if (value instanceof JSONObject) {
                JSONObject jsonObject = dropObject((JSONObject) value);
                json.put(key, jsonObject);
            }
        }
        return (JSONObject) JSON.toJSON(new TreeMap<>(json.getInnerMap()));
    }

    private static JSONArray dropArray(JSONArray jsonArray) {
        // 数字、字符串在JSONArray中的下标集合
        List<Integer> indexList = null;
        Object next;
        for (int i = 0; i < jsonArray.size(); i++) {
            next = jsonArray.get(i);
            // 判断next的类型是对象还是数组，然后依照next的类型（排除数字、字符串）进行递归调用，数字、字符串直接进行排序
            if (next instanceof JSONObject) {
                JSONObject jsonObject = dropObject((JSONObject) next);
                jsonArray.remove(i);
                jsonArray.add(i, jsonObject);
            } else if (next instanceof JSONArray) {
                dropArray((JSONArray) next);
            } else {
                if (indexList == null) {
                    indexList = new ArrayList<>(jsonArray.size());
                }
                // 这样做是考虑到JSON数组中可能存在简单属性（数字、字符串）和复杂属性（JSON对象、JSON数组）共存的情况
                indexList.add(i);
            }
        }
        if (indexList != null) {
            List<Object> list = new ArrayList<>(indexList.size());
            List<Object> objectList = jsonArray.toJavaList(Object.class);
            if (indexList.size() == objectList.size()) {
                // 全部是简单属性（数字、字符串），直接将objectList全部填充到list中，并清空objectList
                list.addAll(objectList);
                objectList.clear();
            } else {
                // 偏移量
                int offset = 0;
                for (Integer index : indexList) {
                    Object o = objectList.remove(index - (offset++));
                    list.add(o);
                }
            }
            // 此时list中只有简单属性（数字、字符串），进行排序
            list.sort(Comparator.comparing(Object::toString));

            // 清空jsonArray，放入新内容
            jsonArray.clear();
            jsonArray.addAll(list);
            // 将复杂属性JSON对象、JSON数组直接添加到方法返回结果中
            if (!objectList.isEmpty()) {
                jsonArray.addAll(objectList);
            }
        }
        return jsonArray;
    }

}
