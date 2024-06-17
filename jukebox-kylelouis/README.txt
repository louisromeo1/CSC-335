Authors: Louis Romeo, Kyle Myint
Date: 3/27/2024
Description: Collaborative project completed in teams of two who share the same GitHub repo. Jukebox allows users to play songs for free after creating an account and logging in. Users are able to choose a song and the application shows the PlayList so students can see the current song playing and all song(s) queued up to be played later in FIFO order. Users see the list of songs that can be selected in a JavaFX TableView. The columns include the song's title, artist, and time to play. Completed in two sprints.
Candidate Object - Responsibility
Jukebox - Coordinate Activities and shows all functionality
JukeboxAccount - Remembers the account data and can determine how many songs have been played today
Song - Know title, artist, playtime, file name
PlayList - Can queue up songs and play songs in FIFO order
SongSelector - Knows all available songs and displays them in a sortable TableView. See javafx.scene.control.TableVie
LocalDate - Helps JukeboxAccounts know how many songs they played.
Grade: 200/200
