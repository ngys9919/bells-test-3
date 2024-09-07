public class Main {
    public static void main(String[] args) {

        while (true) {
            displayMenu();
            int choice = getMenuChoice();
            boolean keepRunning = processChoice(choice);
            if (keepRunning == false) {
                break;
            }
        }
     
    }

    public static void displayMenu() {

    }

    // get the choice that the user has entered
    public static int getMenuChoice() {
        return 0;
    }

    // depending on the user has entered at the menu, do something
    // if return true, keep running the program
    // if return false, quit the program
    public static boolean processChoice(int choice) {
        return false;
    }
}