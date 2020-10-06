using Source.Model;

namespace Source.Commands
{
    public class TurnRightCommand : ICommand
    {
        protected bool Equals(TurnRightCommand other)
        {
            return true;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((TurnRightCommand) obj);
        }

        public Position Execute(Position position)
        {
            return position.TurnRight();
        }
    }
}