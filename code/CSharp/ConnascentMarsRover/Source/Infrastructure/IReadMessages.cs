namespace Source.Infrastructure
{
    public interface IReadMessages
    {
        void CallBack(IProcessMessages messageProcessor);
    }
}