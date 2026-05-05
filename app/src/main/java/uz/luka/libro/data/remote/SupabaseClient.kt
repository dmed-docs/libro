package uz.luka.libro.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {
    
    // Supabase ma'lumotlari
    private const val SUPABASE_URL = "https://gvzxwncrfzxxxyayjutq.supabase.co"
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd2enh3bmNyZnp4eHh5YXlqdXRxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzc5ODY0NTcsImV4cCI6MjA5MzU2MjQ1N30.EzWn8zAozZd8MxBuPN6EaFnXUqmcII5SGtF_E2bJnYI"
    
    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Storage)
        }
    }
}
