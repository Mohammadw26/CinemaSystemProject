# OCSF Cinema System Project

## Description
A project build as a cinema system in which: 
1. Regular cutomers can book movies as guests or registered clients.
2. Registered clients have the option to buy tickets with reduced price.
3. Log-in panel for workers, and for every worker has different authorization level.
4. Workers according to their authorization level can modify movies/screening times/seats or observe clients feedback and also trace the company income/outcome.


## Structure
Pay attention to the three modules:
1. **client** - a simple client built using JavaFX and OCSF. We use EventBus (which implements the mediator pattern) in order to pass events between classes (in this case: between SimpleClient and PrimaryController.
2. **server** - a simple server built using OCSF.
3. **entities** - a shared module where all the entities of the project live.

## Running
1. Run Maven install **in the parent project**.
2. Run the server using the exec:java goal in the server module.
3. Run the client using the javafx:run goal in the client module.
4. Press the button and see what happens!
