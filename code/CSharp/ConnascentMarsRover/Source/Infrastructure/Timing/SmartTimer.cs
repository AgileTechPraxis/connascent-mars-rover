using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace Source.Infrastructure.Timing
{
    internal class SmartTimer : ISmartTimer
    {
        private int _millisecondsToWait;
        private Task _task;
        readonly CancellationTokenSource _tokenSource = new CancellationTokenSource();
        CancellationToken _cancellationToken;

        public ISmartTimer WaitMilliseconds(int milliseconds)
        {
            Reset();
            _millisecondsToWait = milliseconds;
            return this;
        }

        public ISmartTimer BeforeDoing(
            Action<List<string>> notifyMessage, List<string> packets)
        {
            _cancellationToken = _tokenSource.Token;
            _task = Task.Factory.StartNew(() =>
            {
                Task.Delay(_millisecondsToWait, _cancellationToken);
                notifyMessage(packets);
                Reset();
            }, _cancellationToken);
            
            return this;
        }

        private void Reset()
        {
            _tokenSource.Cancel();
        }
    }
}