# SimpleDatabase

A simple database class for Android written in Kotlin.

SimpleDatabase allows you to store the following data types in the Android SharedPreferences:

- `String`
- `Boolean`
- `Int`
- `ArrayList<String>`
- any `Gson`-serializable and deserializable class using `getObject<T>()` and `putObject<T>()`
- a list of any `Gson`-serializable and deserializable class using `getListObject<T>()` and `putListObject<T>()`

The project internally has a dependency on [Gson](https://github.com/google/gson).


## Installation

You can add the library to your project using Jitpack.

First, add Jitpack to your root `build.gradle` if it isn't there already:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

```

Then add it to your app's `build.gradle`:

```groovy
dependencies {
    implementation 'com.github.tinacious:simpledatabase:1.0.0'
}
```

Replacae `1.0.0` with the latest version.


## Usage

### Basic Implementation

You can pass any instance of `android.content.SharedPreferences` to the `SimpleDatabase` class.

Here is an example where we get an instance of a private `SharedPreferences`:

```kt
val sharedPreferences = context
    .getSharedPreferences(
        "${context.packageName}_preferences",
        Context.MODE_PRIVATE
    )

val database = SimpleDatabase(sharedPreferences)
```


### KeyValueStorage

Below is a suggested approach to using it. We create a class `KeyValueStorage` that implements `SimpleDatabase`:

```kt
/**
 * Simple key value storage using SimpleDatabase
 */
class KeyValueStorage private constructor(private val delegate: SimpleDatabase) :
    SimpleDatabase by delegate {
    companion object {
        @JvmStatic
        fun getInstance(context: Context): KeyValueStorage {
            val sharedPreferences = context.getSharedPreferences(
                "${context.packageName}_preferences",
                Context.MODE_PRIVATE
            )
            val simpleDatabase = SimpleDatabaseImpl(sharedPreferences)

            return KeyValueStorage(simpleDatabase)
        }
    }
}
```

Then we can access this instance by calling `val keyValueStorage = KeyValueStorage.getInstance(context)`.

The sample project in `app` has an example implementation that uses the [dependencies container pattern](https://developer.android.com/training/dependency-injection/manual#dependencies-container) for dependency injection.


#### Usage with Koin

You can create the above-mentioned class and provide it with Koin in one of your modules:

```kt
val servicesModule = module {
    single { KeyValueStorage.getInstance(androidContext()) }
}
```

Then you can use it in any Koin component as follows:

```kt
class MyActivity : AppCompatActivity() {
    private val keyValueStorage by inject<KeyValueStorage>()
}
```


## Inspiration

I was inspired by [TinyDB](https://github.com/kcochibili/TinyDB--Android-Shared-Preferences-Turbo) to make a simpler Kotlin version.
