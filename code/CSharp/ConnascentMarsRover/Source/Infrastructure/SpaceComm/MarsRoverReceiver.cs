using System.Collections.Generic;
using Source.Infrastructure.Bus;
using Source.Infrastructure.Timing;

namespace Source.Infrastructure.SpaceComm
{
    public class MarsRoverReceiver : IWriteToServiceBus
    {
        private const int MaxDelayMilliseconds = 3000;
        private IMessageReceivedBus _marsRoverServiceBus;
        private readonly List<string> _packets = new List<string>();
        private readonly ISmartTimer _smartTimer = new SmartTimer();

        public void Received(string packet)
        {
            _packets.Add(packet);
            var message = new Message(_packets);
            if (message.IsValid())
            {
                _marsRoverServiceBus.NotifyMessageReceived(message.ToString());
                return;
            }

            _smartTimer
                .WaitMilliseconds(MaxDelayMilliseconds)
                .BeforeDoing(NotifyMessage, _packets);
        }

        public void WritesTo(IMessageReceivedBus messageReceivedBus)
        {
            _marsRoverServiceBus = messageReceivedBus;
        }

        private void NotifyMessage(List<string> packets)
        {
            var message = new Message(packets);
            if (message.IsValid())
            {
                _marsRoverServiceBus.NotifyMessageReceived(message.ToString());
                return;
            }

            _marsRoverServiceBus.NotifyError();
        }
    }
}