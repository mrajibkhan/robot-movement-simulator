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
SpringBoot framework is used for this solution which provides easy way to build highly configurable, deployable and maintainable 
production quality application. This application is a command line application which take user inputs and places the
robot on the tabletop, moves / rotates it, shows the result, logs information and exits based on user inputs. 
But this application can easily be extended to run as a webb application or run as a API, used from other applications, add security 
can be applied etc. as spring provides all these features. The flip side is the size of the deliverable codebase / package which can be 
quite large considering the requirements of the application.
  
# Environment
 * JAVA - 1.8.x (development env version 1.8.0_102)
 * Gradle - 3.3
 * Developed on MacOS (OS X EI Capitan - 10.11.6)
  
