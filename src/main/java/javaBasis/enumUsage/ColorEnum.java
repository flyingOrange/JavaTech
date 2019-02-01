package javaBasis.enumUsage;

enum Color1 {
    RED, YELLOW, GREEN, BLANK, BLACK, ORANGE;
}

enum Color2 {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Color2(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        //Color2.values()遍历枚举值
        for (Color2 c : Color2.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}

public class ColorEnum {
    Color1 color = Color1.RED;

    // switch用法
    public void change() {
        switch (color) {
        case RED:
            color = Color1.GREEN;
            break;
        case YELLOW:
            color = Color1.RED;
            break;
        case GREEN:
            color = Color1.YELLOW;
            break;
        default:
            break;
        }
        System.out.println(color);
    }

    public static void main(String[] args) {
        ColorEnum colorEnum = new ColorEnum();
        colorEnum.change();


        
    }

}
