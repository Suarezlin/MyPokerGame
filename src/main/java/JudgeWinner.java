import java.util.*;
import java.util.stream.Collectors;

public class JudgeWinner {

    private Map<Character, Integer> map = new HashMap<Character, Integer>();
    private Map<Character, CardType> types = new HashMap<>();

    public JudgeWinner() {
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        map.put('T', 10);
        map.put('J', 11);
        map.put('Q', 12);
        map.put('K', 13);
        map.put('A', 14);
        types.put('D', CardType.D);
        types.put('S', CardType.S);
        types.put('H', CardType.H);
        types.put('C', CardType.C);
    }

    public Card[] generateCards(String str) {
        String[] strs = str.split(" ");

        Card[] cards = new Card[strs.length];

        for (int i = 0; i < strs.length; i++) {
            cards[i] = new Card(types.get((strs[i].charAt(0))), map.get(strs[i].charAt(1)));
        }

        return cards;
    }

    /**
    * @Description: 两者牌型相同时，判定胜负
    * @Param: [card01, card02, level]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judge(Card[] card01, Card[] card02, int level) {
        switch (level) {
            case 9:
                return judgeLevel9(card01, card02);
            case 8:
                return judgeLevel8(card01, card02);
            case 7:
                return judgeLevel7(card01, card02);
            case 6:
                return judgeLevel6(card01, card02);
            case 5:
                return judgeLevel5(card01, card02);
            case 4:
                return judgeLevel4(card01, card02);
            case 3:
                return judgeLevel3(card01, card02);
            case 2:
                return judgeLevel2(card01, card02);
            case 1:
                return judgeLevel1(card01, card02);
        }
        return -1;
    }

    /**
    * @Description: 两者都为同花顺时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel9(Card[] card01, Card[] card02) {

        int sum1 = getCardSum(card01);
        int sum2 = getCardSum(card02);
        if (sum1 < sum2) {
            return -1;
        } else if (sum1 > sum2) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
    * @Description: 铁支和葫芦情况类似，统一判断
    * @Param: [card01, card02, num]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int level7Or8(Card[] card01, Card[] card02, int num) {
        Map<Card, Integer> map01 = JudgeCardType.generateMap(card01);
        Map<Card, Integer> map02 = JudgeCardType.generateMap(card02);


        int[] res = getPairCardNum(map01, num);
        int s1 = res[0];
        int max01 = res[1];
        res = getPairCardNum(map02, num);
        int s2 = res[0];
        int max02 = res[1];


        if (s1 > s2) {
            return 1;
        } else if (s1 < s2) {
            return -1;
        } else {
            if (max01 > max02) {
                return 1;
            } else if (max01 < max02) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
    * @Description: 多个牌型都需要判断对子，封装成方法，方便调用
    * @Param: [map, num]
    * @return: int[]
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int[] getPairCardNum(Map<Card, Integer> map, int num) {
        int s = 0;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Card, Integer> e : map.entrySet()) {
            if (e.getValue() == num) {
                s = e.getKey().getNumber();
            } else {
                if (e.getKey().getNumber() > max) {
                    max = e.getKey().getNumber();
                }
            }
        }
        return new int[] {s, max};
    }

    /**
    * @Description: 两者都为铁支时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel8(Card[] card01, Card[] card02) {

        return level7Or8(card01, card02, 4);
    }

    /**
    * @Description: 两者都为葫芦时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel7(Card[] card01, Card[] card02) {
        return level7Or8(card01, card02, 3);
    }

    /**
    * @Description: 两者都为同花时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel6(Card[] card01, Card[] card02) {
        return judgeLevel1(card01, card02);
    }

    /**
    * @Description: 两者都为顺子时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel5(Card[] card01, Card[] card02) {
        return judgeLevel9(card01, card02);
    }

    /**
    * @Description: 对子和三条情况类似，统一判断
    * @Param: [card01, card02, num]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel2Or4(Card[] card01, Card[] card02, int num) {
        List<Card> list01 = new ArrayList<Card>(Arrays.asList(card01));
        List<Card> list02 = new ArrayList<Card>(Arrays.asList(card02));
        Map<Card, Integer> map01 = JudgeCardType.generateMap(card01);
        Map<Card, Integer> map02 = JudgeCardType.generateMap(card02);


        int[] res = getPairCardNum(map01, num);
        int c1 = res[0];
        res = getPairCardNum(map02, num);
        int c2 = res[0];


        if (c1 > c2) {
            return 1;
        } else if (c1 < c2) {
            return -1;
        } else {
            list01 = filter(list01, c1);
            list02 = filter(list02, c2);

            for (int i = list01.size() - 1; i >= 0; i--) {
                if (list01.get(i).getNumber() > list02.get(i).getNumber()) {
                    return 1;
                } else if (list01.get(i).getNumber() < list02.get(i).getNumber()) {
                    return -1;
                }
            }
            return 0;
        }
    }

    /**
    * @Description: 两者都为三条时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel4(Card[] card01, Card[] card02) {
        return judgeLevel2Or4(card01, card02, 3);

    }

    /**
    * @Description: 两者都为两对时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel3(Card[] card01, Card[] card02) {
        List<Card> list01 = new ArrayList<Card>(Arrays.asList(card01));
        List<Card> list02 = new ArrayList<Card>(Arrays.asList(card02));
        Map<Card, Integer> map01 = JudgeCardType.generateMap(card01);
        Map<Card, Integer> map02 = JudgeCardType.generateMap(card02);


        int c1 = 0;
        int c2 = 0;

        for (Map.Entry<Card, Integer> e : map01.entrySet()) {
            if (e.getValue() == 2 && e.getKey().getNumber() > c1) {
                c1 = e.getKey().getNumber();
                break;
            }
        }

        for (Map.Entry<Card, Integer> e : map02.entrySet()) {
            if (e.getValue() == 2 && e.getKey().getNumber() > c2) {
                c2 = e.getKey().getNumber();
                break;
            }
        }

        if (c1 > c2) {
            return 1;
        } else if (c1 < c2) {
            return -1;
        } else {
            list01 = filter(list01, c1);
            list02 = filter(list02, c2);

            return judgeLevel2(list01.toArray(new Card[list01.size()]), list02.toArray(new Card[list02.size()]));
        }
    }

    /**
    * @Description: 两者都为对子时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel2(Card[] card01, Card[] card02) {
        return judgeLevel2Or4(card01, card02, 2);
    }

    /**
    * @Description: 两者都为散牌时判断胜负
    * @Param: [card01, card02]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int judgeLevel1(Card[] card01, Card[] card02) {
        Arrays.sort(card01);
        Arrays.sort(card02);

        for (int i = card01.length - 1; i >= 0; i--) {
            if (card01[i].getNumber() > card02[i].getNumber()) {
                return 1;
            } else if (card01[i].getNumber() < card02[i].getNumber()) {
                return -1;
            }
        }
        return 0;
    }

    /**
    * @Description: 过滤已确定相同的牌
    * @Param: [list, num]
    * @return: java.util.List<Card>
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private List<Card> filter(List<Card> list, int num) {
        return list.stream()
                .filter(l -> l.getNumber() != num)
                .sorted()
                .collect(Collectors.toList());
    }
    
    
    /**
    * @Description: 计算所有的牌加在一起的和
    * @Param: [cards]
    * @return: int
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    private int getCardSum(Card[] cards) {
        int sum = 0;
        for (int i = 0; i < cards.length; i++) {
            sum += cards[i].getNumber();
        }
        return sum;
    }


    /**
    * @Description: 判断双方胜负
    * @Param: [str1, str2]
    * @return: java.lang.String
    * @Author: 林子牛
    * @Date: 2020-03-13
    */
    public String judge(String str1, String str2) {

        JudgeCardType judgeCardType = new JudgeCardType();

        Card[] card01 = generateCards(str1);
        Card[] card02 = generateCards(str2);

        int level01 = judgeCardType.getCardsType(card01);
        int level02 = judgeCardType.getCardsType(card02);

        if (level01 > level02) {
            return "A 胜利";
        } else if (level01 < level02) {
            return "B 胜利";
        } else {
            int status = judge(card01, card02, level01);

            if (status > 0) {
                return "A 胜利";
            } else if (status < 0) {
                return "B 胜利";
            } else {
                return "平局";
            }
        }
    }


    public static void main(String[] args) {


        JudgeWinner judgeWinner = new JudgeWinner();

        System.out.println(judgeWinner.judge("C2 S5 C3 H8 D7", "C2 S4 C3 HK DA"));
        System.out.println(judgeWinner.judge("C2 C5 C3 C8 C7", "C2 S4 C3 HK DA"));
    }

}
