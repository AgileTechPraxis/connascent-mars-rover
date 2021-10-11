using Source.App;
using Source.Infrastructure.Bus;
using Source.Model;

namespace Source.Infrastructure
{
    public class MarsRoverController : IProcessMessages
    {
        private readonly MarsRoverEngine _marsRoverEngine;
        private readonly CommandInterpreter _commandInterpreter;
        private ISendNotificationBus _marsRoverServiceWriter;

        public MarsRoverController()
        {
            _marsRoverEngine = new MarsRoverEngine();
            _commandInterpreter = new CommandInterpreter();
        }

        public void WritesTo(ISendNotificationBus marsRoverServiceBus)
        {
            _marsRoverServiceWriter = marsRoverServiceBus;
        }

        public void ReadsFrom(IReadMessages marsRoverServiceBus)
        {
            marsRoverServiceBus.CallBack(this);
        }

        public void Process(string messageReceived)
        {
            var commands = _commandInterpreter.Translate(messageReceived);
            _marsRoverEngine.Execute(commands);
            var finalPosition = _marsRoverEngine.GetPosition();
            _marsRoverServiceWriter.NotifyExecution(finalPosition.ToString());
        }
    }
}