package com.blogsetyaaji.moviecatalogue.utils

import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.TvResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.BelongsToCollection
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.LastEpisodeToAir
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.NextEpisodeToAir

object FakeDataDummy {
    fun generateDummyMovies(): MovieResponse {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "en",
                "The Unholy",
                false,
                "The Unholy",
                mutableListOf(27),
                "/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
                "/wwFBRyekDcKXJwP0mImRJjAnudL.jpg",
                "2021-03-31",
                4734.184,
                7.1,
                632357,
                false,
                696
            )
        )
        movies.add(
            MovieEntity(
                "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                "en",
                "Army of the Dead",
                false,
                "Army of the Dead",
                mutableListOf(28, 27, 53),
                "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                "/9WlJFhOSCPnaaSmsrv0B4zA8iUb.jpg",
                "2021-05-14",
                3494.679,
                6.6,
                503736,
                false,
                1189
            )
        )
        movies.add(
            MovieEntity(
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "en",
                "Cruella",
                false,
                "Cruella",
                mutableListOf(35, 80),
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                "/auFsy7xWxLHGC3WrVyPEeKNVVUJ.jpg",
                "2021-05-26",
                3116.245,
                8.8,
                337404,
                false,
                1095
            )
        )
        movies.add(
            MovieEntity(
                "A special crimes investigator forms an unlikely bond with a serial killer to bring down a global child sex trafficking syndicate.",
                "en",
                "I Am All Girls",
                false,
                "I Am All Girls",
                mutableListOf(80, 18, 9648, 53),
                "/m6bUeV4mczG3z2YXXr5XDKPsQzv.jpg",
                "/yyWNPhP1HR4BTLErHcZwIUsMBvA.jpg",
                "2021-05-14",
                1966.057,
                7.0,
                823855,
                false,
                85
            )
        )
        movies.add(
            MovieEntity(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "Mortal Kombat",
                false,
                "Mortal Kombat",
                mutableListOf(28, 14, 12),
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "/mPBI506o7gITnjoSkcyPneK9s8n.jpg",
                "2021-04-07",
                1959.572,
                7.6,
                460465,
                false,
                2776
            )
        )
        movies.add(
            MovieEntity(
                "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                "",
                "Wrath of Man",
                false,
                "Wrath of Man",
                mutableListOf(28, 80),
                "/YxopfHpsCV1oF8CZaL4M3Eodqa.jpg",
                "/77tui163estZrQ78NBggqDB4n2C.jpg",
                "2021-04-22",
                1763.388,
                7.9,
                637649,
                false,
                490
            )
        )
        movies.add(
            MovieEntity(
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "",
                "Tom Clancy's Without Remorse",
                false,
                "Tom Clancy's Without Remorse",
                mutableListOf(28, 53, 10752),
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "/dZznHzmG1yJB2xdbEiiChmDJ1BD.jpg",
                "2021-04-29",
                1698.182,
                7.2,
                567189,
                false,
                1065
            )
        )
        movies.add(
            MovieEntity(
                "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
                "",
                "Those Who Wish Me Dead",
                false,
                "Those Who Wish Me Dead",
                mutableListOf(53, 18, 28, 9648),
                "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
                "/iDdpiUnCeXvBmrkBFpL6lKsZt33.jpg",
                "2021-05-05",
                1642.844,
                7.0,
                578701,
                false,
                350
            )
        )
        movies.add(
            MovieEntity(
                "Working in the shadow of an esteemed police veteran, brash Detective Ezekiel “Zeke” Banks and his rookie partner take charge of a grisly investigation into murders that are eerily reminiscent of the city’s gruesome past.  Unwittingly entrapped in a deepening mystery, Zeke finds himself at the center of the killer’s morbid game.",
                "",
                "Spiral: From the Book of Saw",
                false,
                "Spiral: From the Book of Saw",
                mutableListOf(53, 27, 9648),
                "/lcyKve7nXRFgRyms9M1bndNkKOx.jpg",
                "/7JENyUT8ABxcvrcijDBVpdjgCY9.jpg",
                "2021-05-12",
                1640.001,
                6.6,
                602734,
                false,
                225
            )
        )
        movies.add(
            MovieEntity(
                "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
                "",
                "A Quiet Place Part II",
                false,
                "A Quiet Place Part II",
                mutableListOf(878, 53, 27),
                "/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
                "/z2UtGA1WggESspi6KOXeo66lvLx.jpg",
                "2021-05-21",
                1385.198,
                7.4,
                520763,
                false,
                148
            )
        )
        return MovieResponse(1, 500, movies, 10000)
    }

    fun generateDummyTv(): TvResponse {
        val tv = ArrayList<TvEntity>()

        tv.add(
            TvEntity(
                "2016-01-25",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "en",
                mutableListOf(80, 10765),
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                mutableListOf("US"),
                "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                "Lucifer",
                1574.868,
                8.5,
                "Lucifer",
                63174,
                9106

            )
        )
        tv.add(
            TvEntity(
                "2020-01-31",
                "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                "no",
                mutableListOf(18, 10765, 9648),
                "/xUtOM1QO4r8w8yeE00QvBdq58N5.jpg",
                mutableListOf("NO"),
                "/wu444tM9YBllq9UcBv5TeidO3j3.jpg",
                "Ragnarok",
                1041.024,
                8.0,
                "Ragnarok",
                91557,
                452

            )
        )
        tv.add(
            TvEntity(
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "en",
                mutableListOf(18, 10765),
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                mutableListOf("US"),
                "/9Jmd1OumCjaXDkpllbSGi2EpJvl.jpg",
                "The Flash",
                1094.528,
                7.7,
                "The Flash",
                60735,
                7764

            )
        )
        tv.add(
            TvEntity(
                "2017-09-25",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "en",
                mutableListOf(18),
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                mutableListOf("US"),
                "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                "The Good Doctor",
                966.392,
                8.6,
                "The Good Doctor",
                71712,
                8580

            )
        )
        tv.add(
            TvEntity(
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                mutableListOf(10765, 10759, 18, 10768),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                mutableListOf("US"),
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                783.789,
                7.9,
                "The Falcon and the Winter Soldier",
                88396,
                5779

            )
        )

        return TvResponse(1, 500, tv, 10000)
    }

    fun generateDummyDetailMovie(): DetailMovieResponse {
        return DetailMovieResponse(
            "en",
            "tt5034838",
            false,
            "Godzilla vs. Kong",
            "/oboBn4VYB79uDxnyIri0Nt3U3N2.jpg",
            36718668,
            emptyList(),
            1913.072,
            emptyList(),
            399566,
            5876,
            200000000,
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "Godzilla vs. Kong",
            113,
            "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            emptyList(),
            emptyList(),
            "2021-03-24",
            8.1,
            BelongsToCollection(),
            "One Will Fall",
            false,
            "https://www.godzillavskong.net/",
            "Released"
        )
    }

    fun generateDummyDetailTv(): DetailTvResponse {
        return DetailTvResponse(
            "en",
            151,
            emptyList(),
            "Scripted",
            "/9Jmd1OumCjaXDkpllbSGi2EpJvl.jpg",
            emptyList(),
            1094.528,
            emptyList(),
            60735,
            7,
            7765,
            "2014-10-07",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            emptyList(),
            emptyList(),
            emptyList(),
            LastEpisodeToAir(),
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            emptyList(),
            emptyList(),
            emptyList(),
            "The Flash",
            7.7,
            "The Flash",
            "The fastest man alive.",
            emptyList(),
            NextEpisodeToAir(),
            true,
            "2021-05-25",
        )
    }
}