# MoviesAppUpdated
movies-app as submission for technical interview at fair-money

## Getting Started
* Clone the repository

## API used
* https://developers.themoviedb.org/3  

## Important Documentations
* [Movie API Docs for the endpoints needed by the app](https://developers.themoviedb.org/3)

## Architecture
* MVVM for the presentation layer
* Android architecture components ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)


## Important Libraries and Frameworks
* [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection
* [Room](https://developer.android.com/training/data-storage/room) - Provides abstraction layer over SQLite for local caching
* [Retrofit](https://square.github.io/retrofit/) - type safe http client and supports coroutines out of the box.  
* [Moshi](https://github.com/square/moshi) - JSON Parser,used to parse requests on the data layer
* [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
* [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines for threading
* [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/)) - 
* [Mockk](https://mockk.io/) mocking library for kotlin. also provides a nice dsl manner for creating stubs
* [Kluent](https://github.com/MarkusAmshove/Kluent) A fluent assertion library for kotlin
* [Coil](https://github.com/coil-kt/coil) - image loading library with Kotlin idiomatic API

## Tests
Tests are automated and run on CircleCI
