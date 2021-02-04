package domain;

/**
 * @Auther: Carl
 * @Date: 2021/02/04/10:42
 * @Description:
 */
public class Printer implements Equipment {

    private String name;    //机器型号
    private String type;    //机器类型

    public Printer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return name + " (" + type + ") ";
    }

}
