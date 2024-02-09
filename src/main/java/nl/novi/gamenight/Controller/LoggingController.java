package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Model.Logging;
import nl.novi.gamenight.Services.LoggingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("log")
public class LoggingController {

    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    LoggingService loggingService;

    @GetMapping("getlog")
    public List<Logging> getLog() {
        return loggingService.getLog();
    }

}
