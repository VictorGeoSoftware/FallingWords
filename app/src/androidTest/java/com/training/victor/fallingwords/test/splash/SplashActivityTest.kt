package com.training.victor.fallingwords.test.splash

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.victor.fallingwords.ParentInstrumentalTest
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.ui.MainActivity
import com.training.victor.fallingwords.ui.SplashActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class SplashActivityTest: ParentInstrumentalTest() {
    @Rule
    val splashActivityTestRule: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java)
    private lateinit var splashActivity: SplashActivity

    @Inject
    lateinit var dataManager: DataManager

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        Intents.init()

        splashActivityTestRule.launchActivity(Intent())
        splashActivity = splashActivityTestRule.activity
    }

    @After
    fun tearDown() {
        Intents.release()
        splashActivity.finishAffinity()
    }



    // --------------------------------------------- Test cases ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    @Given("the splash view is shown")
    fun the_splash_view_is_shown() {

    }

    @When("all data is prepared")
    fun all_data_is_prepared() {
        dataManager.loadDataFromJsonAndFeedDataBase()
        val items = dataManager.getDataBaseItemCount()
        assertEquals(items, 295)
    }

    @Then("the main activity is launched")
    fun the_main_activity_is_launched() {
        Thread.sleep(1000)
        intended(hasComponent(MainActivity::class.java.name))
        onView(withId(R.id.txtShownText)).check(matches(isDisplayed()))
    }
}
