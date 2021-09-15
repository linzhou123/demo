package com.example.demo.Enums;

public enum HexDataTypeEnum {
    ALARM_DATA("报警数据", 0),
    FAULT_DATA("故障数据", 1),
    OTHER_DATA("其他数据", 2);


    private String name;
    private int value;

    HexDataTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * int类型转换成枚举类型
     *
     * @param value 对应枚举的int 类型
     * @return 返回枚举数据
     */

    public static HexDataTypeEnum valueOfType(int value) {
        switch (value) {
            case 0:
                return ALARM_DATA;
            case 1:
                return FAULT_DATA;
            case 2:
                return OTHER_DATA;
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
