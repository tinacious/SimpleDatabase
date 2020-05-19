# SimpleDatabase

A simple database class for Android written in Kotlin.

SimpleDatabase allows you to store the following data types in the Android SharedPreferences:

- `String`
- `Int`
- `ArrayList<String>`
- `ArrayList<T>` – where `T` is any JSON serializable class


## Dependencies

- [Gson](https://github.com/google/gson)


## Usage

Copy and paste the class into your project as a new class.

Implement it as such:

```kt
val sharedPreferences = context
    .getSharedPreferences(
        "${context.packageName}_preferences",
        Context.MODE_PRIVATE
    )

val database = SimpleDatabase(sharedPreferences)
```

You can pass any instance of `android.content.SharedPreferences` to the `SimpleDatabase` class.

The above example implements what the [deprecated](https://developer.android.com/reference/android/preference/PreferenceManager) class `PreferenceManager`'s `getDefaultSharedPreferences()` method returns.


## Inspiration

I was inspired by [TinyDB](https://github.com/kcochibili/TinyDB--Android-Shared-Preferences-Turbo) to make a simpler Kotlin version.


## Roadmap

- Add support for more types
- Write tests
- Distribute as package
