# Trending Movie Time

## Getting Start

You can open this Project with Android Studio *3.4.1*.

## Prerequisites

What things you need to install the software and install them :
If you using Mac OS, you could install this with [Homebrew](homebrew.sh)

- Java 8
- Gradle

## Installing

A step by step to get a development env running

```
./gradlew assembleDevDebug
```

To install in emulator or device
```
./gradlew installDevDebug
```

To check dependencies using

```
./gradlew app:dependencies
```

## Features

- API Call with [Retrofit](https://square.github.io/retrofit)
- Dependency injection (with [Koin](https://insert-koin.io))
- Reactive programming with RxJava 2 and RxAndroid
- Google Design library
- Android architecture components with MVVM
    
## Supported devices

The template support every device with a SDK level of at least 16 (Android 4+)


| **Splash Screen** | **Main Screen** | **Result Pick Genre Screen** |
| ------ | ------ | ------ |
| ![alt text](https://i.postimg.cc/WzvzrVD8/Screenshot-2020-04-16-20-39-10-66.png "Splash Screen") | ![alt text](https://i.postimg.cc/k4dqYcnX/Screenshot-2020-04-16-20-39-15-01.png "Main Screen") | ![alt text](https://i.postimg.cc/Gh9wQNkx/Screenshot-2020-04-16-20-39-19-58.png "Result Pick Genre Screen") 

| **Detail Movie Screen** | **Trailer Movie Screen** | **Review Movie Screen** |
| ------ | ------ | ------ |
| ![alt text](https://i.postimg.cc/zBNgrTJ0/Screenshot-2020-04-16-20-39-24-50.png "Detail Movie Screen") | ![alt text](https://i.postimg.cc/q7T59ZCY/Screenshot-2020-04-16-20-39-40-94.png "Trailer Movie Screen") | ![alt text](https://i.postimg.cc/0jMn6pwD/Screenshot-2020-04-16-20-39-50-89.png "Review Movie Screen")

