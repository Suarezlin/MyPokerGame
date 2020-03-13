import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestJudgeWinner {

    private JudgeWinner judgeWinner = new JudgeWinner();

    @Test
    public void testGenerateCards() {

        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 3),
                new Card(CardType.H, 13),
                new Card(CardType.D, 14),
        };

        Card[] cards02 = judgeWinner.generateCards("C2 S2 C3 HK DA");

        assertArrayEquals(cards01, cards02);
    }

    @Test
    public void testJudge() {
        String card01 = "H2 D3 S5 C9 DK";
        String card02 = "C2 H3 S4 C8 HA";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 S4 C4 D2 H4";
        card02 = "S2 S8 SA SQ S3";
        assertEquals("A 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 D3 S5 C9 DK";
        card02 = "C2 H3 S4 C8 HK";
        assertEquals("A 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 D3 S5 C9 DK";
        card02 = "D2 H3 C5 S9 HK";
        assertEquals("平局", judgeWinner.judge(card01, card02));

        card01 = "H2 H3 H5 H9 HK";
        card02 = "D2 H3 C5 S9 HK";
        assertEquals("A 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 H3 H4 H5 H6";
        card02 = "H3 H4 H5 H6 H7";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 H2 H2 H2 D6";
        card02 = "H3 H4 H5 H6 H6";
        assertEquals("A 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 H2 H2 H2 D6";
        card02 = "H3 H3 H3 H3 S6";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 H2 H2 H2 D7";
        card02 = "H2 H2 H2 H2 S6";
        assertEquals("A 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 D2 C2 C4 D4";
        card02 = "H3 H3 H3 D5 S6";
        assertEquals("A 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 D2 C2 C4 D4";
        card02 = "H3 H3 H3 D5 S5";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 D2 C2 C4 D4";
        card02 = "H2 H2 H2 D5 S5";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));

        card01 = "H2 H3 H5 H7 H4";
        card02 = "H2 H3 H5 H7 H4";
        assertEquals("平局", judgeWinner.judge(card01, card02));

        card01 = "H2 H3 H5 H7 H4";
        card02 = "H2 H3 H5 H8 H4";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));

        card01 = "D2 S2 H5 H8 H4";
        card02 = "D2 S4 H5 H8 H4";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));

        card01 = "D2 S2 H5 H8 H4";
        card02 = "D2 S2 H5 H8 H4";
        assertEquals("平局", judgeWinner.judge(card01, card02));

        card01 = "D2 S2 H5 H8 H4";
        card02 = "D2 S2 H5 H9 H4";
        assertEquals("B 胜利", judgeWinner.judge(card01, card02));
    }
}
