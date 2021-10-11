using Source.Infrastructure;
using Source.Infrastructure.Bus;
using Source.Infrastructure.SpaceComm;

namespace Source
{
    public class MarsRover
    {
        private readonly ServiceBus _marsRoverBus;
        private readonly MarsRoverReceiver _marsRoverReceiver;
        private readonly ISendNotifications _marsRoverSender;
        private readonly MarsRoverController _marsRoverController;

        public MarsRover(
            ServiceBus marsRoverBus,
            MarsRoverReceiver marsRoverReceiver,
            ISendNotifications marsRoverSender,
            MarsRoverController marsRoverController)
        {
            _marsRoverBus = marsRoverBus;
            _marsRoverReceiver = marsRoverReceiver;
            _marsRoverSender = marsRoverSender;
            _marsRoverController = marsRoverController;


            _marsRoverReceiver.WritesTo(_marsRoverBus);

            _marsRoverController.ReadsFrom(_marsRoverBus);
            _marsRoverController.WritesTo(_marsRoverBus);

            _marsRoverSender.ReadsFrom(this._marsRoverBus);
        }
    }
}