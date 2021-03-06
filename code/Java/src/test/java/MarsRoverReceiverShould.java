import Infrastructure.Bus.IMessageReceivedBus;
import Infrastructure.SpaceComm.MarsRoverReceiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MarsRoverReceiverShould {
    MarsRoverReceiver marsRoverReceiver;
    private IMessageReceivedBus mockServiceBus;

    private static Stream<Arguments> valuesProvider() {
        return Stream.of(
                Arguments.of(
                        new String[]{"X2", "Y5", "DN", "M5", "1F", "2L", "3F", "4R", "5F"},
                        "100 100\n2 5 N\nFLFRF"),
                Arguments.of(
                        new String[]{"X2", "Y5", "DN", "M4",  "3F","2L","1F",  "4R"},
                        "100 100\n2 5 N\nFLFR")
        );
    }

    @ParameterizedTest
    @MethodSource("valuesProvider")
    void rebuildMessageCorrectly(String[] packages, String rebuiltMessage) {
        marsRoverReceiver = new MarsRoverReceiver();
        mockServiceBus = mock(IMessageReceivedBus.class);
        marsRoverReceiver.writesTo(mockServiceBus);

        for (String datagram : packages) {
            marsRoverReceiver.received(datagram);
        }

        verify(mockServiceBus).NotifyMessageReceived(rebuiltMessage);
    }

    @Test
    void notifyErrorCorrectly() throws InterruptedException {
        int sleepTime = 3100;
        marsRoverReceiver = new MarsRoverReceiver();
        mockServiceBus = mock(IMessageReceivedBus.class);
        marsRoverReceiver.writesTo(mockServiceBus);

        String[] packages = new String[]{"X2", "Y5", "DN", "M5", "2L", "3F", "4R", "5F"};
        for (String datagram : packages) {
            marsRoverReceiver.received(datagram);
        }

        Thread.sleep(sleepTime);

        verify(mockServiceBus).NotifyError();
    }
}
