using Source.Model;

namespace Source.Commands
{
    public interface ICommand
    {
        Position Execute(Position position);
    }
}