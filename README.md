#Movie Theater Manager

####Short description
Application allows to manage creating Events in different Auditoriums.
You can:
* create Event
* create Auditoriums for Event
* create a pool of seats and VIP seats in chosen Auditorium
* book a set of tickets
* for sure you you can rate a movie
* prolong your Event for multiple days and etc. 

As a manager of Movie Theater Manager you can manipulate all this data.
For sure, all operations are verified and validated when they occur.  

As a user you can add new Event and setup the following details:
* set a list of dates (airDates) with Auditoriums for planning Events 
* set a rating for this Event: high, medium, low
* base price for a ticket

Also you have the opportunity to manipulate Auditoriums by adding them with setting:
* name
* number of seats
* VIP seats

Each potential User with name, email and id can book a ticket on Event using BookingService with following API:
* remove
* getUserByEmail
* save

We have some additional service DiscountService for getting discount during purchases.


###HW1

1. __DONE__ Add_UserServiceImpl, UserRepository interface and memory implementation
2. __DONE__ Add EventServiceImpl, EventRepository interface and memory implementation
3. __DONE__ All services should be provided by tests

###HW2
1. __DONE__ Add AuditoriumServiceImpl, AuditoriumRepository and memory implementation
2. __DONE__ Add BookingServiceImpl, TicketRepository and memory implementation
3. __DONE__ Add simple DiscountServiceImpl. 10% for buying more 10 tickets, 20% for 20 tickets and 30% for more than 50 tickets. At the next versions (HW3) Discount service will be improved by personal Events discounts
4. __DONE__ All services should be provided by tests
5. __IN_PROGRESS__ Provide Command Line Interface for adding entities and reading all entities
6. __DONE__ Replace Xml Spring configuration by AppConfig as a Spring Configuration annotated class
7. __DONE__ Add CounterAspect and provide tests
8. __DONE__ Add DiscountAspect and provide tests
9. __DONE__ Add LuckyWinnerAspect and provide tests


####How to test
1. install maven
2. go to project folder by "cd" command in Terminal (Mac) or any Command Line Commander (Windows)
3. mvn clean test