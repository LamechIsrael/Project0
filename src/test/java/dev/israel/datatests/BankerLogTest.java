package dev.israel.datatests;

import dev.israel.utilities.BankerLogger;
import dev.israel.utilities.BankerLoggerLevel;
import org.junit.jupiter.api.Test;

public class BankerLogTest {

    @Test
    void info_banklog_tests(){
        BankerLogger.bankLogInfo("Hello.", BankerLoggerLevel.INFO);
    }
}
