package nl.novi.gamenight.Services;

import nl.novi.gamenight.Model.Logging;
import nl.novi.gamenight.Repository.LoggingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoggingService {
    private final LoggingRepository loggingRepository;

    public LoggingService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }


    public List<Logging> getLog() {
        List<Logging> allLogs = new ArrayList<>();
        for (Logging log : loggingRepository.findAll()) {
            allLogs.add(log);
        }
        return allLogs;
    }

}
