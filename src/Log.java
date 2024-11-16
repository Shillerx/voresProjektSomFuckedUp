import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private String logEvent;

    public Log(){}
    public Log(String logEvent) {
        this.logEvent = logEvent;
    }
    public void timeStamp(String s){
       String s1 = getTime() + s;
       writeEditLogToTxt(s1);
    }
    public void writeEditLogToTxt(String log){
        String logFileName = "logs/editLog.txt";

        try(FileWriter fw = new FileWriter(logFileName, true)){
            fw.write(log + System.lineSeparator());
            
        }catch (IOException e){
            System.out.println("FilError " + e.getMessage());
        }
    }
    public void writeSystemLogToTxt(String s){
        String logFileName = "logs/systemLog.txt";

        try(FileWriter fw = new FileWriter(logFileName, true)) {
            fw.write(s + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("FilError " + e.getMessage());
        }
    }
    public void writeLoginLogToTxt(String log){
        String logFileName = "logs/loginLog.txt";

        try(FileWriter fw = new FileWriter(logFileName, true)){
            fw.write(log + System.lineSeparator());

        }catch (IOException e){
            System.out.println("FilError " + e.getMessage());
        }
    }
    public void writeRegisterLogToTxt(String log){
        String logFileName = "logs/registerLog.txt";
    }
    public String getTime() {
        String time = "";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        time = now.format(formatter);

        return time;
    }

    public void loginLog(String user){
        String string = (" Logged in as " + user);
        timeStamp(string);
    }
    public void editLog(String user){
        String string = (" Edited " + user);
        timeStamp(string);
    }
    public void editLog(String user, String event){
        String string = ("Brugeren " + user + " " + event);
    }

    public String getLogEvent() {
        return logEvent;
    }

    public void setLogEvent(String logEvent) {
        this.logEvent = logEvent;
    }
}
