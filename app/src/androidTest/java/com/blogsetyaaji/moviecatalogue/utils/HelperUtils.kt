package com.blogsetyaaji.moviecatalogue.utils

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import com.blogsetyaaji.moviecatalogue.R
import org.hamcrest.Matcher

class HelperUtils {
    companion object {
        fun getTitleRecyclerViewMovie(matcher: ViewInteraction, position: Int): String {
            var movieTitle = String()
            matcher.perform(object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isAssignableFrom(RecyclerView::class.java)
                }

                override fun getDescription(): String {
                    return "Title of current Item"
                }

                override fun perform(uiController: UiController, view: View) {
                    val rv = view as RecyclerView
                    movieTitle =
                        rv.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<TextView>(
                            R.id.title_item
                        )?.text.toString()
                }
            })
            return movieTitle
        }
    }
}