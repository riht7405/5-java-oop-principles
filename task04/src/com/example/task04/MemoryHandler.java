package com.example.task04;

import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик-прокси, аккумулирующий сообщения в памяти
 */
public class MemoryHandler implements MessageHandler {
    private final MessageHandler targetHandler;
    private final int bufferSize;
    private final List<String> buffer;

    public MemoryHandler(MessageHandler targetHandler, int bufferSize) {
        this.targetHandler = targetHandler;
        this.bufferSize = bufferSize;
        this.buffer = new ArrayList<>();
    }

    @Override
    public void handle(String message) {
        buffer.add(message);

        if (buffer.size() >= bufferSize) {
            flush();
        }
    }

    /**
     * Принудительно отправляет накопленные сообщения в целевой обработчик
     */
    public void flush() {
        for (String message : buffer) {
            targetHandler.handle(message);
        }
        buffer.clear();
    }

    /**
     * @return количество сообщений в буфере
     */
    public int getBufferSize() {
        return buffer.size();
    }
}