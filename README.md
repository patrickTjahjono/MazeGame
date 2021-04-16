# Testing, Building and Running the Game
**General Steps**

- open command prompt
- go to the MazeGame directory

**Test:**

- run `mvn test`

**Test and Build:**

- run `mvn package`

**Run:**

- run `java -cp target/MazeGame-1.0-SNAPSHOT.jar Main`

# Game Information
**Game controls:**

- use arrow keys to direct Player(the axe) movement
- press ESC key to pause
- press the close window button to terminate

**Winning and Losing Conditions**

- Win Conditions: collect all regular rewards (coins) and reach the end point(orange)
- Lose Conditions: score goes below zero by receiving from punishments(spikes) or caught by enemy(ghost)
