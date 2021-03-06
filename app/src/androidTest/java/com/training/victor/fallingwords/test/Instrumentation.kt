package com.training.victor.fallingwords.test

import android.os.Bundle
import android.support.test.runner.MonitoringInstrumentation
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore

//@CucumberOptions(features = ["features/first_start.feature"], glue = ["com.training.victor.fallingwords.test.splash"], tags = ["@test1"])
@CucumberOptions(features = ["features/first_start.feature"], glue = ["com.training.victor.fallingwords.test.main"], tags = ["@test2"])
class Instrumentation: MonitoringInstrumentation() {
    private val instrumentationCore = CucumberInstrumentationCore(this)

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        instrumentationCore.create(arguments)
        start()
    }

    override fun onStart() {
        super.onStart()
        waitForIdleSync()
        instrumentationCore.start()
    }
}