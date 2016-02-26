# ServerProxy: simple JSON server that fetches data from forecast.io

This service shows how to fetch JSON data from forecast.io, and how to serve
that same data.  (It does no cacheing, and does no computation on the data.)

This is serving at http://weather-cache.appspot.com (despite doing no caching).

This sample also demonstrates how to deploy an application on Google App Engine.
## Setup
1. Update the <application> tag in src/main/webapp/WEB-INF/appengine-web.xml with your project name
1. Update the <version> tag in src/main/webapp/WEB-INF/appengine-web.xml with your version name

## Running locally
    $ mvn appengine:devserver

## Running locally with quick updates
    $ mvn appengine:devserver_start
    $ mvn install
      (repeat "mvn install" as desired)
    $ mvn appengine:devserver_stop

## Deploying
    $ mvn appengine:update

## Known problems and TODOs:
Error handling: the various exceptions should probably send 500 responses
(in addition to generating stack traces).
