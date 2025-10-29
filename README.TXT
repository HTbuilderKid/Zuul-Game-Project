
1.1. The Zuul-Bad program basically does the following things:
- Welcomes the player
- Describes where they currently are (in which room)
- Waits for typed commands, like: "go south" or "help"
- Moves the player between rooms if there is one
- Ends the game when the player types "quit"

1.2 & 1.3. The game takes 3 commands: "help", "go" and "quit". 
- help prints a short help message and lists the available commands
- go (we need to follow up with the direction) moves the player to another room following the direction. If there is no exit in said direction, it will print "There is no door!"
- quit ends the game if typed alone, but if we type anything after it, such as "quit the game", it prints "Quit what?" and does nothing.

1.4. There are 5 rooms created in the createRooms() method: 
- outside
- theater
- pub
- lab
- office

1.5. Based on their descriptions, I think the map would look something like this: 
        [theater]
           |
           |
[pub]---[outside]---[lab]---[office]

2. The classes in the project does the following things:
- Game creates all the rooms and connects them together, it starts and runs the game's loop, handles the inputs and the logic, and keeps track of which room the player is currently in
- Command is basically a user command. It has the command word and the second word that comes after it ("go", "south") and it also has the helper methods to check if the command is valid or needs a second word
- Room is a location in the game. It has a text description of the area, links to the rooms next to it, and is used to navigate between different rooms
- Parser is the thing that handles the input. It reads the line from the terminal we input it into, splits it into words, creates a Command object based on what we typed, and checks if the first word is valid by using the CommandWords class
- CommandWords is the storage for the list of valid words. It checks if a given word is one in the list, and helps the Parser to validate out input.






