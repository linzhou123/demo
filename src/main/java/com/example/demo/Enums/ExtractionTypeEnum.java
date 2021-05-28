package com.example.demo.Enums;

public enum ExtractionTypeEnum {

    HeardExtraction("提取消息头内容", 0),
    BodyExtraction("提取返回内容", 1);

    private String name;
    private int value;

    ExtractionTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * int类型转换成枚举类型
     *
     * @param value 对应枚举的int 类型
     * @return 返回枚举数据
     */

    public static ExtractionTypeEnum valueOfType(int value) {
        switch (value) {
            case 0:
                return HeardExtraction;
            case 1:
                return BodyExtraction;
            default:
                return null;
        }
    }


    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
