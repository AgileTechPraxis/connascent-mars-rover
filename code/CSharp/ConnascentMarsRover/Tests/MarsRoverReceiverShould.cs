using System.Collections;
using System.Threading;
using NSubstitute;
using NUnit.Framework;
using Source.Infrastructure.Bus;
using Source.Infrastructure.SpaceComm;

namespace Tests
{
    [TestFixture]
    public class MarsRoverReceiverShould
    {
        private MarsRoverReceiver _marsRoverReceiver;

        private class MarsRoverReceiverTestCases
        {
            public static IEnumerable TestCases
            {
                get
                {
                    yield return new TestCaseData(
                        new[] {"X2", "Y5", "DN", "M5", "1F", "2L", "3F", "4R", "5F"},
                        "100 100\n2 5 N\nFLFRF");

                    yield return new TestCaseData(
                        new[] {"X2", "Y5", "DN", "M4", "3F", "2L", "1F", "4R"},
                        "100 100\n2 5 N\nFLFR");
                }
            }
        }

        [TestCaseSource(typeof(MarsRoverReceiverTestCases), nameof(MarsRoverReceiverTestCases.TestCases))]
        public void RebuildMessageCorrectly(string[] packets, string rebuiltMessage)
        {
            const int maxDelay = 100;
            _marsRoverReceiver = new MarsRoverReceiver(maxDelay);
            var mockServiceBus = Substitute.For<IMessageReceivedBus>();
            _marsRoverReceiver.WritesTo(mockServiceBus);

            foreach (var packet in packets)
            {
                _marsRoverReceiver.Received(packet);
            }

            mockServiceBus.Received().NotifyMessageReceived(rebuiltMessage);
        }

        [Test]
        public void NotifyErrorCorrectly()
        {
            const int maxDelay = 100;
            _marsRoverReceiver = new MarsRoverReceiver(maxDelay);
            var mockServiceBus = Substitute.For<IMessageReceivedBus>();
            _marsRoverReceiver.WritesTo(mockServiceBus);

            var packets = new[] {"X2", "Y5", "DN", "M5", "2L", "3F", "4R", "5F"};
            foreach (var packet in packets)
            {
                _marsRoverReceiver.Received(packet);
            }

            Thread.Sleep(maxDelay + 30);

            mockServiceBus.Received().NotifyError();
        }
    }
}