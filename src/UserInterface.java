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
                //continue;
            } else if(input.equalsIgnoreCase("All")) {
                printAll(database);
            } else if(input.equalsIgnoreCase("Observation")) {
                addObservation();
            } else if(input.equalsIgnoreCase("One")) {
                getSingleBirdInfo();
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

    public void addObservation() {
        while(true) {
            System.out.print("Bird? ");
            String birdName = scanner.nextLine();
            if(birdName.isEmpty()) {
                System.out.println("Please enter a name!");
                continue;
            } else{
                if(checkIfNameInDatabase(database,birdName)) {
                    Bird observedBird = database.get(getIndexOfSpecificBird(birdName));
                    observedBird.observed();
                    break;
                } else {
                    System.out.println("Not a bird!");
                    break;
                }
            }
        }
    }

    public void getSingleBirdInfo() {
        while(true) {
            System.out.print("Bird? ");
            String birdName = scanner.nextLine();
            if(birdName.isEmpty()) {
                System.out.println("Please enter a name!");
                continue;
            } else {
                if(checkIfNameInDatabase(database, birdName)) {
                    Bird selectedBird = database.get(getIndexOfSpecificBird(birdName));
                    System.out.print(selectedBird + "\n");

                    break;
                    // Not in DB
                } else {
                    System.out.println("Bird Not Found in the Database!");
                    break;
                }
            }
        }
    }

    public int getIndexOfSpecificBird(String birdName) {
        int index = 0;
        for(int i=0; i<database.size();i++) {
            if(database.get(i).getName().equalsIgnoreCase(birdName)) {
                index = i;
            }
        }
        return index;
    }


}
