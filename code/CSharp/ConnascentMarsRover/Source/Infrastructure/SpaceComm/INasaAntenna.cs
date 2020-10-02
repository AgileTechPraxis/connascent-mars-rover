namespace Source
{
    public interface INasaAntenna
    {
        void Received(string[] datagrams);
    }
}