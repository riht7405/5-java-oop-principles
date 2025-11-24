package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Продвинутый логгер с поддержкой multiple handlers
 */
public class Logger {
    private final String name;
    private final List<MessageHandler> handlers;
    private Level level;

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public Logger(String name) {
        this.name = name;
        this.handlers = new ArrayList<>();
        this.level = Level.DEBUG;
    }

    public Logger(String name, Level level) {
        this(name);
        this.level = level;
    }

    /**
     * Добавляет обработчик сообщений
     * @param handler обработчик для добавления
     */
    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    /**
     * Устанавливает уровень логирования
     * @param level уровень логирования
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return уровень логирования
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return имя логгера
     */
    public String getName() {
        return name;
    }

    private void log(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }

        String formattedMessage = String.format("[%s] %s %s - %s",
                level,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                name,
                message);

        for (MessageHandler handler : handlers) {
            handler.handle(formattedMessage);
        }
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }
}