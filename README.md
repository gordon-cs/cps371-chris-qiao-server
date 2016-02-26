# cps371-chris-qiao-server


This server uses api.forecast.io to transfer weather data and cache the data. 
It is similar to the api.forecast.io, and it sends a string of current weather information to client. 
This server caches data in recent 1 hour and location(not complete) and should delete expired data 
so that the cache does not get larger and larger and take longer and longer time to search through(not complete).

I use HashMap to store cache, and the key is a string array, the value is weather data. when a request comes, 
the server checks in the map using time and the location to find useful cache and sends it to client. 
if nothing is useful, the server get the weather data from api.forecast.io, saves it to cache and sends it to the client.

Server URL: http://super-weatherproxy.appspot.com/lat,lng
