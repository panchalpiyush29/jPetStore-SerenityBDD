package com.jpetstore.cucumber.steps;

import com.github.javafaker.Faker;
import cucumber.api.java.en.When;
import io.restassured.filter.session.SessionFilter;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

public class CreateUserApiSteps extends ScenarioSteps {

    @Managed
    WebDriver driver;

    private static SessionFilter filter = new SessionFilter();

    @When("^I post to Actions api with valid user details$")
    public void iPostToActionsApiWithValidUserDetails() {

        Faker faker = new Faker();

        SerenityRest.given()
                .filter(filter)
                .get("http://localhost:8080/jpetstore/actions/Catalog.action");

        SerenityRest.given()
                .log()
                .all()
                .sessionId(filter.getSessionId())
                .formParam("account.address1", "WDrive")
                .formParam("account.address2", "10")
                .formParam("account.bannerOption", true)
                .formParam("account.city", "Kzoo")
                .formParam("account.country", "USA")
                .formParam("account.email", "th@sf.com")
                .formParam("account.favouriteCategoryId", "FISH")
                .formParam("account.firstName", "John")
                .formParam("account.lastName", "Doe")
                .formParam("account.languagePreference", "english")
                .formParam("account.zip", 01721)
                .formParam("account.phone", "3333333333")
                .formParam("account.state", "MI")
                .formParam("username", "test" + faker.idNumber())
                .formParam("password", "test")
                .formParam("repeatedPassword", "test")
                .formParam("newAccount", "Save+Account+Information")
                .when()
                .post("http://localhost:8080/jpetstore/actions/Account.action")
                .then()
                .log()
                .all();
    }
}
