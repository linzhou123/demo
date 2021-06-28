package com.example.demo.Enums;

public enum CodeEnum {

    INTERNAL_ERROR("参数校验错误或者内部逻辑错误", 1001),
    SYSTEM_ERROR("系统异常，请稍后重试", 1002),
    BOOLEAN_TYPE("boolean类型", 2);


    private String message;
    private int code;

    CodeEnum(String message, int code) {
        this.message = message;
        this.code = code;

    }

    /**
     * int类型转换成枚举类型
     *
     * @param code 对应枚举的int 类型
     * @return 返回枚举数据
     */

    public static CodeEnum valueOfType(int code) {
        switch (code) {
            case 1001:
                return INTERNAL_ERROR;
            case 1002:
                return SYSTEM_ERROR;
            case 2:
                return BOOLEAN_TYPE;
            default:
                return null;
        }
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
