import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private ArrayList<Bird> database;
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.database = new ArrayList<>();
    }

    public void startUI() {
        while(true) {
            System.out.print("? ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("Quit")) {
                break;
            } else if(input.equalsIgnoreCase("Add")) {
                addBirdName();
                continue;
            } else if(input.equalsIgnoreCase("All")) {
                printAll(database);
            } else {
                System.out.println("Command Not Found! Try Again!");
                continue;
            }
        }
    }

    public void addBirdName() {
        while(true) {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            if(name.isEmpty()) {
                System.out.println("Invalid Input. Please try again!");
                continue;
            } else {
                // Name already exists in the database
                if(checkIfNameInDatabase(database,name)) {
                    System.out.println("Bird Already Exists in the Database!");
                    break;
                } else {
                    addBird(name);
                    break;
                }
            }
        }
    }

    public void addBird(String name) {
        while(true) {
            System.out.print("Name in Latin: ");
            String latinName = scanner.nextLine();
            if(latinName.isEmpty()) {
                System.out.println("Please enter a name!");
                continue;
            } else {
                Bird newbird = new Bird(name, latinName);
                database.add(newbird);
                break;
            }
        }
    }

    public boolean checkIfNameInDatabase(ArrayList<Bird> birds, String name) {
        for(Bird bird : birds) {
            if(name.equalsIgnoreCase(bird.getName())) {
                return true;
            }
        }
        return false;
    }

    public void printAll(ArrayList<Bird> database) {
        if(database.isEmpty()) {
            System.out.println("No Birds in Database! Try Adding Some!");
        } else {
            for(Bird bird : database) {
                System.out.println(bird);
            }
        }
    }


}
