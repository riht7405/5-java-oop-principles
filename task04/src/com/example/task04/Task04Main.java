package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = new Logger("MyLogger", Logger.Level.DEBUG);

        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new FileHandler("application.log"));
        logger.addHandler(new RotationFileHandler("rotated", ChronoUnit.HOURS));

        MemoryHandler memoryHandler = new MemoryHandler(new ConsoleHandler(), 3);
        logger.addHandler(memoryHandler);

        logger.debug("Отладочное сообщение");
        logger.info("Информационное сообщение");
        logger.warning("Предупреждение");
        logger.error("Ошибка");

        memoryHandler.flush();
    }
}