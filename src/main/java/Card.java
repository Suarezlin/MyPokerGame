public class Card implements Comparable<Card> {

    private CardType type;
    private int number;

    public Card(CardType type, int number) {
        this.type = type;
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    /**
    * @Description: 重写 equals 方法，方便在 TreeSet 和 TreeMap 中使用
    * @Param: obj 要比较的对象
    * @return: 是否相等
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    @Override
    public boolean equals(Object obj) {
        Card that = (Card)obj;
        return this.getNumber() == that.getNumber();
    }

    /**
    * @Description: 实现 Comparable 接口，方便方便在 TreeMap 中和排序中使用
    * @Param: o 要比较的对象
    * @return: 大小情况
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public int compareTo(Card o) {
        if (this.getNumber() > o.getNumber()) {
            return 1;
        } else if (this.getNumber() < o.getNumber()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.getType() + " " + this.getNumber();
    }
}

enum CardType {
    D('D'),
    S('S'),
    H('H'),
    C('C');

    private char name;

    CardType(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }



}
