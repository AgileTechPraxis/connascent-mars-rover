import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverConnascentShould {


    @BeforeEach
    void setUp() {

    }


    @Test
    void prove_tests_work() {
        assertEquals(true, true);
    }

//    @ParameterizedTest
//    @CsvSource({
//            "'BOTTOM_RIGHT,TOP_LEFT,MIDDLE_LEFT,TOP_MIDDLE,MIDDLE_MIDDLE,TOP_RIGHT', O",
//            "'TOP_LEFT,MIDDLE_LEFT,TOP_MIDDLE,MIDDLE_MIDDLE,TOP_RIGHT', X",
//            "'BOTTOM_LEFT,MIDDLE_LEFT,BOTTOM_MIDDLE,MIDDLE_MIDDLE,BOTTOM_RIGHT', X",
//            "'TOP_LEFT,BOTTOM_LEFT,MIDDLE_MIDDLE,MIDDLE_LEFT,BOTTOM_RIGHT', X"
//    }
//    )
//    void return_winner(String moves, TicTacToeEngine.Player expectedWinner) {
//        String[] movesSplitted = moves.split(",");
//        for (String move : movesSplitted) {
//            engine.play(Field.valueOf(move));
//        }
//
//        TicTacToeEngine.Player winner = engine.getWinner();
//
//        assertEquals(expectedWinner, winner);
//    }
}
