using System;
using Source.Infrastructure.Bus;

namespace Source.Infrastructure.SpaceComm
{
    public class MarsRoverSender : ISendNotifications
    {
        private readonly INasaAntenna _nasaAntenna;

        public MarsRoverSender(INasaAntenna nasaAntenna)
        {
            _nasaAntenna = nasaAntenna;
        }

        public void Send(string message)
        {
            var messageParts = message.Split(" ");
            _nasaAntenna.Received(new[]
            {
                $"X{messageParts[0]}",
                $"Y{messageParts[1]}",
                $"D{messageParts[2]}"
            });
        }

        public void ReadsFrom(ServiceBus marsRoverServiceBus)
        {
            marsRoverServiceBus.Trigger(this);
        }

        public void SendError()
        {
            _nasaAntenna.Received(new[] {"ER"});
        }
    }
}