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

// If you're using Koin dependency injection, you can create a module.
val servicesModule = module {
    single { KeyValueStorage.getInstance(androidContext()) }
}

// and then use it
class MyActivity : AppCompatActivity() {
    private val keyValueStorage: KeyValueStorage by inject()
}
