package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Обработчик, выводящий сообщения в файл
 */
public class FileHandler implements MessageHandler {
    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void handle(String message) {
        try (Writer writer = new FileWriter(filePath, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}