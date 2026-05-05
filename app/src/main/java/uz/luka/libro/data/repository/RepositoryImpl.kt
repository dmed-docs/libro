package uz.luka.libro.data.repository

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import uz.luka.libro.domain.repository.Repository

class RepositoryImpl : Repository {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://gvzxwncrfzxxxyayjutq.supabase.co",
        supabaseKey = "sb_publishable_VyvRLks5Q917MmmYZnlsHQ_KXac9na_"
    ) {
        install(Postgrest)
    }


}
