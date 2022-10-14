# The Intention

So the point of this project was to make something that was fun to do. The idea came from the time when I thought Guessing the Price of a product in a store was a fun thing to do. And more so from guessing accruately how overpriced the Iphone was. Ofcourse that was the inspiration. Now this idea can be grown on obviously. Now true, there are better things that I can do for a project, but this is one that I can safely mention in an Interview and have the interviewer play the game. But much more importantly, I would like to see how all my friends can play this game and who can guess the best. Above all that is what that matters. 

Another reason to go with this project, is that it would teach me nicely the basics of working with databases. It would also be my first project in which I add a Database. 

This thing must look Beautiful.

# The Features

The reason that Intention was important to state is that its the motivation to do everything, and all the features wanna meet the expectations somewhere down the road. Keeping that in mind, the features that I want it to have are: 

1. Ideally, it must be able to scrap data off a website like amazon, or any other site where stuff is sold, and just download the image, name and price, and save the images locally, and the name, price and the location of the image in MongoDB. 
2. It should have a menu to choose from what category of items you wanna guess the price from. Something like 5 or 6 Categories, each would have a separate table. 
3. It should have a dynamic layout, so like i dont want fixed sized buttons, and you should be able to freely resize it and make it fullscreen. 
4. Again, clean and minimalistic UI. Doing it in swing we wouldnt have a lot of choices, But we will try to keep it simple. 
5. Unique and universal font.
6. Obviously also a timer to make it interesting, something like 10 seconds. 
7. HAHA users too. So like you can log in, and save your progress, in that very database, You should also be able to login as guest, in case I send this to some other computer, where you obviously cant play this, coz the database doesnt exist. In which case, You would be asked questions from the Local database.
8. There should now be an option to update the local database. And save it. 
9. Going too far would be to show a small status bar on the bottom, where I could have a progress bar too to check how much of the local database has been updated, coz that would take time. 
10. The goad would now be to make it such that it runs on any other computer, as well as my computer. My computer would have the users, and the database itself. Others would have to login as guest, and have their login button grayed out. I would access files from a local SQL database, which ideally should get updated every time I launch the application. I will also update the local file database. So both would get updated. Both would be a copy of each other. Except on someone else machine where there would be just one, that they would refer from. They would also be able to downlaod and update it ofcourse, but it would only be a csv files with images and names, and no user data.
11. Now the local database will be accessed on my computer as well, in case there is no internet, or you cant scrap data due to some reason.
12. I would like to keep this as Modular as possible, coz it can then be used for a lot of things other than just for guessing price, all the rest of the features combined with a database are pretty useful. It could just be copied and pasted and be made into a new project with just a few design changes. The skeleton would be same.
13. Also there would be scores too. Even for Guest people. The usual scoring system. You get points depending on how fast you guess the item. Each level would get harder, as the price to guess would keep increasing. 

# Screens

1. The Menu screen. 
2. The Help screen, just to add more screens
3. The Credit Screen, just to add more screens and to give myself credit, obviously. 
4. A Button to _Update Database_, which on my computer would update local file and SQL database. On someone else's computer it would update the Local file database only. Either way It will scrap data. 
5. There should be a login screen, but dont make it like those hobby project login screens. Make it look professional. 
6. A Button to toggle light and dark mode. Coz why not. That would be the first time I would do it in my project. 
7. A Screen to Choose which topic we wanna guess from. Topics should include many things including a _Random_ topic, where everything would be considered. 
8. The Actual playing screen. 
9. Game Over screen, with options to go back and go to the topic choose screen and stuff. 
10. If possible a view highscore screen lmao. 

# Databases

1. The MongoDB Database would be storing user data and different tables or files for each topic. So like 7 or 8 tables in total. The Same Thing would be stored in a csv file as well.
2. The Tables would store the location of the images for each item, The Price and the name. 
3. The User database would store the Name, password, and Score. 
4. The Images would be stored locally. 


