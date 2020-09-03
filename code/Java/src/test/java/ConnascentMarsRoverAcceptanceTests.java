import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConnascentMarsRoverAcceptanceTests {
    private MarsRoverReceiver marsRoverReceiver;
    private ISendNotifications marsRoverSender;
    private INasaAntenna nasaAntenna;
    private MarsRover marsRover;

    @BeforeEach
    void setUp() {
        nasaAntenna = mock(INasaAntenna.class);
        marsRoverReceiver = new MarsRoverReceiver();
        marsRoverSender = new MarsRoverSender(nasaAntenna);
        MarsRoverController marsRoverController = new MarsRoverController();
        ServiceBus marsRoverBus = new ServiceBus();
        marsRover = new MarsRover(marsRoverBus, marsRoverReceiver, marsRoverSender, marsRoverController);
    }

    @Test
    void move_following_commands() {
        String[] inputPackages = {"X2", "Y5", "DN", "M5", "1F", "2L", "3F", "4R", "5F"};
        for (String pack : inputPackages) {
            marsRoverReceiver.received(pack);
        }

        verify(nasaAntenna).received(new String[]{"X1", "Y7", "DN"});
    }
}

