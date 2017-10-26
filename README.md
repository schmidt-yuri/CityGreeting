# CityGreeting
[![Build Status](https://travis-ci.org/schmidt-yuri/CityGreeting.svg?branch=master)](https://travis-ci.org/schmidt-yuri/CityGreeting)


Console application with command line arguments:
1. Mandatory argument - a city name.
2. Optional argument - a time zone identifier.
The programm prints out a greeting message for the entered city which depends
on:
1. the current time 
2. the time zone of the city.
The day parts:
Morning 06:00 - 09:00
Day	09:00 - 19:00
Evening	19:00 - 23:00
Night	23:00 - 06:00
If the entered city name is not in the list of zone identifiers 
than it is necessary to enter GMT as the second argument(in the format of GMT+/-[a number from 1 to 12].
The console messages must be extracted from message bundle and they must depend on user's system locale.
There are cities that consist of two words. For example, New York. In order that such city's name to be
a single argument it should be entered with underscore between words. For example New_York, Los_Angeles.
However, in the print out the underscore should be replaced with a space.

Examples of print out:
Good morning, New York!
Good day, Paris!
Good evening, Caracas!
Good night, Bahdad!

The code should contain all possible unit tests and create log file.
Maven should be used for building the project.

The source code should be published on GitHub. The README file must contain build status by Travis CI (https://docs.travis-ci.com/user/status-images/)
or Circle CI (https://circleci.com/docs/status-badges) as a badge. A click on the badge takes to the build page. The Build must be
successful.  The tests must run during the build. 


