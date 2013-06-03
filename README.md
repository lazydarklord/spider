This project is a sample coding task for Spider Tracks developers.

**Overview:**

The application provides a journey planner for a hypothetical railways company "GoFetch Railways". The rail network is represented a directed graph with the application providing the user the following three usecases

1. Calculate Distance: Given a path like "A-B-C-D" (where A,B,C,D are stations in the network), the application displays the total distance for traversing this path or an error message if one the path cannot be traversed.

2. Journey Planner: Given a set of source and destination stations and constraints on the max stops in between or the exact number of stops in between, the application displays all the possible paths that can be taken.

3. Shortest Route: Given a set of source and destination stations the shortest path (with the distance) that can be taken.

**Running the project:**

1. Import the project as a Maven project

2. Run the following from the commandline (or use the equivalent from the IDE): mvn install

3. From the commandline change the directory to spider and execute the following: java -cp target/spider-1.0-SNAPSHOT.jar com.github.lazydarklord.spider.App

**Usage:**
* Calculate Distance: Takes input in the format _(station ID)-(station ID)-(station ID)_ Ex: A-B-_C
* Journey Planner: Takes input in the format _(station ID) to (station ID) (max stops) (exact stops)_ where max stops and exact stops should be set to -1 to ignore that constraint Ex: A to J -1 -1
* Shortest Route:  Takes input in the format _(station ID) to (station ID)_ Ex: A to J
