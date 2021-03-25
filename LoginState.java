import java.util.*;
import java.text.*;
import java.io.*;

public class LoginState extends WareState {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static LoginState instance;
	private static final int LOGOUT = 0;
	private static final int CLIENT = 1;
	private static final int CLERK = 2;
	private static final int MANAGER = 3;
	
	private LoginState() {
		super();
	}
	
	public static LoginState instance() {
		if (instance == null) {
			instance = new LoginState();
		}
		return instance;
	}
	
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
  }
  
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

	public void help() {
		System.out.println("Enter a number between 0 and 3 as explained below:");
		System.out.println(LOGOUT + " to Logout\n");
		System.out.println(CLIENT + " to login as a client.");
		System.out.println(CLERK + " to login as a clerk.");
		System.out.println(MANAGER + " to login as a manager.");
	}
	
	public void client() {
		System.out.println("Client transition dummy function");
	}
	
	public void clerk() {
		System.out.println("Clerk transition dummy function");
	}
	
	public void manager() {
		System.out.println("Manager transition dummy function");
	}
	
	public void logout() {
		System.out.println("Logout transition dummy function");
	}
	
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != LOGOUT){
			switch (command) {
				case CLIENT:
					client();
					break;
				case CLERK:
					clerk();
					break;
				case MANAGER:
					manager();
					break;
				default:
					System.out.println("Not a valid command");
			}
		}
		logout();
	}
	
	public void run() {
		process();
	}
}