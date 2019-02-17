# Presenter
Simple server to be able to control power point etc. from phone/tablet. It is doing so by simulating arrow left/right 
and capture the screen for every click.

# Install
Before you run this program, you need to have installed java.

When you have downloaded the app, open terminal and type:

```
java -jar presenter.jar
```

You now need to find your ip address to be able to connect to your computer from your phone (Google it).

You can change different variables on the following format:
```
java -Dserver.port=80 -jar presenter.jar
``` 

|Property|Default|Description|
|---|---|----|
|server.port | 8080 |Start the server on different port|
|presenter.temp |temp|Define the location of the temp folder|
|presenter.clean-temp|true|Will only keep the last image in temp folder. Be aware that it will delete ALL FILES in temp folder if this is set to true. Choose a temp folder with care!|

# Screen shot
![Presenter on phone screen](https://github.com/eirikandre/presenter/blob/master/screen_capture.PNG?raw=true)


