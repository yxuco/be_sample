ps aux | grep be-engine | grep BETestLib_PU | awk '{print $2}' | xargs kill
