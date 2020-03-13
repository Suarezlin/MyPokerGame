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


    @Override
    public boolean equals(Object obj) {
        Card that = (Card)obj;
        return this.getNumber() == that.getNumber();
    }

    public int compareTo(Card o) {
        if (this.getNumber() > o.getNumber()) {
            return 1;
        } else if (this.getNumber() < o.getNumber()) {
            return -1;
        } else {
            return 0;
        }
    }
}

enum CardType {
    D("方片", 1),
    S("黑桃", 2),
    H("红桃", 3),
    C("梅花", 4);

    private String name;
    private int index;

    CardType(String name, int index) {
        this.name = name;
        this.index = index;
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
