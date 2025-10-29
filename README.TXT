
1. The Zuul-Bad program basically does the following things:
- Welcomes the player
- Describes where they currently are (in which room)
- Waits for typed commands, like: "go south" or "help"
- Moves the player between rooms if there is one
- Ends the game when the player types "quit"

2 & 3. The game takes 3 commands: "help", "go" and "quit". 
- help prints a short help message and lists the available commands
- go (we need to follow up with the direction) moves the player to another room following the direction. If there is no exit in said direction, it will print "There is no door!"
- quit ends the game if typed alone, but if we type anything after it, such as "quit the game", it prints "Quit what?" and does nothing.

4. There are 5 rooms created in the createRooms() method: 
- outside
- theater
- pub
- lab
- office

5. Based on their descriptions, I think the map would look something like this: 
        [theater]
           |
           |
[pub]---[outside]---[lab]---[office]



