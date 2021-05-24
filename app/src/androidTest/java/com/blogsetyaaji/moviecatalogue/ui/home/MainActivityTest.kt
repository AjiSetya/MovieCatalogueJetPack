package com.blogsetyaaji.moviecatalogue.ui.home


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.utils.MovieDummy
import com.blogsetyaaji.moviecatalogue.utils.TvDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = MovieDummy.generateDummyMovies()[0]
    private val dummyTV = TvDummy.generateDummyTv()[0]

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withText(R.string.movie)).perform(ViewActions.click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                MovieDummy.generateDummyMovies().size - 1
            )
        )
    }

    @Test
    fun loadDetailMovie(){
        onView(withText(R.string.movie)).perform(ViewActions.click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.text_name_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_name_movie)).check(matches(withText(dummyMovie.name)))
        onView(withId(R.id.text_desc_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc_movie)).check(matches(withText(dummyMovie.overview)))
        onView(withId(R.id.vote_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail_movie)).check(matches(withText(dummyMovie.rating.toString())))
        onView(withId(R.id.img_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_back)).perform(ViewActions.click())
    }

    @Test
    fun loadTv(){
        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                TvDummy.generateDummyTv().size - 1
            )
        )
    }

    @Test
    fun loadDetailTv(){
        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.text_name_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.text_name_tv)).check(matches(withText(dummyTV.name)))
        onView(withId(R.id.text_desc_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc_tv)).check(matches(withText(dummyTV.overview)))
        onView(withId(R.id.vote_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail_tv)).check(matches(withText(dummyTV.rating.toString())))
        onView(withId(R.id.img_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_back)).perform(ViewActions.click())
    }

}