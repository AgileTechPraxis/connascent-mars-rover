import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MarsRoverSenderShould {
    MarsRoverSender marsRoverSender;
    private IMessageReceivedBus mockServiceBus;

    @BeforeEach
    void setUp() {
    }

    @Test
    void sendMessageCorrectly()  {
        INasaAntenna nasaAntenna = mock(INasaAntenna.class);
        marsRoverSender = new MarsRoverSender(nasaAntenna);

        marsRoverSender.send("6 99 S");

        verify(nasaAntenna).received(new String[]{"X6","Y99","DS"});
    }

    @Test
    void sendErrorMessage()  {
        INasaAntenna nasaAntenna = mock(INasaAntenna.class);
        marsRoverSender = new MarsRoverSender(nasaAntenna);

        marsRoverSender.sendError();

        verify(nasaAntenna).received(new String[]{"ER"});
    }
}
