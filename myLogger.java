import java.io.*;

public class myLogger {
    private static final String logFileName = "ChangesLog.txt";
    private static BufferedWriter logWriter;

    public static void logInfo(String message){
        log("INFO" , message);
    }

    public static void logWarning(String message){
        log("WARNING" , message);
    }

    public static void logError(String message){
        log("ERROR" , message);
    }

    public static void log(String type , String message){
        String logMessage = "[ " + type + " ] : " + message;

        try{
            if(logWriter == null){
                logWriter = new BufferedWriter(new FileWriter(logFileName, false));
            }
            logWriter.write(logMessage);
            logWriter.newLine();
            logWriter.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void close(){
        try{
            if(logWriter != null){
                logWriter.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
