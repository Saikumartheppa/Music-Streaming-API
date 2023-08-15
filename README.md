<h1 align = "center"> Music-Streaming-API </h1>

<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.0.6-brightgreen.svg" />
</a>
</p>
The Music Sound Fusion is a web application designed to deliver an exceptional music listening experience to users. Developed using the Spring Boot framework and Java programming language, the project aims to provide a seamless and feature-rich platform for discovering, managing, and enjoying music.

---
<br>

>## Technologies used

* Spring Boot
* Java
* SQL database
---
<br>

>## Dependencies
The following dependencies are required to run the project:

* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* javax -mail
* Swagger
---

<br>

>## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:
```
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/<DatabaseName>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```
<br>

>## Data Flow

* **Controller** : The controller handles incoming HTTP requests and delegates the processing to the appropriate service methods. It maps the incoming URLs to specific methods and returns the corresponding HTTP responses.
* **Services** : The services contain the business logic of the application. They interact with the repositories to fetch or update data, perform CRUD operations, and manage the relationships between entities.
* **Repository** : The repository interfaces define data access operations using Spring Data JPA. They interact with the database to retrieve or persist data from/to the entities.
* **Database Design** : The database design includes tables for users, playlists, and songs, where playlists have a many-to-one relationship with users, and playlists have a many-to-many relationship with songs.
* **dto** : this layer UserSignupInput,UserSignUpOutput,AdminSignInInput,AdminSignInOutput which store the data of user and admin to Authentication.
---
<br>

>## Data Structure

* **User** :
```
  This table stores user information, including their unique identifier (id), username, hashed and salted password, email, and timestamps for creation and update.
```
* **Admin** :
```
  This table stores user information, including their unique identifier (id), username, hashed and salted password, email, and timestamps for creation and update.
```
* **Song** :
```
  The songs table contains information about individual songs, such as the song name, artist, album, duration in seconds, and timestamps for creation and update.
```
* **Playlist** :
```
  The playlists table holds details about user-created playlists. It includes a foreign key (user_id) that references the user who owns the playlist, the playlist name, and timestamps for creation and update.
  
  RelationShips :
  
        User PlayListOwner :- Many-To-One relation ship with user.
  
        List<Song> songs :- Many-To-Many relation ship with songs
  
```
* **AuthenticationToken** :
```
  The Authentication Token tables contains Singin user Information such as tokenId, token value, token creation time stamp.
  
  RelationShips :
  
        User user :- One-To-One relation ship with user.
  
        Admin admin :-  One-To-One relation ship with admin;
  
```
---
<br>

>## API Endpoints 

The project provides the following API endpoints:

* **AdminController** 
```
      POST/admin/signUp                                    :-  Register new Admin.
      POST/admin/signIn                                    :-  SignIn existing Admin.
      POST/song                                            :-  Create a Song.
      GET/songs                                            :-  Fetch all Songs.
      GET/song/{songId}                                    :-  Fetch song by songId.
      PUT/song/{songId}/title/{title}                      :-  Update song by songId.
      DELETE/song/{songId}                                 :-  Delete song by songId.
      DELETE/singOut                                       :-  SignOut Admin.
      
```
* **UserController**
```
      POST/user/signUp                                     :-  Register new User.
      POST/user/signIn                                     :-  SignIn existing User.
      POST/playlist                                        :-  Create a playList.
      POST/playlist/songs                                  :-  Add Songs to the existing playList
      GET/playlist/user                                    :-  Fetch all PlayLists of an user.
      GET/songs/playlist/{playlistId}                      :-  Fetch all songs by playListId.
      PUT/playlist/{playListId}                            :-  Update playlist by playlistId.
      DELETE/playList/{playListId}                         :-  Delete playlist by playlist.
      DELETE/playList/{playListId}/songs/song/{songId}     :-  Delete song by playlistId and songId.
      DELETE/singOut                                       :-  SignOut User.
```


---
<br>

>## Project Summary


Sound Fusion provides users with a seamless music streaming experience, allowing them to discover and manage playlists and songs. The application incorporates role-based access control, allowing normal users to create playlists and admin users to manage the song library. The data flow is organized through controllers, services, and repositories, interacting with the MySQL database. The project is designed to be responsive and user-friendly, ensuring a smooth and enjoyable music experience for all users.
Overall, the Sound Fusion provides a comprehensive platform for users to immerse themselves in the world of melodies.
---
<br>

>## Author

👤 **Saikumar theppa**

* GitHub: [Saikumar theppa](https://gist.github.com/Saikumartheppa)

---
<br>

>## Installation and Usage

* Clone the repository to your local machine.
* Make sure you have Java, Maven, and MySQL installed.
* Set up the database configuration in the application.properties file.
* Run the application using Maven or your preferred IDE.
* Access the API endpoints using Swagger UI by navigating to the appropriate URL (e.g., `http://localhost:8080/swagger-ui.html`).
---
<br>

>## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page]("url").
    
---
<br>

>## Show your support

Give a ⭐️ if this project helped you!
    
---
