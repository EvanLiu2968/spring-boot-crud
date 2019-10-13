package com.crud.cloud.evanliu2968.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;

import java.util.List;

public class ResponseData<T> {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Boolean bizError = false;

    /**
     * 响应业务状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应中的数据
     */
    @JsonProperty(value = "response")
    private T data;

    public ResponseData() {
    }

    public static ResponseData build(Boolean bizError, Integer code, String msg, Object data) {
        return new ResponseData(bizError, code, msg, data);
    }

    public static ResponseData build(Integer code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    public static ResponseData build(Integer code, String msg) {
        return new ResponseData(code, msg, null);
    }

    /**
     * 通用失败接口。
     *
     * @param msg 失败信息
     * @return ResponseData
     */
    public static ResponseData fail(String msg) {
        return build(ResponseCodeEnum.SYSTEM_ERROR.getValue(), msg, null);
    }

    /**
     * 通用失败接口。
     *
     * @return ResponseData
     */
    public static ResponseData fail() {
        return fail(ResponseCodeEnum.SYSTEM_ERROR.getMessage());
    }

    /**
     * 通用失败接口2。
     *
     * @return ResponseData
     */
    public static ResponseData fail(ResponseCodeEnum responseCodeEnum) {
        return build(responseCodeEnum.getValue(), responseCodeEnum.getMessage());
    }

    /**
     * 通用成功接口。
     *
     * @param msg  成功信息
     * @param data 返回数据
     * @return ResponseData
     */
    public static ResponseData success(String msg, Object data) {
        return build(ResponseCodeEnum.SUCCESS.getValue(), msg, data);
    }

    /**
     * 通用成功接口。
     *
     * @param data 返回数据
     * @return ResponseData
     */
    public static ResponseData success(Object data) {
        return success(ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 通用成功接口。
     *
     * @return ResponseData
     */
    public static ResponseData success() {
        return success(null);
    }

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    private ResponseData(Boolean bizError, Integer code, String message, T data) {
        this.bizError = bizError;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz    TaotaoResult中的object类型
     * @return
     */
    public static ResponseData formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResponseData.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ResponseData format(String json) {
        try {
            return MAPPER.readValue(json, ResponseData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     */
    public static ResponseData formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean getBizError() {
        return this.bizError;
    }

    public void setBizError(Boolean bizError) {
        this.bizError = bizError;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
