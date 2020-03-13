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
