# TMDB Movie Explorer


TMDB Movie Explorer is an Android application that allows users to browse popular movies, TV shows, and artists using the TMDB (The Movie Database) API. The app showcases a clean and modern user interface with offline caching and data persistence.

## Features

- **Browse Popular Content**:
    -  🌟 Artists ([Preview](app/Screenshots/Artists.png))
    - 🎬 Movies ([Preview](app/Screenshots/movies.png))
    - 📺 TV Shows ([Preview](app/Screenshots/TvShow.png))
  

- **Offline Support**: Cache data locally using Room database
- **Search Functionality**: Find movies, TV shows, or artists quickly
- **Modern UI**: Clean, intuitive interface with smooth animations
- **MVVM Clean Architecture**: Better separation of concerns and easier testing
## Screenshots

| Home Screen | Movies | TV Shows |
|-------------|--------|----------|
| ![Home](app/Screenshots/home.png) | ![Movies](app/Screenshots/movies.png) | ![TV Shows](app/Screenshots/TvShow.png) |

| Artists |
|---------|
| ![Artists](app/Screenshots/Artists.png) |

## Technologies Used

- **Language**: Kotlin
- **Architecture**: MVVM Clean Architecture
- **Android Jetpack**:
    - LiveData
    - ViewModel
    - Room Database
- **Libraries**:
    - Retrofit - Networking
    - Glide - Image loading
    - Dagger - Dependency injection
    - Coroutines - Asynchronous programming
