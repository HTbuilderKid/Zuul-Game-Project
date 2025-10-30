/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrance, hall, armory, library, prison, treasury, bossRoom;
      
        // create the rooms
        entrance = new Room("at the dungeon entrance. A cold wind blows from within.");
        hall = new Room("in the grand hall, torches lighting up the stone walls.");
        armory = new Room("in the armory, filled with rusty swords and broken shields.");
        library = new Room("in a dark library. Dusty tomes seem to be whispering to you.");
        prison = new Room("in a prison chamber. You see eyes glowing within nearby cells.");
        treasury = new Room("in the treasure vault, filled with mountains of gold and jewels.");
        bossRoom = new Room("in the throne of the Dungeon Lord! His massive shadow looms over you.");
        
        // initialise room exits
        entrance.setExit("east", hall);
        hall.setExit("west", entrance);
        hall.setExit("east", armory);
        hall.setExit("south", prison);
        armory.setExit("west", hall);
        armory.setExit("north", library);
        library.setExit("south", armory);
        prison.setExit("north", hall);
        prison.setExit("east", treasury);
        treasury.setExit("west", prison);
        treasury.setExit("east", bossRoom);
        bossRoom.setExit("west", treasury); 
        
        // and now we can add some random exits to the original dungeon that
        // will break the map! Yay!
        
        prison.setExit("down", new Room("in a dark catacomb filled with dead bones."));
        armory.setExit("up", new Room("on a spiral staircase that leads down to the abyss..."));

        // start game outside
        currentRoom = entrance;  
    }
    
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Dungeon of Doom!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }
    
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            // signal that we want to quit
            return true;  
        }
    }
}
