# WIldlife Tracker.

## Author and collaborators
* Kimatu Franklin.
* Lastborn Tech. Company.
## Description
The Mara wildlife tracker is an application that helps rangers in Mara Rreserve to report and record the animals and species(endangered or Thriving) into a database.
* The information is saved in the database for record keeping.
## Setup instructions
##### Here are the instructions for re-creating the database.

* IN PSQL
* CREATE DATABASE wildlife_tracker;
* \c wildlife_tracker.
* //creating database table for sighting.
* CREATE TABLE sightings( id serial PRIMARY KEY, location varchar, rangername varchar);
* CREATE TABLE animals( id serial PRIMARY KEY,animalname varchar,sightingid int,type DATABASE_TYPE,health varchar,age varchar);


## Technologies used.
* Java.
* Spark framework.
## Licence.
* MIT license.

## Known bugs
* Some of the information saved in the database is not properly displayed.




