package com.example.demo.Enums;

public enum EnvTypeEnum {

    STRING_TYPE("srting类型",0),
    INT_TYPE("int类型",1),
    BOOLEAN_TYPE("boolean类型",2);


    private String name;
    private int value;

    EnvTypeEnum(String name ,int value){
        this.name=name;
        this.value=value;
    }

    /**
     * int类型转换成枚举类型
     * @param value 对应枚举的int 类型
     * @return 返回枚举数据
     * */

    public static EnvTypeEnum valueOfType(int value){
        switch (value){
            case 0:
                return STRING_TYPE;
            case 1:
                return INT_TYPE;
            case 2:
                return BOOLEAN_TYPE;
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
