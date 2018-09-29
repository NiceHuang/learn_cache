package cn.huangnengxin.common.utils;

/**
 * Created by viruser on 2018/9/19.
 */
public enum DataSourceTypeEnum {

    READ("read", "读库"), WRITE("write", "写库");

    private String type;
    private String name;

    DataSourceTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
