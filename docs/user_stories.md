
#### UserStory - 1: "create repository"
In order to maintain the applicaiton, as a developer I want to create 
a repository in github

Success Criteria:
 * repository should be downloadable / cloneable
  
#### UserStory - 2: "user interaction with the application"
In order to play with the toy robot, as an user I want to see help messages 
when I run the application and type commands

Success Criteria:
 * user should be able to run the application
 * on start application should display the commands
 * user should be able to type on console
 * application should display the user input message 
 
#### UserStory - 3: "place the robot on the table"
In order to play with the toy robot, as an user I want to see place the
robot on the table in a specific co-ordinate and direction

Success Criteria:
 * user should be able to put the robot on a specific position by
typing the command: **PLACE X, Y, F** where X = x co-ordinate, Y = y co-ordinate
and F = North / South / East / West
 * any other commands / user input should be ignored before **PLACE** command
 
#### UserStory - 4: "report the position"
In order to see the result, as an user I want the application to display robot's
position on the table at any time

Success Criteria:
 * application should display robot's current position at any time after
the robot is placed on the table when user puts **REPORT** command
 
#### UserStory - 5: "rotate the robot"
In order to rotate the robot, as an user I want the application to rotate the robot 
the robot on the table facing North, South, East or West

Success Criteria:
 * application should rotate / place the robot in the specified direction: 'North', 
'South', 'East', 'West' at any time after the robot is placed on the table when user puts 
**NORTH**, **SOUTH**, **EAST** or **WEST** command

#### UserStory - 6: "move the robot"
In order to move the robot, as an user I want the application to move 
the robot on the table 

Success Criteria:
 * application should move the robot one unit from current position 
 at any time after the robot is placed on the table when user puts **MOVE** command
 * robot should be moved in the direction of the current position (North, South, East or West)

  
#### UserStory - 7: "prevent the robot from falling off the table"
In order to prevent the robot from destruction, as an user I want the application to prevent 
move if the move cause the robot to fall off the table

Success Criteria:
 * robot should not move (i.e. X, Y position to remain unchanged) if -
   * X < 0 or X > 5
   * Y < 0 or Y > 5

 
 
 
 

  