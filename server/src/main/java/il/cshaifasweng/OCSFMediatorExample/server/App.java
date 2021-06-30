package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.Timer;

import il.cshaifasweng.OCSFMediatorExample.helpers.ScheduledTask;

/**
 * Hello world!
 *
 */
public class App 
{
	private static SimpleServer server;
    public static void main( String[] args ) throws IOException
    {
    	Timer time = new Timer(); // Instantiate Timer Object
        ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
        time.schedule(st, 0, 60000); // Create Repetitively task for every 10 secs
        
        server = new SimpleServer(3000);
        server.connectData();
        server.listen();
    }
}
