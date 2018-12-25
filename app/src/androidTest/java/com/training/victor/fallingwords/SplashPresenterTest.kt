package com.training.victor.fallingwords

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.victor.fallingwords.ui.SplashActivity
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
class SplashPresenterTest: ParentInstrumentalTest() {
    @Rule
    val splashActivityTestRule = ActivityTestRule(SplashActivity::class.java)
    private lateinit var splashActivity: SplashActivity

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
    }



    // --------------------------------------------- Test cases ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    @Given("the splash view is shown")
    fun the_splash_view_is_shown() {
        onView(ViewMatchers.withId(R.id.txtSplashText))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @When("json file is ready")
    fun json_file_is_ready() {
        splashActivity.splashPresenter.loadAllData()
    }

    @And("data base is feeded with all json file info")
    fun data_base_is_feeded_with_all_json_file_info() {

    }

    @Then("the main activity is launched")
    fun the_main_activity_is_launched() {

    }
}
