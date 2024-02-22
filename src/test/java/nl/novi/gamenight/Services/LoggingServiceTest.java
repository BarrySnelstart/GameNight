package nl.novi.gamenight.Services;

import nl.novi.gamenight.Model.Logging;
import nl.novi.gamenight.Repository.LoggingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;



class LoggingServiceTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    LoggingRepository loggingRepository;

    @InjectMocks
    LoggingService loggingService;
    @Test
    void getLog() {
        List<Logging> expectedLogs = new ArrayList<>();
        Logging log1 = new Logging();
        Logging log2 = new Logging();
        expectedLogs.add(log1);
        expectedLogs.add(log2);

        when(loggingRepository.findAll()).thenReturn(expectedLogs);

        var logdata = loggingService.getLog();

        assertEquals(2, logdata.size());
    }
}