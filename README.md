# Tournament manager

### Hópmeðlimir

*Birgir Óli Snorrason 	- 150795-2499*

*Jón Þorsteinsson		- 130291-2289*

*Kristján Pétur Þórarinsson	- 270695-2569*

*Kristófer Már Gíslason	- 181096-3579*

## Success metrics

The most obvious criteria would be the number of tournaments being held over some period of time, and the fact that a tournament is completed, not just created. The average number of participants or teams that register to tournaments and the amount of 
users could also give a good indication about whether the project is successful or not. It would also be a sign of success if an official tournament decides to use our platform.

## Vision statement

Our system is designed for professional and leisure tournament holders and participants   who want to set up and organize tournaments for football, handball or basketball. The tournament management system is an information system that will help organize and keep track of tournament status, unlike other tournament systems our will not use the popular solution of using an excel spreadsheet instead we will provide an easy to use platform with online results that will provide participants with real-time updates on games and leaderboard positions.

## Major features
+ A new user is interested in using the application. He enters the SignUp section where he is prompted to provide a username and a password. The system then validates his input and creates his account.
+ A tournament manager wants to set up a tournament using the application. He enters the tournament creation section and selects the type of sport. Then he can either register the teams himself or he can let teams sign up themselves. The system then creates a tournament based on those parameters and organizes matches between registered teams.
+ A user wants to subscribe to a tournament to have quicker access to information regarding it. He searches for the tournament by name using the search window. The system finds the tournament matching the search in the database and shows the user the homepage for that tournament. On that page the user has an option to follow a tournament. The user chooses that option and the system adds the tournament to the list of tournaments followed by the user.
+ A tournament holder decides to have his tournament registration based, he then picks the expiration date for registration. The system issues a participation key for the tournament which the tournament holder can send to possible participants. The participants will then decide if they will register to the tournament. The system declines all attempts to register after the expiration date.
+ When the user is creating a new tournament he will be prompted by the system to add the ability to have a group stage with knockout phases. The system will then create a tournament with the abilities the user has chosen.




## Scope of initial release

In the initial release a user should be able to create an account (I). A user will be able to create tournaments for three different type of sports, football, handball and basketball (II).  A user can also sign his team up for tournaments (IV) and subscribe to tournaments he’s interested in to get updates about it (III). 

## Questions for Matthias

Is it possible to persist an array/list from the front-end to the back-end? ...without using the ajax method?

Correct persitance connections between entities.

Is it sensible to have a default layout, fixed header and footer so we don't have to copy and paste them to every new jsp file.
