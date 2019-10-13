package com.crud.cloud.evanliu2968.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author likeyong
 * @date 2017年2月13日
 */
@SuppressWarnings("unchecked")
public class BeanHelper {

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static boolean usePOVOCopyHelper = false;

    private static final ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
                    DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).disableDefaultTyping()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

	/*static {
		mapper.setVisibility(mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY));
		mapper.registerModule(new SimpleModule().addSerializer(java.util.Date.class, new DateSerializer())
				.addDeserializer(java.util.Date.class, new DateDeserializer()));
	}*/

    synchronized public static void setUsePOVOCopyHelper(boolean usePOVOCopyHelper) {
        BeanHelper.usePOVOCopyHelper = usePOVOCopyHelper;
    }

    public static class DateSerializer extends JsonSerializer<java.util.Date> {
        public void serialize(java.util.Date date, JsonGenerator jg, SerializerProvider paramSerializerProvider)
                throws IOException, JsonProcessingException {
            jg.writeString(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
        }
    }

    public static class DateDeserializer extends JsonDeserializer<java.util.Date> {
        public java.util.Date deserialize(JsonParser jp, DeserializationContext dc)
                throws IOException, JsonProcessingException {
            String date = null;
            try {
                date = jp.getText();
                return DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMM/dd HH:mm:ss",
                        "yyyyMM/dd");
            } catch (Exception e) {
//				Logger.warn(FCS.get("Input value {0} can't not format to Date", date));
            }
            return null;
        }
    }

    private BeanHelper() {
    }

    /**
     * if usePOVOCopyHelper=true, then use POVOCopyHelper to copy the object @see POVOCopyHelper.
     * if usePOVOCopyHelper=false, then use Jackson serialize the origin object and de-serialize to destine object.
     *
     * @param dest
     * @param orig
     * @return
     * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>
     * @since Feb 14, 2016
     */
    public static <T> T copyProperties(Object dest, Object orig) {
        if (orig == null) {
            return (T) null;
        }
        try {
            if (usePOVOCopyHelper) {
                return POVOCopyHelper.copyTo(orig, dest);
            } else {
                ByteArrayOutputStream baos = new ByteArrayOutputStream(10240);
                mapper.writeValue(baos, orig);
                mapper.readerForUpdating(dest).readValue(baos.toByteArray());
            }
        } catch (Exception e) {
//			Exceptions.runtime("org.iff.infra.util.BeanHelper.copyProperties(Object, Object)", e);
        }
        return (T) dest;
    }

    /**
     * if usePOVOCopyHelper=true, then use POVOCopyHelper to copy the object @see POVOCopyHelper.
     * if usePOVOCopyHelper=false, then use GSON serialize the origin object and de-serialize to destine class.
     *
     * @param clazz
     * @param orig
     * @return
     * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>
     * @since Feb 14, 2016
     */
    public static <T> T copyProperties(Class<T> clazz, Object orig) {
        if (orig == null) {
            return (T) null;
        }
        if (usePOVOCopyHelper) {
            return POVOCopyHelper.copyTo(orig, clazz);
        } else {
            Object fromJson = GSON.fromJson(GSON.toJsonTree(orig), clazz);
            return (T) fromJson;
        }
    }

    public static <T, D extends Object> List<T> copyListProperties(Class<T> clazz, List<D> d_list) {
        List<T> t_list = null;
        if (d_list != null && d_list.size() > 0) {
            t_list = new ArrayList<T>();
            for (Object o : d_list) {
                try {
                    t_list.add(BeanHelper.copyProperties(clazz, o));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return t_list;
    }

    public static <T, D extends Object> List<D> copyDListProperties(Class<D> clazz, Collection<T> d_list) {
        List<D> t_list = null;
        if (d_list != null && d_list.size() > 0) {
            t_list = new ArrayList<D>();
            for (Object o : d_list) {
                try {
                    t_list.add(BeanHelper.copyProperties(clazz, o));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return t_list;
    }


}
