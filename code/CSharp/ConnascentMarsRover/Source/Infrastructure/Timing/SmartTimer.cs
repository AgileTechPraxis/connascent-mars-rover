using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Threading;
using System.Threading.Tasks;

namespace Source.Infrastructure.Timing
{
    internal class SmartTimer : ISmartTimer
    {
        private int _millisecondsToWait;
        private Task _task;
        private CancellationToken _cancellationToken = CancellationToken.None;

        public ISmartTimer WaitMilliseconds(int milliseconds)
        {
            Reset();
            _millisecondsToWait = milliseconds;
            return this;
        }

        public ISmartTimer BeforeDoing(
            Action<List<string>> notifyMessage, List<string> packets)
        {
            _task = Task.Factory.StartNew(() =>
            {
                notifyMessage(packets);
                Reset();
            });

            _task.Wait(_millisecondsToWait, _cancellationToken);
            _cancellationToken = CancellationToken.None;
            return this;
        }

        private void Reset()
        {
            _cancellationToken = new CancellationToken();
        }
    }
}