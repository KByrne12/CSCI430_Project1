import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface {
  private int currentState;
  private static WareContext context;
  private int currentUser = 0;
  private int userID;
  private WareState[] states;
  private int[][] nextState;
	
	private UserInterface() {
		states = new WareStates[4];
		states[0] = LoginState.instance();
		states[1] = ClientState.instance();
		states[2] = ClerkState.instance();
		states[3] = ManagerState.instance();
		nextState = new int[4][4];
		nextState[0][0]= -1;nextState[0][1]= 1;nextState[0][2]= 2;nextState[0][3]= 3;
		nextState[1][0]= 0;nextState[1][1]= -2;nextState[1][2]= -2;nextState[1][3]= -2;
		nextState[2][0]= 0;nextState[2][1]= 4;nextState[2][2]= -2;nextState[2][3]= -2;
		nextState[3][0]= 0;nextState[3][1]= -2;nextState[3][2]= 4;nextState[3][3]= -2;
		currentState = 0;
	}
	public void changeState(int transition) {
    currentState = nextState[currentState][transition];
    if (currentState == -2) {
		System.out.println("Error has occurred");
		terminate();
	}
    if (currentState == -1) 
		terminate();
	if (currentState == 4)
		saveUser(transition);
	if (currentState == 0)
		savedUser();
    states[currentState].run();
	}
    private void terminate() {
		System.out.println(" Goodbye \n ");
		System.exit(0);
	}
	private void saveUser (int user) {
		currentUser += user;
		currentState == user;
		//currentUser = 1, clerk as client
		//currentUser = 2, manager as clerk
		//currentUser = 3, manager as clerk as client		
	}
	private void savedUser () {
		if (currentUser == 2) {
			currentUser -= 2;
			currentState = 3;
		} else if (currentUser == 1 || 3) {
			--currentUser;
			currentState = 2;
		}
		// manager as clerk back to manager
		//(manager as) clerk as client back to clerk
	}
	public static UserInterface instance() {
		if (userInterface == null) {
			return context = new WareContext();
		} else {
			return context;
		}
	}

	public void process() {
		states[currentState].run();
	}
					
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}