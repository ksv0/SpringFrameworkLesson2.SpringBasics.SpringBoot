package ru.gb.my_first_crud.service;

import org.springframework.stereotype.Service;
import ru.gb.my_first_crud.model.LogEntry;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    private List<LogEntry> logs = new ArrayList<>();

    public void addLog(String timestamp,String logMessage) {
        logs.add(new LogEntry(timestamp,logMessage));
    }

    public List<LogEntry> getAllLogs() {
        return logs;
    }
}