using Source.Infrastructure.Bus;

namespace Source.Infrastructure.SpaceComm
{
    public class MarsRoverReceiver : IWriteToServiceBus
    {
        public MarsRoverReceiver(in int maxDelay)
        {
            throw new System.NotImplementedException();
        }

        public void Received(string packet)
        {
            throw new System.NotImplementedException();
        }

        public void WritesTo(IMessageReceivedBus messageReceivedBus)
        {
            throw new System.NotImplementedException();
        }
    }
}