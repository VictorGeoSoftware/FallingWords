package com.training.victor.fallingwords.test

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.victor.fallingwords.ParentInstrumentalTest
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.ui.SplashActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class SplashPresenterTest: ParentInstrumentalTest() {
    @Rule
    val splashActivityTestRule: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java)
    private lateinit var splashActivity: SplashActivity

    @Inject
    lateinit var dataManager: DataManager

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
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
        onView(ViewMatchers.withId(R.id.txtSplashText))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @When("all data is prepared")
    fun all_data_is_prepared() {
        dataManager.loadDataFromJsonAndFeedDataBase()
        dataManager.getDataBaseItemCount()

    }

    @Then("the main activity is launched")
    fun the_main_activity_is_launched() {
        onView(ViewMatchers.withId(R.id.txtShownText))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}
