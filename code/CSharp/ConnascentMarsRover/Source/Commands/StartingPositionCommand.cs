using Source.Model;

namespace Source.Commands
{
    public class StartingPositionCommand : ICommand
    {
        private readonly Position _position;

        public StartingPositionCommand(Position position)
        {
            _position = position;
        }

        protected bool Equals(StartingPositionCommand other)
        {
            return Equals(_position, other._position);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((StartingPositionCommand) obj);
        }

        public override int GetHashCode()
        {
            return (_position != null ? _position.GetHashCode() : 0);
        }
    }
}