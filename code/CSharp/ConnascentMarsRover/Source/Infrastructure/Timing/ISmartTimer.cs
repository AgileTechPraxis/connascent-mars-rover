using System;
using System.Collections.Generic;

namespace Source.Infrastructure.Timing
{
    internal interface ISmartTimer
    {
        ISmartTimer WaitMilliseconds(int milliseconds);
        ISmartTimer BeforeDoing(Action<List<string>> notifyMessage, List<string> packets);
    }
}