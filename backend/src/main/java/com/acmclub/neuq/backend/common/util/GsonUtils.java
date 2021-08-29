package com.acmclub.neuq.backend.common.util;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public final class GsonUtils {
    private GsonUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonUtils.class);
    public static final String EMPTY = "";
    /**
     * 空的 {@code JSON} 数据 - <code>"{}"</code>。
     */
    public static final String EMPTY_JSON = "{}";
    /**
     * 空的 {@code JSON} 数组(集合)数据 - {@code "[]"}。
     */
    public static final String EMPTY_JSON_ARRAY = "[]";
    /**
     * 默认的 {@code JSON} ⽇期/时间字段的格式化模式。
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";
    /**
     * {@code Google Gson} 的 {@literal @Since} 注解常⽤的版本号常量 - {@code 1.0}。
     */
    public static final Double SINCE_VERSION_10 = 1.0d;
    /**
     * {@code Google Gson} 的 {@literal @Since} 注解常⽤的版本号常量 - {@code 1.1}。
     */
    public static final Double SINCE_VERSION_11 = 1.1d;
    /**
     * {@code Google Gson} 的 {@literal @Since} 注解常⽤的版本号常量 - {@code 1.2}。
     */
    public static final Double SINCE_VERSION_12 = 1.2d;

    /**
     * 将给定的⽬标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
     * <p/>
     * <strong>该⽅法转换发⽣错误时，不会抛出任何异常。若发⽣错误时，曾通对象返回 <code>"{}"</
     * code>； 集合或数组对象返回
     * <code>"[]"</code></strong>
     *
     * @param target                      ⽬标对象。
     * @param targetType                  ⽬标对象的类型。
     * @param version                     字段的版本号注解。
     * @param datePattern                 ⽇期字段的格式化模式。
     * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, Type targetType, Double version, String datePattern,
                                boolean excludesFieldsWithoutExpose) {
        if (target == null) {
            return EMPTY_JSON;
        }
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();// 序列化 {@code null} 值字段。
        if (version != null) {
            builder.setVersion(version.doubleValue());
        }
        if (isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        if (excludesFieldsWithoutExpose) {
            builder.excludeFieldsWithoutExposeAnnotation();
        }
        String result = EMPTY;
        Gson gson = builder.create();
        try {
            if (targetType != null) {
                result = gson.toJson(target, targetType);
            } else {
                result = gson.toJson(target);
            }
        } catch (Exception ex) {
            LOGGER.warn("⽬标对象 " + target.getClass().getName() + " 转换 JSON 字符串时，发⽣异常！ ", ex);
            if (target instanceof Collection || target instanceof Iterator || target instanceof Enumeration ||
                    target.getClass().isArray()) {
                result = EMPTY_JSON_ARRAY;
            } else {
                result = EMPTY_JSON;
            }
        }
        return result;
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法只⽤来转换普通的
     * {@code JavaBean}
     * 对象。 </strong>
     * <ul>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Expose} 注解的字段； </li>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Since} 的字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}； </li>
     * </ul>
     *
     * @param target 要转换成 {@code JSON} 的⽬标对象。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target) {
        return toJson(target, null, null, null, false);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法只⽤来转换普通的
     * {@code JavaBean}
     * 对象。 </strong>
     * <ul>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Expose} 注解的字段； </li>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Since} 的字段； </li>
     * </ul>
     *
     * @param target      要转换成 {@code JSON} 的⽬标对象。
     * @param datePattern ⽇期字段的格式化模式。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, String datePattern) {
        return toJson(target, null, null, datePattern, false);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法只⽤来转换普通的
     * {@code JavaBean}
     * 对象。 </strong>
     * <ul>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Expose} 注解的字段； </li>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}； </li>
     * </ul>
     *
     * @param target  要转换成 {@code JSON} 的⽬标对象。
     * @param version 字段的版本号注解({@literal @Since})。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, Double version) {
        return toJson(target, null, version, null, false);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法只⽤来转换普通的
     * {@code JavaBean}
     * 对象。 </strong>
     * <ul>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Since} 的字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}； </li>
     * </ul>
     *
     * @param target                      要转换成 {@code JSON} 的⽬标对象。
     * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, boolean excludesFieldsWithoutExpose) {
        return toJson(target, null, null, null, excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法只⽤来转换普通的
     * {@code JavaBean}
     * 对象。 </strong>
     * <ul>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}； </li>
     * </ul>
     *
     * @param target                      要转换成 {@code JSON} 的⽬标对象。
     * @param version                     字段的版本号注解({@literal @Since})。
     * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, Double version, boolean excludesFieldsWithoutExpose) {
        return toJson(target, null, version, null, excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法通常⽤来转换使⽤泛型
     * 的对象。 </strong>
     * <ul>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Expose} 注解的字段； </li>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Since} 的字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSSS}； </
     * li>
     * </ul>
     * * @param target 要转换成 {@code JSON} 的⽬标对象。
     *
     * @param targetType ⽬标对象的类型。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, Type targetType) {
        return toJson(target, targetType, null, null, false);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法通常⽤来转换使⽤泛型
     * 的对象。 </strong>
     * <ul>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Expose} 注解的字段； </li>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSSS}； </
     * li>
     * </ul>
     *
     * @param target     要转换成 {@code JSON} 的⽬标对象。
     * @param targetType ⽬标对象的类型。
     * @param version    字段的版本号注解({@literal @Since})。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, Type targetType, Double version) {
        return toJson(target, targetType, version, null, false);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法通常⽤来转换使⽤泛型
     * 的对象。 </strong>
     * <ul>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法会转换所有未标注或已标注 {@literal @Since} 的字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}； </li>
     * </ul>
     *
     * @param target                      要转换成 {@code JSON} 的⽬标对象。
     * @param targetType                  ⽬标对象的类型。
     * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, Type targetType, boolean excludesFieldsWithoutExpose) {
        return toJson(target, targetType, null, null, excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的⽬标对象转换成 {@code JSON} 格式的字符串。 <strong>此⽅法通常⽤来转换使⽤泛型
     * 的对象。 </strong>
     * <ul>
     * <li>该⽅法不会转换 {@code null} 值字段； </li>
     * <li>该⽅法转换时使⽤默认的 ⽇期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}； </li>
     * </ul>*
     *
     * @param target                      要转换成 {@code JSON} 的⽬标对象。
     * @param targetType                  ⽬标对象的类型。
     * @param version                     字段的版本号注解({@literal @Since})。
     * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
     * @return ⽬标对象的 {@code JSON} 格式的字符串。
     */
    public static String toJson(Object target, Type targetType, Double version, boolean
            excludesFieldsWithoutExpose) {
        return toJson(target, targetType, version, null, excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
     *
     * @param <T>         要转换的⽬标类型。
     * @param json        给定的 {@code JSON} 字符串。
     * @param token       {@code com.google.gson.reflect.TypeToken} 的类型指示类对象。
     * @param datePattern ⽇期格式模式。
     * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, TypeToken<T> token, String datePattern) {
        if (isEmpty(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, token.getType());
        } catch (Exception ex) {
            LOGGER.error(json + " ⽆法转换为 " + token.getRawType().getName() + " 对象!", ex);
            return null;
        }
    }

    /**
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
     *
     * @param <T>   要转换的⽬标类型。
     * @param json  给定的 {@code JSON} 字符串。
     * @param token {@code com.google.gson.reflect.TypeToken} 的类型指示类对象。
     * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, TypeToken<T> token) {
        return fromJson(json, token, null);
    }

    /*** 将给定的 {@code JSON} 字符串转换成指定的类型对象。 <strong>此⽅法通常⽤来转换普通的
     {@code JavaBean}
     * 对象。 </strong>
     *
     * @param <T> 要转换的⽬标类型。
     * @param json 给定的 {@code JSON} 字符串。
     * @param clazz 要转换的⽬标类。
     * @param datePattern ⽇期格式模式。
     * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
        if (isEmpty(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception ex) {
            LOGGER.error(json + " ⽆法转换为 " + clazz.getName() + " 对象!", ex);
            return null;
        }
    }

    /**
     * 将key-value增加到targetJsonStr中
     * 如果targetJsonStr不是map形式的json字符串，将targetJsonKey-targetJsonStr，作为KV构造⼀个
     * 新的map-json
     * 然后将key-value增加到Map中
     *
     * @param targetJsonKey
     * @param targetJsonStr
     * @param key
     * @param value
     * @return
     */
    public static String appendToMapJson(String targetJsonKey, String targetJsonStr, String key, String
            value) {
        Map<String, String> commentMap = Maps.newHashMap();
        if (StringUtils.isNotBlank(targetJsonStr)) {
            try {
                commentMap = new GsonBuilder().create().fromJson(targetJsonStr, new
                        TypeToken<Map<String, String>>() {
                        }.getType());
            } catch (JsonSyntaxException e) {
                LOGGER.error("targetJsonStr不是正确的JSon字符串" + targetJsonStr + ",errorMsg:{}",
                        ExceptionUtils.getStackTrace(e));
// 说明是targetJsonStr不是json形式,
                commentMap = Maps.newHashMap();
                commentMap.put(targetJsonKey, targetJsonStr);
            }
        }
        commentMap.put(key, value);
        return GsonUtils.toJson(commentMap);
    }

    /**
     * 将key-value增加到mapJson中
     * 如果mapJson不是map形式的json字符串，将key-value，作为KV构造⼀个新的map-json
     * 然后将key-value增加到mapJson中
     *
     * @param mapJson
     * @param key
     * @return
     */
    public static String fromMapJson(String mapJson, String key) {
        if (StringUtils.isBlank(mapJson)) {
            return "";
        }
        try {
            Map<String, String> commentMap = new GsonBuilder().create().fromJson(mapJson, new
                    TypeToken<Map<String, String>>() {
                    }.getType());
            return commentMap.get(key);
        } catch (JsonSyntaxException e) {
            LOGGER.error("mapJson不是正确的JSon字符串" + mapJson + ",errorMsg:{}",
                    ExceptionUtils.getStackTrace(e));
// 说明是targetJsonStr不是json形式,
            return "";
        }
    }

    /**
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象。 <strong>此⽅法通常⽤来转换普通的
     * {@code JavaBean}
     * 对象。 </strong>
     *
     * @param <T>   要转换的⽬标类型。
     * @param json  给定的 {@code JSON} 字符串。
     * @param clazz 要转换的⽬标类。
     * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(json, clazz, null);
    }

    public static boolean isEmpty(String inStr) {
        boolean reTag = false;
        if (inStr == null || "".equals(inStr)) {
            reTag = true;
        }
        return reTag;
    }

    /**
     * 从⼀个map格式的JSon字符串中获取其key对应的value值
     *
     * @param json
     * @param key
     * @return
     */
    public static String parseJson(String json, String key) {
        Map<String, String> commentMap;
        if (StringUtils.isNotBlank(json) && isJson(json)) {
            commentMap = GsonUtils.fromJson(json, new TypeToken<Map<String, String>>() {
            });
        } else {
            LOGGER.error("解析json出错，原字符串内容是： [{}]", json);
            commentMap = Maps.newHashMap();
        }
        return commentMap.get(key);
    }

    public static boolean isJson(String jsonStr) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(jsonStr);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            return false;
        }
        return true;
    }
}