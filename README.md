# TvEthusiast

Track TV series! Discover awesome series!
* Discover awesome series
* Track series you are interested
* Get new episode notifications
* Save your watching progress

## Data source
This APP uses TMDB API.
https://www.themoviedb.org/documentation/api

## Screenshots
<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/1.png" width="400" height="712" />

<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/2.png" width="400" height="712" />

<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/3.png" width="400" height="712" />

<img src="https://github.com/ivanisidrowu/TvEnthusiast/blob/master/screenshots/4.png" width="400" height="712" />

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

Retrofit https://github.com/square/retrofit

RxJava https://github.com/ReactiveX/RxJava

RxAndroid https://github.com/ReactiveX/RxAndroid

Dagger2 https://github.com/google/dagger

Glide https://github.com/bumptech/glide

Butterknife https://github.com/JakeWharton/butterknife

EventBus https://github.com/greenrobot/EventBus

Retrolamda https://github.com/evant/gradle-retrolambda

Realm https://realm.io/

## How to contribute

Any contributions will be appreciated. If you want to contribute to this project, you can send pull request.

If you discover an issue, please file it in issue section. Thanks.
