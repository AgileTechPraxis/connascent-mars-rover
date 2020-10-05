using Source.Model;

namespace Source.Commands
{
    public class InitialisationCommand : ICommand
    {
        private readonly Coordinate _coordinate;

        public InitialisationCommand(Coordinate coordinate)
        {
            _coordinate = coordinate;
        }

        private bool Equals(InitialisationCommand other)
        {
            return Equals(_coordinate, other._coordinate);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((InitialisationCommand) obj);
        }

        public override int GetHashCode()
        {
            return (_coordinate != null ? _coordinate.GetHashCode() : 0);
        }
    }
}