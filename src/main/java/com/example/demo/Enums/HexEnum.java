package com.example.demo.Enums;

/**
 * @author LinZhou
 * @data 2020/05/07
 * */
public enum HexEnum {

    //数据枚举
    RealTimeData("实时数据",0),
    AlarmData("报警数据",1),
    Fault("故障数据",2);

    private String name;
    private int value;
    HexEnum(String name ,int value){
        this.name=name;
        this.value=value;
    }

    /**
     * int类型转换成枚举类型
     * @param value 对应枚举的int 类型
     * @return 返回枚举数据
     * */

    public static HexEnum valueOfType(int value){
        switch (value){
            case 0:
                return RealTimeData;
            case 1:
                return AlarmData;
            case 2:
                return Fault;
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
