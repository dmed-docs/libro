package uz.luka.libro.domain.common

import androidx.annotation.DrawableRes
import uz.luka.libro.R

sealed class IntroCommon(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {

    data object FirstPages : IntroCommon(
        R.drawable.intro_first_icon,
        "Enjoy the World of Reading and Sharing Together",
        "Discover thousands of books and meet your book bestie!"
    )

    data object SecondPages : IntroCommon(
        R.drawable.intro_second_icon,
        "Read and Make Friends Without Limits",
        "Read Anytime, Connect Anywhere with iJogja"
    )

    data object ThirdPages : IntroCommon(
        R.drawable.intro_third_icon,
        "Welcome to the iJogja Community",
        "Enhance Your Reading Experience with Social Features on iJogja"
    )

}