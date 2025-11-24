package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
    private String name;
    private Level level;
    private static ArrayList<Logger> loggers = new ArrayList<Logger>();
    public Logger(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static Logger getLogger(String name){
        for (int i= 0; i< loggers.size();i++){
            if(loggers.get(i).getName().equals(name)){
                return loggers.get(i);
            }
        }
        Logger logger = new Logger(name);
        loggers.add(logger);
        return logger;
    }
    public void setLevel(Level level) {
        this.level = level;
    }
    public Level getLevel() {
        return level;
    }
    private String formatMessage(Level level, String Message){
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

        return String.format("[%s] %s  %s - %s", level.name(), date, this.name, Message);
    }
    public void log(Level level, String message){

        if(level.compareTo(this.level) >= 0){
            System.out.println(formatMessage(level, message));
        }
    }
    public void log(Level level, String format, Object... args){
        if(level.compareTo(this.level) >= 0){
            System.out.println(formatMessage(level, String.format(format, args)));
        }
    }
    public void debug(String message){
        log(Level.DEBUG, message);
    }
    public void debug(String format, Object... args){
        log(Level.DEBUG,format,args);
    }
    public void info(String message){
        log(Level.INFO, message);
    }
    public void info(String format, Object... args){
        log(Level.INFO, format, args);
    }
    public void warning(String message){
        log(Level.WARNING, message);
    }
    public void warning(String format, Object... args){
        log(Level.WARNING, format, args);
    }
    public void error(String message){
        log(Level.ERROR, message);
    }
    public void error(String format, Object... args){
        log(Level.ERROR, format, args);
    }
}