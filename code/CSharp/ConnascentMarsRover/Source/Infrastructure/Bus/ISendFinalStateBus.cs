namespace Source.Infrastructure.Bus
{
    public interface ISendFinalStateBus
    {
        void Trigger(ISendNotifications sender);
    }
}