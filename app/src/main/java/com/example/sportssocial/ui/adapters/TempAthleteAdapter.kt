package com.example.sportssocial.ui.adapters

import android.app.Application
import com.example.sportssocial.data.model.db.TempAthlete
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class TempAthleteAdapter @Inject constructor(@ApplicationContext private val context : Context,
                                             private var onCardClick: (position: Int) -> Unit
)
    : RecyclerView.Adapter<TempAthleteAdapter.ViewHolder>() {

    var athleteList = getTempAthletes()

    class ViewHolder(view: View, private val onCardClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        val firstName: TextView = view.findViewById(R.id.cardAthleteFirstName)
        val lastName: TextView = view.findViewById(R.id.cardAthleteLastName)
        val userName : TextView = view.findViewById(R.id.cardAthleteUsername)
        val imageView: ImageView = view.findViewById(R.id.cardAthleteImage)

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            onCardClick(position)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempAthleteAdapter.ViewHolder {
        var viewInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.athlete_card, parent, false)

        return TempAthleteAdapter.ViewHolder(viewInflater, onCardClick)
    }

    override fun onBindViewHolder(holder: TempAthleteAdapter.ViewHolder, position: Int) {
        holder.apply {
            firstName.text = athleteList[position].firstName
            lastName.text = athleteList[position].lastName
            userName.text = athleteList[position].userName
        }

        try {
            Glide.with(context).load(athleteList[position].image).into(holder.imageView)
        } catch (e: Exception) {
            Timber.e("ArticleThumbnailAdapter: Line 43. Exception: $e")
            holder.imageView.setImageResource(R.drawable.ic_baseline_person_24)
        }
    }

    override fun getItemCount(): Int {
        return getTempAthletes().size
    }

    //
    fun getTempAthletes() : List<TempAthlete>{
        var tempList = listOf<TempAthlete>()
        val aaronDonald = TempAthlete(
            "Aaron",
            "Donald",
            "@AaronDonald",
            31,
            "Aaron Charles Donald (born May 23, 1991) is an American football " +
                    "defensive tackle for the Los Angeles Rams of the " +
                    "National Football League (NFL).",
            "https://devoncowherd.github.io/AthletePhotos/AaronDonaldProFootballRumors.jpg"

        )
        val tomBrady = TempAthlete(
            "Tom",
            "Brady",
            "@TomBrady",
            44,
            "Thomas Edward Patrick Brady Jr. (born August 3, 1977) is an American football quarterback for the Tampa Bay Buccaneers of the National Football League (NFL).",
            "https://devoncowherd.github.io/AthletePhotos/AllProReelTomBrady.jpg"
        )
        val austinMatthews = TempAthlete(
            "Austin",
            "Matthews",
            "@AustinMatthews",
            24,
            "Auston Taylour Matthews (born September 17, 1997) is an American professional ice hockey center and alternate captain for the Toronto Maple Leafs",
            "https://devoncowherd.github.io/AthletePhotos/AustonMatthews.jpg"
        )
        val juanSoto = TempAthlete(
            "Juan",
            "Soto",
            "@JuanSoto",
            23,
            "Juan José Soto Pacheco (born October 25, 1998), nicknamed \"Childish Bambino\", is a Dominican professional baseball outfielder for the Washington Nationals",
            "https://devoncowherd.github.io/AthletePhotos/BleachReportJuanSoto.jpg"
        )
        val lionelMessi = TempAthlete(
            "Lionel",
            "Messi",
            "@LionelMessi",
            35,
            "Lionel Andrés Messi also known as Leo Messi, is an Argentine professional footballer who plays as a forward for Ligue 1 club Paris Saint-Germain",
            "https://devoncowherd.github.io/AthletePhotos/BritannicaLionelMessi.png"
        )
        val caleMaker = TempAthlete(
            "Cale",
            "Maker",
            "@CaleMaker",
            23,
            "Cale Douglas Makar (born October 30, 1998) is a Canadian professional ice hockey defenceman for the Colorado Avalanche of the National Hockey League (NHL)",
            "https://devoncowherd.github.io/AthletePhotos/CaleMaker.jpg"
        )
        val steveAustin = TempAthlete(
            "Steve",
            "Austin",
            "@SteveAustin",
            57,
            "Steve Austin better known by his ring name \"Stone Cold\" Steve Austin, is an American media personality, actor, producer, and retired professional wrestler.",
            "https://devoncowherd.github.io/AthletePhotos/EtsySteveAustin.png"
        )
        val mikeTrout = TempAthlete(
            "Mike",
            "Trout",
            "@MikeTrout",
        30,
            "Michael Nelson Trout (born August 7, 1991) is an American professional baseball center fielder for the Los Angeles Angels of Major League Baseball (MLB).",
            "https://devoncowherd.github.io/AthletePhotos/NYTMikeTrout.jpg"
        )
        val novakDjokovic = TempAthlete(
            "Novak",
            "Djokovic",
            "@NovakDjokovic",
            35,
            "Novak Djokovic is a Serbian professional tennis player. He is currently ranked world No. 3 in singles by the Association of Tennis Professionals (ATP).",
            "https://devoncowherd.github.io/AthletePhotos/NYTNovakDjokovic.jpg"
        )
        val paulRabil = TempAthlete(
            "Paul",
            "Rabil",
            "@PaulRabil",
            36,
            "Paul Rabil (born December 14, 1985), is an American former professional lacrosse player and co-founder of the Premier Lacrosse League.",
            "https://devoncowherd.github.io/AthletePhotos/PaulRabil.webp"
        )
        val rafaelNadal = TempAthlete(
            "Rafael",
            "Nadal",
            "@RafaelNadal",
            36,
            "Rafael Nadal Parera is a Spanish professional tennis player, currently ranked world No. 4 in singles by the Association of Tennis Professionals (ATP).",
            "https://devoncowherd.github.io/AthletePhotos/RafaelNadal.jpg"
        )
        val christianoRonaldo = TempAthlete(
            "Christiano",
            "Ronaldo",
            "@ChristianoRonaldo",
            37,
            "Cristiano Ronaldo dos Santos Aveiro GOIH ComM is a Portuguese professional footballer who plays as a forward for Premier League club Manchester",
            "https://devoncowherd.github.io/AthletePhotos/SIChristianoRonaldo.jpg"
        )
        val michaelJordan = TempAthlete(
            "Michael",
            "Jordan",
            "@MichaelJordan",
            59,
            "Michael Jeffrey Jordan (born February 17, 1963), also known by his initials MJ, is an American businessman and former professional basketball player.",
            "https://devoncowherd.github.io/AthletePhotos/TheGuardianMichaelJordan.webp"
        )
        val theUndertaker = TempAthlete(
            "The",
            "Undertaker",
            "@TheUndertaker",
            57,
            "Mark William Calaway (born March 24, 1965), better known by the ring name The Undertaker is an American retired professional wrestler.",
            "https://devoncowherd.github.io/AthletePhotos/TheSunTheUndertaker.png"
        )
        val lebronJames = TempAthlete(
            "Lebron",
            "James",
            "@LebronJames",
            37,
            "LeBron Raymone James Sr is an American professional basketball player for the Los Angeles Lakers of the National Basketball Association (NBA).",
            "https://devoncowherd.github.io/AthletePhotos/usa_today_Lebron.jpg"
        )

        tempList = listOf(aaronDonald,
            tomBrady,
            austinMatthews,
            juanSoto,
            lionelMessi,
            caleMaker,
            steveAustin,
            mikeTrout,
            novakDjokovic,
            paulRabil,
            rafaelNadal,
            christianoRonaldo,
            michaelJordan,
            theUndertaker,
            lebronJames)
        return tempList
    }


}


