import java.util.*;

public class JudgeCardType {


    /**
    * @Description: 判断牌是不是对子
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isPair(Card[] cards) {
        Set<Card> set = new TreeSet<Card>();
        for (int i = 0; i < cards.length; i++) {
            if (set.contains(cards[i])) {
                return true;
            }
            set.add(cards[i]);
        }
        return false;

    }
    
    /**
    * @Description: 判断牌是不是两对
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isTwoPairs(Card[] cards) {
        Map<Card, Integer> map = generateMap(cards);
        
        int count = 0;
        for (HashMap.Entry<Card, Integer> e : map.entrySet()) {
            if (e.getValue() > 1) {
                count++;
            }
        }
        
        return count > 1 ? true : false;
    }
    
    /**
    * @Description: 判断牌是不是三条
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isThreeKind(Card[] cards) {
        Map<Card, Integer> map = generateMap(cards);

        int count = 0;
        for (HashMap.Entry<Card, Integer> e : map.entrySet()) {
            if (e.getValue() >= 3) {
                return true;
            }
        }

        return false;
    }

    /**
    * @Description: 判断牌是不是顺子
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isStraight(Card[] cards) {

        // Card 已实现 Comparable 接口，可以直接排序
        Arrays.sort(cards);

        // 判断是否连续
        for (int i = 1; i < cards.length; i++) {
            if (cards[i].getNumber() - cards[i-1].getNumber() != 1) {
                return false;
            }
        }
        return true;
    }

    
    /**
    * @Description: 判断牌是不是同花
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isFlush(Card[] cards) {
        CardType type = cards[0].getType();

        for (int i = 1; i < cards.length; i++) {
            if (cards[i].getType() != type) {
                return false;
            }
        }
        return true;
    }


    /**
    * @Description: 判断牌是不是葫芦
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isFullHouse(Card[] cards) {
        Map<Card, Integer> map = generateMap(cards);

        if (map.size() == 2 && !isFourKind(cards)) {
            return true;
        }
        return false;

    }

    /**
    * @Description: 判断牌是不是铁支
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isFourKind(Card[] cards) {
        Map<Card, Integer> map = generateMap(cards);

        for (HashMap.Entry<Card, Integer> e : map.entrySet()) {
            if (e.getValue() >= 4) {
                return true;
            }
        }

        return false;
    }

    /**
    * @Description: 判断牌是不是同花顺
    * @Param: [cards]
    * @return: boolean
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public boolean isStraightFlush(Card[] cards) {
        return isFlush(cards) && isStraight(cards);
    }

    /**
    * @Description: 生成一个卡牌类型 map
    * @Param: [cards]
    * @return: java.util.Map<Card,java.lang.Integer>
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public Map<Card, Integer> generateMap(Card[] cards) {
        Map<Card, Integer> map = new TreeMap<Card, Integer>();
        for (int i = 0; i < cards.length; i++) {
            map.put(cards[i], map.getOrDefault(cards[i], 0) + 1);
        }
        return map;
    }

    public int getCardsType(Card[] cards) {

        if (isStraightFlush(cards)) {
            return 9;
        }
        if (isFourKind(cards)) {
            return 8;
        }
        if (isFullHouse(cards)) {
            return 7;
        }
        if (isFlush(cards)) {
            return 6;
        }
        if (isStraight(cards)) {
            return 5;
        }
        if (isThreeKind(cards)) {
            return 4;
        }
        if (isTwoPairs(cards)) {
            return 3;
        }
        if (isPair(cards)) {
            return 2;
        }
        return 1;
    }
}
