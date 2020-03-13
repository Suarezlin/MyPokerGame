import java.util.*;
import java.util.stream.Collectors;

public class JudgeWinner {

    private Map<Character, Integer> map = new HashMap<Character, Integer>();

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
    }

    public Card[] generateCards(String str) {
        String[] strs = str.split(" ");

        Card[] cards = new Card[strs.length];

        for (int i = 0; i < strs.length; i++) {
            switch (strs[i].charAt(0)) {
                case 'D':
                    cards[i] = new Card(CardType.D, map.get(strs[i].charAt(1)));
                    break;
                case 'S':
                    cards[i] = new Card(CardType.S, map.get(strs[i].charAt(1)));
                    break;
                case 'H':
                    cards[i] = new Card(CardType.H, map.get(strs[i].charAt(1)));
                    break;
                case 'C':
                    cards[i] = new Card(CardType.C, map.get(strs[i].charAt(1)));
                    break;
            }
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

    private int level7Or8(Card[] card01, Card[] card02, int num) {
        Map<Card, Integer> map01 = generateMap(card01);
        Map<Card, Integer> map02 = generateMap(card02);

        int s1 = 0;
        int s2 = 0;
        int max01 = Integer.MIN_VALUE;
        int max02 = Integer.MIN_VALUE;


        for (Map.Entry<Card, Integer> e : map01.entrySet()) {
            if (e.getValue() == num) {
                s1 = e.getKey().getNumber();
            } else {
                if (e.getKey().getNumber() > max01) {
                    max01 = e.getKey().getNumber();
                }
            }
        }

        for (Map.Entry<Card, Integer> e : map02.entrySet()) {
            if (e.getValue() == num) {
                s2 = e.getKey().getNumber();
            } else {
                if (e.getKey().getNumber() > max02) {
                    max02 = e.getKey().getNumber();
                }
            }
        }

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

    private int judgeLevel8(Card[] card01, Card[] card02) {

        return level7Or8(card01, card02, 4);
    }

    private int judgeLevel7(Card[] card01, Card[] card02) {
        return level7Or8(card01, card02, 3);
    }

    private int judgeLevel6(Card[] card01, Card[] card02) {
        return judgeLevel1(card01, card02);
    }

    private int judgeLevel5(Card[] card01, Card[] card02) {
        return judgeLevel9(card01, card02);
    }

    private int judgeLevel4(Card[] card01, Card[] card02) {
        List<Card> list01 = new ArrayList<Card>(Arrays.asList(card01));
        List<Card> list02 = new ArrayList<Card>(Arrays.asList(card02));
        Map<Card, Integer> map01 = generateMap(card01);
        Map<Card, Integer> map02 = generateMap(card02);
        int c1 = 0;
        int c2 = 0;

        for (Map.Entry<Card, Integer> e : map01.entrySet()) {
            if (e.getValue() == 2) {
                c1 = e.getKey().getNumber();
                break;
            }
        }

        for (Map.Entry<Card, Integer> e : map02.entrySet()) {
            if (e.getValue() == 2) {
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

    private int judgeLevel3(Card[] card01, Card[] card02) {
        List<Card> list01 = new ArrayList<Card>(Arrays.asList(card01));
        List<Card> list02 = new ArrayList<Card>(Arrays.asList(card02));
        Map<Card, Integer> map01 = generateMap(card01);
        Map<Card, Integer> map02 = generateMap(card02);
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

    private int judgeLevel2(Card[] card01, Card[] card02) {
        List<Card> list01 = new ArrayList<Card>(Arrays.asList(card01));
        List<Card> list02 = new ArrayList<Card>(Arrays.asList(card02));
        Map<Card, Integer> map01 = generateMap(card01);
        Map<Card, Integer> map02 = generateMap(card02);
        int c1 = 0;
        int c2 = 0;

        for (Map.Entry<Card, Integer> e : map01.entrySet()) {
            if (e.getValue() == 2) {
                c1 = e.getKey().getNumber();
                break;
            }
        }

        for (Map.Entry<Card, Integer> e : map02.entrySet()) {
            if (e.getValue() == 2) {
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

    private List<Card> filter(List<Card> list, int num) {
        return list.stream()
                .filter(l -> l.getNumber() != num)
                .sorted()
                .collect(Collectors.toList());
    }

    private Card getMax(Card[] cards) {
        Card res = null;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < cards.length; i++) {
            if (max < cards[i].getNumber()) {
                max = cards[i].getNumber();
                res = cards[i];
            }
        }
        return res;
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

    private int getCardSum(Card[] cards) {
        int sum = 0;
        for (int i = 0; i < cards.length; i++) {
            sum += cards[i].getNumber();
        }
        return sum;
    }


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
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 1),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 7),
        };

        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 4),
                new Card(CardType.C, 3),
                new Card(CardType.H, 13),
                new Card(CardType.D, 14),
        };

        JudgeWinner judgeWinner = new JudgeWinner();

        System.out.println(judgeWinner.judge("C2 S5 C3 H8 D7", "C2 S4 C3 HK DA"));
    }

}
