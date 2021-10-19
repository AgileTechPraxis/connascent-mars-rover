using System.Linq;
using System.Threading;
using NUnit.Framework;
using Source;
using NSubstitute;
using Source.Infrastructure;
using Source.Infrastructure.Bus;
using Source.Infrastructure.SpaceComm;

namespace Tests
{
    public class ConnascentMarsRoverScenarios
    {
        private MarsRoverReceiver _marsRoverReceiver;
        private ISendNotifications _marsRoverSender;
        private INasaAntenna _nasaAntenna;
        private MarsRover _marsRover;

        [SetUp]
        public void Setup()
        {
            _nasaAntenna = Substitute.For<INasaAntenna>();
            _marsRoverReceiver = new MarsRoverReceiver();
            _marsRoverSender = new MarsRoverSender(_nasaAntenna);
            var marsRoverController = new MarsRoverController();
            var marsRoverBus = new ServiceBus();
            _marsRover = new MarsRover(marsRoverBus, _marsRoverReceiver, _marsRoverSender, marsRoverController);
        }

        [Test]
        public void Move_following_commands()
        {
            string[] inputPackages = {"X2", "Y5", "DN", "M5", "1F", "2L", "3F", "4R", "5F"};
            foreach (var inputPackage in inputPackages)
            {
                _marsRoverReceiver.Received(inputPackage);
            }

            _nasaAntenna.Received()
                .Received(
                    Arg.Is<string[]>(datagrams =>
                        datagrams.SequenceEqual(new[] {"X1", "Y7", "DN"})));
        }

        [Test]
        public void Move_following_commands_any_order()
        {
            string[] inputPackages = {"DN", "M5", "X2", "Y5", "3F", "4R", "1F", "2L", "5F"};
            foreach (var inputPackage in inputPackages)
            {
                _marsRoverReceiver.Received(inputPackage);
            }

            _nasaAntenna.Received()
                .Received(
                    Arg.Is<string[]>(datagrams =>
                        datagrams.SequenceEqual(new[] {"X1", "Y7", "DN"})));
        }

        [Test]
        public void Move_following_commands_incomplete()
        {
            string[] inputPackages =
            {
                "DN", "M5", "X2", "Y5", "3F", "1F", "2L", "5F"
            };
            foreach (var inputPackage in inputPackages)
            {
                _marsRoverReceiver.Received(inputPackage);
            }

            Thread.Sleep(3100);
            _nasaAntenna.Received()
                .Received(
                    Arg.Is<string[]>(datagrams =>
                        datagrams.SequenceEqual(new[] {"ER"})));
        }
    }
}