# ProjectVI
ProjectVI for CSC142


Skip To Content
Dashboard
Aaron Hand
Account
Dashboard
Courses
Groups
Calendar
1
Inbox
Commons
Help

CSC 142 - 1163AssignmentsProject VI
Spring 2016
Home
Announcements
Discussions
Grades1
Syllabus
Modules
People
Collaborations
SCC Library
eTutoring
Project VI
 Submit Assignment
Due Saturday by 7pm  Points 20  Submitting a file upload File Types pdf, docx, and zip  Available May 29 at 12am - Jun 18 at 7pm 21 days
Space Invasion!
When working at the computer, everyone needs to take a break once in a while. Since computer games are such a popular diversion, here is your chance to write your own computer game... Space Invasion! In this game, the player controls a space ship which shoots at aliens. The game is initially fairly simple, but you are free to make it as challenging as you like.

 

This homework is an extension of the graphics ideas you used in Project V. In addition, it requires using inheritance and other issues involved in real-life programming:

using a library of prepackaged functions
having to make sense of  a fairly complicated program that someone else wrote
 

What you have to start with

Sample files: Alien.javaPreview the documentView in a new window, SpaceInvader.javaPreview the documentView in a new window, MovingObject.javaPreview the documentView in a new window, SpaceShip.javaPreview the documentView in a new window

The sample program displays an outer space screen with a space ship and some space aliens. The space ship moves back and forth on its own and it can shoot at the aliens if the player presses the space bar.

You have a sample Executable ProgramView in a new window to experiment with (written in C). This sample executable only meets the basic requirements outlined below (it would receive a grade of 8/20).  In the sample executable, you can move the space ship to the left by hitting the "<" key and to the right by hitting the ">" key (actually, "," and "." work as well so you don't have to use the shift key). The ship will shoot if you press the space bar. Note that the sample executable has a menu. But this is not required in this assignment.

Start by reading through and running the sample file provided (Run the class SpaceInvader).  This program does a couple of things, but not many.  Make sure that you understand what it does before modifying it . 

What you need to end up with

###To meet the basic requirements (worth 8 points), your program should

start with three different colored aliens each with a different number of hit points. Each time an alien is shot, its color changes and its hit points decrease until it is dead. Aliens should start at different heights on the screen, and over time, aliens should drop down a level (like the sample executable). When an alien is dead, it should no longer be drawn on the screen (contrary to what is done when you execute the code in the sample file).
allow the user to move the space ship left or right with the keys ">" and "<". Print instructions at the top of the screen telling which keys move the space ship.
check whether the game is over (if there are no more aliens), and if so, prompt the user for another game via a dialog box.
To do so, you will to complete some code in the three classes SpaceInvader, Alien and SpaceShip. Read through these classes. Comments in the code give you some ideas as to what to write.

Improve the program so that the game is fun to play. Beyond the above basic requirements, here are an extra requirement and some suggestions for a more exciting program (worth another 12 points).

##Required (4 points)

Create a new class for a new kind of Alien (Derive it directly from MovingObject or from Alien). That class may incorporate some of the ideas listed below. It must be somewhat different from the Alien class.
Suggestions (4 of them or similar ideas = 8 points)

Let the space ship move forward and backward as well as right and left.
Have something interesting happen if the aliens reach the bottom of the screen (currently, the aliens just return to the top of the screen).
Have the aliens fire bullets at the space ship, and if they hit, the space ship gets hurt. Keep track of the health level of the space ship.
Some of the aliens could help the space ship (by dropping special devices to "heal" the space ship, make it invisible, give it a shield, etc.)
Let the space ship fire different types of bullets/lasers that do interesting things.
Keep track of a player's score based on how quickly the aliens are shot.
Allow multiple space ships on the screen at once.
Increase the level of difficulty as the game progresses
Make the aliens move right to left so that they're harder to shoot.
Create different types of space ships.
Use your imagination...
I am sure that you can come up with something interesting; you don't have to do something from this list if you have other ideas. But, you have to implement some features that go beyond the minimal requirements.

Caution:  Be realistic and don't try to do too much all at once.  It is much more important to have something fairly simple that works instead of something overly complex that you can't finish in time.  Start with a simplified version of your ultimate program, get that working, then add to it.

Grading

Your work will be graded on a 20 point scale. 8 points will be given to a program that meets the basic requirements.

The remaining 12 points are for the extra features that you need to add to the program. You will get the full 12 points if you create a new class for a new kind of Alien (= 4 points) and if you hear me yell "What a ride!" when I play your game (=8 points). More precisely, if you implement at least 4 (non simplistic, glitzy and working) extra features, you have a very high probability of getting the full 8 points.

At the start of the file SpaceInvader.java, write a compliance statement describing how you think you meet the requirements of the assignment, e.g what are your 4 extra features and how is the new alien different? Full credit is given only if you have such a statement.

Here are some examples of programs done by previous students: Example1View in a new window, Example2View in a new window, Example3View in a new window, Example4View in a new window

Good luck!  

Your Program has to be your own!
Previous Next

