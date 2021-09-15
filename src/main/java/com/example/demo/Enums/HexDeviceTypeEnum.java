package com.example.demo.Enums;

public enum HexDeviceTypeEnum {
    UNKNOWN(0,  "未知类型"),
    SMOKE(1, "无线烟感主机"),
    GAS(2, "燃气监测主机"),
    ELECTRIC(3, "智能预警监测主机"),
    ELECTRIC_THREE_PHASE(4, "智能预警监测主机（三相）"),
    ELECTRIC_MULTI_CHANNEL(5, "智能预警监测主机（多通道）"),
    WATER(6, "消防用水主机"),
    ELECTRIC_WIFI(7, "智能预警监测主机（WIFI）"),
    FIRE(8, "用户信息传输主机"),
    RELAY(9, "物联中继主机"),
    DYNAMIC(10, "动态监测主机"),
    HAND(11, "手动报警主机"),
    ALARM(12, "声光报警主机"),
    ELECTRIC_THREE_PHASE_METERING(13, "智能预警监测主机（三相计量）"),
    REMOTE_CONTROL(14, "远程控制主机"),
    CAMERA(15, "摄像头"),
    WATER_LOGGING(16, "水浸主机"),
    ONE_BUTTON_ALARM(17, "一键报警器"),
    SMART_WATCH(18, "智能手表");


    private String name;
    private int value;

    HexDeviceTypeEnum(int value,String name) {
        this.name = name;
        this.value = value;
    }

    /**
     * int类型转换成枚举类型
     *
     * @param value 对应枚举的int 类型
     * @return 返回枚举数据
     */

    public static HexDeviceTypeEnum valueOfType(int value) {
        switch (value) {
            case 0:
                return UNKNOWN;
            case 1:
                return SMOKE;
            case 2:
                return GAS;
            case 3:
                return ELECTRIC;
            case 4:
                return ELECTRIC_THREE_PHASE;
            case 5:
                return ELECTRIC_MULTI_CHANNEL;
            case 6:
                return WATER;
            case 7:
                return ELECTRIC_WIFI;
            case 8:
                return FIRE;
            case 9:
                return RELAY;
            case 10:
                return DYNAMIC;
            case 11:
                return HAND;
            case 12:
                return ALARM;
            case 13:
                return ELECTRIC_THREE_PHASE_METERING;
            case 14:
                return REMOTE_CONTROL;
            case 15:
                return CAMERA;
            case 16:
                return WATER_LOGGING;
            case 17:
                return ONE_BUTTON_ALARM;
            case 18:
                return SMART_WATCH;
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
