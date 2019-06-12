package com.qz.jwttoken.utils;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象转换成map
 */
public class ObjectToMapUtil {
  private static Logger logger = LoggerFactory.getLogger(ObjectToMapUtil.class);

    @SneakyThrows
    public static Map<String,Object> object2Map(Object obj){

        if (obj == null) {
            logger.info("对象为空,无法转换成Map!");
            return null;
        }

        Map<String,Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
