# Application: robot-movement-simulator
Sample application for demonstrating basic 2D robot movement

# Problem statement:
## Short description
Build a sample application to demonstrate a toy robot movement based on user commands ( console input or from a file).
Robot will be placed on a tabletop and based on user commands it should move 1 unit at a time in the specified direction (north, south, east or west).
Robot movement should be prevented to fall off the table.
## Detailed description:  
See [PROBLEM.md](PROBLEM.md) file for detailed description

# Development Approach
 * requirements will be broken down into user stories / features 
 * github will be used
 * each feature should be maintained in a brach (branches won't be removed for now)
 * after completion of a feature / user story feature branch will be merged into master branch 
 * there should be a deployable / executable application after completion and running corresponding tests  
 
## Features / User stories: see in [docs/user_stories](docs/user_stories.md)

# Solution
 * SpringBoot framework is used for this solution which provides easy way to build highly configurable, deployable and maintainable 
production quality application. This application is a command line application which take user inputs and places the
robot on the tabletop, moves / rotates it, shows the result, logs information and exits based on user inputs. 
This application can easily be extended to run as a webb application or run as a API, used from other applications, security 
can be applied, store results in database etc. as spring provides all these features. 
The flip side is the size of the deliverable codebase / package which can be a little bit bigger considering the requirements 
of the application. But above well proven production ready features of Spring framework outweighs these trade-offs.
 * Command pattern has been applied to execute the commands. Implementation of commands uses java 8 functional interfaces
 * Main class is "Appllication.java". It uses "RobotController.run()" to interact and execute user commands   
 * Dimension of the simulated table is configurable by setting below properties in application.properties config file:
  
  `application.properties`
  ```
  dimension.min.x=0
  dimension.max.x=5
  dimension.min.y=0
  dimension.max.y=5
  ```
 * application uses console to interact with user. 
 
# Environment
 * JAVA - 1.8.x (development env version 1.8.0_102)
 * Gradle - 3.3
 * Developed on MacOS (OS X EI Capitan - 10.11.6)
 
# **How to run the application**  
## Get from GIT
```
   git clone https://github.com/mrajibkhan/robot-movement-simulator
   cd robot-movement-simulator
   git checkout master
```
## Run Tests
```
./gradlew clean test
```
## Build
```
./gradlew clean build
```
this creates executable jar file: ./build/libs/robot-movement-simulator-0.0.1-SNAPSHOT.jar

## Run the application
```
java -jar ./build/libs/robot-movement-simulator-0.0.1-SNAPSHOT.jar
```
### Commands
```
 PLACE X,Y,F
 MOVE
 LEFT
 RIGHT
 REPORT
 QUIT
```
`sample run (console)`
```
$ java -jar ./build/libs/robot-movement-simulator-0.0.1-SNAPSHOT.jar
           _           _
          | |         | |
 _ __ ___ | |__   ___ | |_
| '__/ _ \| '_ \ / _ \| __|
| | | (_) | |_) | (_) | |_
|_|  \___/|_.__/ \___/ \__|


Welcome to Robot Simulation
Please enter following options to play with the robot 
1. PLACE X,Y,F (e.g. 0,0,NORTH)
2. MOVE 
3. LEFT
4. RIGHT 
5. REPORT
6. QUIT
>>    
place 0,0,north
You entered: PLACE 0, 0, NORTH
Robot is placed on the table. Position: 0, 0, NORTH
move
You entered: MOVE
report
You entered: REPORT
Robot position: 0, 1, NORTH
quit
Exiting simulator!

```

 


  
