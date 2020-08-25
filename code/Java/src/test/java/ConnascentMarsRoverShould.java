import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

public class ConnascentMarsRoverShould {
    @BeforeEach
    void setUp() {
    }

    @Test
    void move_following_commands() {
        String[] init = {"P", "X2", "Y75", "DN"};
        String[] commands = {"M5", "1F", "2L", "3F", "4R", "5F"};
        String[] finalPosition = {"P", "X1", "Y3", "DN"};
        MarsRoverReceiver marsRoverReceiver = mock(MarsRoverReceiver.class);
        MarsRoverSender marsRoverSender = mock(MarsRoverSender.class);
        when(marsRoverReceiver.packageReceived())
                .thenReturn(init[0], init[1], init[2], init[3]);
        when(marsRoverReceiver.packageReceived())
                .thenReturn(commands[0], commands[1], commands[2], commands[3], commands[4], commands[5]);

        MarsRover marsRover = new MarsRover(marsRoverReceiver, marsRoverSender);

        verify(marsRoverSender).send(finalPosition[0]);
        verify(marsRoverSender).send(finalPosition[1]);
        verify(marsRoverSender).send(finalPosition[2]);
        verify(marsRoverSender).send(finalPosition[3]);
    }

//    @ParameterizedTest
//    @ValueSource(strings = ["NORTH", "South", "east", "wesT", "Up", "down"])
//    fun `Persist player after moving`(direction: String) {
//        val afterMoveLocation = Location(Id("2"), "$direction room description", Directions(emptyArray()), Items(emptyArray()))
//        val playerAfterMove = Player(Id(sid), playerName, afterMoveLocation)
//        every { katacomb.move(startLocation, any()) } returns afterMoveLocation
//
//        applicationService.execute(sid, direction)
//
//        verify { playersRepository.update(playerAfterMove) }
//    }

}