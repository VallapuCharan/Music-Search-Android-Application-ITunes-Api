# Music-Search-Android-Application-ITunes-Api
Music search application using itunes api


Using this application we can search any music related Key Word (track title, artist name, etc.) and display the results.

I used JSON parsing to retrieve the music tracks, dynamic layout to display the list, and a different activity to show the details of the selected music track. 

ITUNES API
API URL:  https://itunes.apple.com/search?term=<Search Keyword/s>&limit=<# of Results>

term :Keyword/s you put in the search bar. If you put two or more keywords in the search bar like in our example, you should modify the URL to https://itunes.apple.com/search?term=jack+johnson&limit=25

limit: It is taken from the SeekBar. The minimum value should be 10, and maximum value should be 30.
Track name: used trackName from JSON
Genre: used primaryGenreName from JSON
Artist: used artistName from JSON
Album: used collectionName from JSON
Track: Price used trackPrice from JSON
Album: Price used collectionPrice from JSON
