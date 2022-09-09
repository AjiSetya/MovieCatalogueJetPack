package com.blogsetyaaji.moviecatalogue.ui.home


import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.utils.EspressoIdlingResource
import com.blogsetyaaji.moviecatalogue.utils.HelperUtils.Companion.getTitleRecyclerViewMovie
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class MainActivityTest {
    private val randomNumber = Random.nextInt(0, 7)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withText(R.string.movie)).perform(ViewActions.click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                randomNumber
            )
        )
    }

    @Test
    fun loadDetailAndFavoriteMovie() {
        var titleMovie: String

        onView(withText(R.string.movie)).perform(ViewActions.click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                randomNumber
            )
        ).also {
            titleMovie = getTitleRecyclerViewMovie(it, randomNumber)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                randomNumber,
                ViewActions.click()
            )
        )
        onView(withId(R.id.text_name_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_name_movie)).check(matches(withText(titleMovie)))
        onView(withId(R.id.text_desc_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_detail_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_movie_favorite)).perform(ViewActions.click())
        onView(withId(R.id.detail_movie_back)).perform(ViewActions.click())

        onView(withId(R.id.item_fav)).perform(ViewActions.click())
        onView(withText(R.string.movie)).perform(ViewActions.click())
        onView(withId(R.id.rv_fav_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        ).also {
            titleMovie = getTitleRecyclerViewMovie(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.text_name_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_name_movie)).check(matches(withText(titleMovie)))
        onView(withId(R.id.text_desc_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_detail_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_movie_favorite)).perform(ViewActions.click())
        onView(withId(R.id.detail_movie_back)).perform(ViewActions.click())
    }

    @Test
    fun loadTv() {
        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                randomNumber
            )
        )
    }

    @Test
    fun loadDetailAndFavoriteTv() {
        var titleTv: String

        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                randomNumber
            )
        ).also {
            titleTv = getTitleRecyclerViewMovie(it, randomNumber)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                randomNumber,
                ViewActions.click()
            )
        )
        onView(withId(R.id.text_name_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.text_name_tv)).check(matches(withText(titleTv)))
        onView(withId(R.id.text_desc_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_detail_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_tv_favorite)).perform(ViewActions.click())
        onView(withId(R.id.detail_tv_back)).perform(ViewActions.click())

        onView(withId(R.id.item_fav)).perform(ViewActions.click())

        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_fav_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        ).also {
            titleTv = getTitleRecyclerViewMovie(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.text_name_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.text_name_tv)).check(matches(withText(titleTv)))
        onView(withId(R.id.text_desc_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_detail_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_tv_favorite)).perform(ViewActions.click())
        onView(withId(R.id.detail_tv_back)).perform(ViewActions.click())
    }
}