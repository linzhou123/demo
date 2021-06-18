package com.example.demo.Enums;

/**
 * @author LinZhou
 * @data 2020/05/07
 */
public enum PlanStatusEnum {

    //数据枚举
    ONGOING_STATUS("进行中", 0),
    PASS_STATUS("执行成功", 1),
    FAILED_STATUS("执行失败", 2);

    private String name;
    private int value;

    PlanStatusEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * int类型转换成枚举类型
     *
     * @param value 对应枚举的int 类型
     * @return 返回枚举数据
     */

    public static PlanStatusEnum valueOfType(int value) {
        switch (value) {
            case 0:
                return ONGOING_STATUS;
            case 1:
                return PASS_STATUS;
            case 2:
                return FAILED_STATUS;
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
