package com.training.victor.fallingwords.test.main

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.victor.fallingwords.ParentInstrumentalTest
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.ui.MainActivity
import com.training.victor.fallingwords.ui.SplashActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest: ParentInstrumentalTest() {
    @Rule
    val splashActivityTestRule: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java)
    private lateinit var splashActivity: SplashActivity

    @Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private lateinit var mainActivity: MainActivity


    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        Intents.init()

//        splashActivityTestRule.launchActivity(Intent())
//        splashActivity = splashActivityTestRule.activity
    }

    @After
    fun tearDown() {
        Intents.release()
//        mainActivity.finishAffinity()
    }



    // --------------------------------------------- Test cases ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    @Given("app is launched")
    fun app_is_launched() {
        splashActivityTestRule.launchActivity(Intent())
        splashActivity = splashActivityTestRule.activity
    }

    @When("a shown key word is shown")
    fun a_shown_key_word_is_shown() {
        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.name))
        onView(withId(R.id.txtShownText)).check(matches(isDisplayed()))
        onView(withId(R.id.txtShownText)).check(matches(not(withText(""))))
    }

    @And("right translation word is shown")
    fun right_translation_word_is_shown() {
        onView(withId(R.id.txtFallingWord)).check(matches(not(withText(""))))
    }

    @And("user press right button")
    fun user_press_right_button() {
        onView(withId(R.id.btnRight)).perform(click())
    }

    @Then("score label shows current score")
    fun score_label_shows_current_score() {
        onView(withId(R.id.txtWorng)).check(matches(withText("1")))
        onView(withId(R.id.txtSkip)).check(matches(withText("1")))
        Thread.sleep(5000)
    }
}
