# TvEthusiast

Track TV series! Discover awesome series!
* Discover awesome series

<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/1.png" width="200" height="356" />  <img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/2.png" width="200" height="356" />


* Track series you are interested

<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/3.png" width="200" height="356" />  <img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/4.png" width="200" height="356" />

* Get new episode notifications

<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/9.png" width="200" height="356" />  <img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/8.png" width="222.5" height="356" />

* Save your watching progress

<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/5.png" width="300" height="480" />  <img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/6.png" width="300" height="480" />


[Download APK](https://drive.google.com/file/d/0B2rhAFc83w23dDJxVWt4Y1k1QzA/view?usp=sharing)

## Attention
!!! This APP only contains TV shows information, and it does not provide pirate videos. !!!

## Data source
This APP uses TMDB API, an open soure data API, for getting TV shows information.

https://www.themoviedb.org/documentation/api

## How to build it

### Get an TMDB API KEY
Get a developer api key via...
https://www.themoviedb.org/documentation/api

### Configure your API key
Add API key in gradle.build
```
buildConfigField STRING, "API_KEY", "\"${YOUR_APIKEY}\""
```

### Configure retrolamda
Change jdk location in build.gradle under "app" directory.
```
retrolambda {
        jdk '${JAVA8_HOME_PATH}'
        oldJdk '${JAVA7_HOME_PATH}'
        javaVersion JavaVersion.VERSION_1_7
        jvmArgs '-noverify'
        defaultMethods false
        incremental true
}
```

## Libraries and Plugins in this project

* Retrofit

A type-safe HTTP client for Android and Java

https://github.com/square/retrofit

* RxJava

Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.

https://github.com/ReactiveX/RxJava

* RxAndroid

RxJava bindings for Android

https://github.com/ReactiveX/RxAndroid

* Dagger2

A fast dependency injector for Android and Java.

https://github.com/google/dagger

* Glide

An image loading and caching library for Android focused on smooth scrolling

https://github.com/bumptech/glide

* Butterknife

Bind Android views and callbacks to fields and methods.

https://github.com/JakeWharton/butterknife

* Retrolamda

A gradle plugin for getting java lambda support in java 6, 7 and android

https://github.com/evant/gradle-retrolambda

* Realm

Client-side database

https://realm.io/

## Implementation

### MVP
It was implemented in Model-View-Presenter pattern (MVP). Model represents the database model and pojos that could be display on views. View is a interface to display information. Presenter is just like a middle man who communicates with mode and view. For example, user clicked a button on view to ask for more information. View tells presenter to get data from repository or server, then it tells view to display the data. Every model, view, and presenter has interface and implementation, so we can easily manipulate them. Models and pojos have their builders because they have a lot of contructors. Builders helps to new an object gracefully.

### Service
This APP provides a feature that informs user which episode will be airing today. The service was implemented with IntentService and AlarmManager. IntentService takes care of the background works. AlarmManager controls the service to execute daily model updates and show notifications.

### RxJava, Retrofit, Realm, and Retrolambda
RxJava actually helps a lot when you write complicated requests and chained callback. If you use RxJava, it will take care of them. All you have to do just focus on the flow. Luckily, Retrofit and Realm support observable. So that will be easier for us to adopt them. Retrolambda is an plugin that allows you to write lambda. I think the code will be more concise and readable if you use lambda especially you are using RxJava.

## Notes
I'm still modifying the tests. I will commit them as soon as possible.

## License
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0


