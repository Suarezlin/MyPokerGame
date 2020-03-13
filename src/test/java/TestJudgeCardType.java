import org.junit.Test;

import static org.junit.Assert.*;

public class TestJudgeCardType {
    private JudgeCardType judgeCardType = new JudgeCardType();

    @Test
    public void testIsPair() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 2),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 7),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };

        assertTrue(judgeCardType.isPair(cards01));
        assertTrue(judgeCardType.isPair(cards02));
        assertFalse(judgeCardType.isPair(cards03));
    }

    @Test
    public void testIsTwoPairs() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 3),
                new Card(CardType.H, 3),
                new Card(CardType.D, 5),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 4),
                new Card(CardType.H, 5),
                new Card(CardType.D, 3),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 4),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };

        assertTrue(judgeCardType.isTwoPairs(cards01));
        assertFalse(judgeCardType.isTwoPairs(cards02));
        assertFalse(judgeCardType.isTwoPairs(cards03));
    }

    @Test
    public void testIsThreeKind() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 3),
                new Card(CardType.H, 3),
                new Card(CardType.D, 3),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 4),
                new Card(CardType.H, 5),
                new Card(CardType.D, 5),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 4),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };

        assertTrue(judgeCardType.isThreeKind(cards01));
        assertFalse(judgeCardType.isThreeKind(cards02));
        assertFalse(judgeCardType.isThreeKind(cards03));
    }

    @Test
    public void testIsStraight() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 3),
                new Card(CardType.C, 4),
                new Card(CardType.H, 5),
                new Card(CardType.D, 6),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 3),
                new Card(CardType.C, 4),
                new Card(CardType.H, 5),
                new Card(CardType.D, 5),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 4),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };

        assertTrue(judgeCardType.isStraight(cards01));
        assertFalse(judgeCardType.isStraight(cards02));
        assertFalse(judgeCardType.isStraight(cards03));
    }

    @Test
    public void testIsFlush() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.C, 3),
                new Card(CardType.C, 4),
                new Card(CardType.C, 5),
                new Card(CardType.C, 6),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 2),
                new Card(CardType.H, 2),
                new Card(CardType.D, 2),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 4),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };

        assertTrue(judgeCardType.isFlush(cards01));
        assertFalse(judgeCardType.isFlush(cards02));
        assertFalse(judgeCardType.isFlush(cards03));
    }

    @Test
    public void testIsFullHouse() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 2),
                new Card(CardType.D, 5),
                new Card(CardType.H, 5),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 4),
                new Card(CardType.H, 2),
                new Card(CardType.D, 2),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 4),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };

        assertTrue(judgeCardType.isFullHouse(cards01));
        assertFalse(judgeCardType.isFullHouse(cards02));
        assertFalse(judgeCardType.isFullHouse(cards03));
    }

    @Test
    public void testIsFourKind() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 2),
                new Card(CardType.D, 2),
                new Card(CardType.H, 5),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 2),
                new Card(CardType.C, 2),
                new Card(CardType.H, 2),
                new Card(CardType.D, 2),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 4),
                new Card(CardType.C, 3),
                new Card(CardType.H, 8),
                new Card(CardType.D, 5),
        };

        assertTrue(judgeCardType.isFourKind(cards01));
        assertTrue(judgeCardType.isFourKind(cards02));
        assertFalse(judgeCardType.isFourKind(cards03));
    }

    public void testIsStraightFlush() {
        Card[] cards01 = {
                new Card(CardType.C, 2),
                new Card(CardType.C, 3),
                new Card(CardType.C, 4),
                new Card(CardType.C, 5),
                new Card(CardType.C, 6),
        };
        Card[] cards02 = {
                new Card(CardType.C, 2),
                new Card(CardType.S, 3),
                new Card(CardType.C, 4),
                new Card(CardType.H, 5),
                new Card(CardType.D, 6),
        };
        Card[] cards03 = {
                new Card(CardType.C, 2),
                new Card(CardType.C, 4),
                new Card(CardType.C, 3),
                new Card(CardType.C, 8),
                new Card(CardType.C, 5),
        };

        assertTrue(judgeCardType.isFourKind(cards01));
        assertFalse(judgeCardType.isFourKind(cards02));
        assertFalse(judgeCardType.isFourKind(cards03));
    }
}
