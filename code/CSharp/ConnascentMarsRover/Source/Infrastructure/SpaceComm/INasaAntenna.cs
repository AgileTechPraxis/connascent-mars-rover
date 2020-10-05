namespace Source.Infrastructure.SpaceComm
{
    public interface INasaAntenna
    {
        void Received(string[] datagrams);
    }
}