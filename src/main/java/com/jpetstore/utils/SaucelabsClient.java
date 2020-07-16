package com.jpetstore.utils;

import cucumber.api.Scenario;

public interface SaucelabsClient {

    void updateCurrentJob(Scenario scenario);
}
