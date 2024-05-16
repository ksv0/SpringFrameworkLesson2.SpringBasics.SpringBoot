package ru.gb.my_first_crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogEntry {
    private  String timestamp;
    private  String message;
    }

