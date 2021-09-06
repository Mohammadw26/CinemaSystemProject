# OCSF Cinema System Project

-NOTE: some photos are provided below to visualize aspects of our project.we would appreciate your time in taking a look at them.


## Description
A project built as a cinema system in which: 
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

## Diving into the project
1. First screen:

    ![Screenshot (54)](https://user-images.githubusercontent.com/82311533/132223681-27c9e93d-5c20-44ca-b79c-6f4992ace9a7.png)
    
    
2. Sign-up screen: 

  ![Screenshot (61)](https://user-images.githubusercontent.com/82311533/132223943-893730a4-cac1-4011-aa34-64b5fcb445c4.png)


3. Sign-in screen:
   a user can sign in as a client or as a worker with different authorities.
   client user and pass: client1 - CinemaMember1 password: wa7wa7
                         client2 - CinemaMember1 password: wa7wa7
   workers users and pass:general manager - user: GeneralManager password: wa7wa7
                          content manager - user: ContentManager password: wa7wa7
                          branch manager - user: BranchManager password: wa7wa7
                          customer service - user: CustomerService password: wa7wa7
                          
   ![Screenshot (56)](https://user-images.githubusercontent.com/82311533/132224536-d2f86d8c-7e31-4f9e-9b2b-8fb12f5c9fe4.png)
   
   
4. Movies catalog screen (it is divided into movies available in branches, on demand and coming soon movies).
   It can be viewed as a guest, client and a worker:

   ![Screenshot (57)](https://user-images.githubusercontent.com/82311533/132224747-39b281f5-4b03-4de5-9e16-16e3218d7099.png)
   
   
5.Booking and choosing seats screens:
  
  ![Screenshot (58)](https://user-images.githubusercontent.com/82311533/132225099-3c624f1a-416f-40f4-9cb1-dacdc7fdb487.png)
  
  ![Screenshot (59)](https://user-images.githubusercontent.com/82311533/132225322-bc007f96-6d26-43e2-8675-c31b46020c3a.png)
  
  
6. Modifying movie screenings:

   ![Screenshot (60)](https://user-images.githubusercontent.com/82311533/132225471-2e301362-ad80-43db-b7ee-93254c980ae7.png)
   
   
   
   
   
   **** our project has a lot of things that aren't listed above ! Please take your time exploring and knowing more about our project, we would appreciate it ! ****




                          
