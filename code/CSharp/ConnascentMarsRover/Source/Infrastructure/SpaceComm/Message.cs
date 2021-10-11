using System.Collections.Generic;

namespace Source.Infrastructure.SpaceComm
{
    public class Message
    {
        private string _x;
        private string _y;
        private int _commandsCount;
        private string _direction;
        private readonly string _positionMessage;
        private readonly string _commandsMessage;

        public Message(List<string> datagrams)
        {
            _positionMessage = ParsePosition(datagrams);
            _commandsMessage = ParseCommands(datagrams);
        }

        private string ParsePosition(List<string> datagrams)
        {
            foreach (var datagram in datagrams)
            {
                if (datagram.StartsWith("X"))
                    _x = datagram.Substring(1);
                if (datagram.StartsWith("Y"))
                    _y = datagram.Substring(1);
                if (datagram.StartsWith("D"))
                    _direction = datagram.Substring(1);
                if (datagram.StartsWith("M"))
                {
                    _commandsCount = int.Parse(datagram.Substring(1));
                }
            }

            return $"100 100\n{_x} {_y} {_direction}\n";
        }

        public override string ToString()
        {
            return $"{_positionMessage}{_commandsMessage}";
        }

        public bool IsValid()
        {
            return _x != null &&
                   _y != null &&
                   _direction != null &&
                   _commandsCount > 0 &&
                   _commandsMessage.Length == _commandsCount;
        }

        string ParseCommands(List<string> datagrams)
        {
            var commandsMessage = "";
            for (int commandNumber = 1; commandNumber <= _commandsCount; commandNumber++)
            {
                foreach (var datagram in datagrams)
                {
                    if (datagram.StartsWith(commandNumber.ToString()))
                    {
                        commandsMessage += datagram.Substring(1);
                    }
                }
            }

            return commandsMessage;
        }
    }
}