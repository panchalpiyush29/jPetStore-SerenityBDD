package com.jpetstore.cucumber.steps;

import com.github.javafaker.Faker;
import com.jpetstore.cucumber.steps.serenity.PetStoreSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class UserRegistrationSteps extends ScenarioSteps {

    @Steps
    PetStoreSteps shopper;

    @Given("I navigate to the registration page")
    public void I_Navigate_To_Registration_page() {

        shopper.navigateToLoginPage();
        shopper.navigateToRegistrationPage();
    }

    // And I add new user information

    @And("I add new user information")
    public void I_add_new_user_info() {

        Faker faker = new Faker();
        String userName = "test" + faker.number().randomNumber(10, false);
        Serenity.setSessionVariable("userName").to(userName);

        String password = faker.internet().password();
        Serenity.setSessionVariable("password").to(password);

        String repeatPassword = password;

        shopper.addNewUserInformation(userName, password, repeatPassword);
    }

    @Given("^I add account information$")
    public void i_add_account_information() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        Serenity.setSessionVariable("firstName").to(firstName);

        String lastName = faker.name().lastName();
        String emailId = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String addr1 = faker.address().buildingNumber();
        String addr2 = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode();
        String country = faker.address().country();

        shopper.addAccountInformation(firstName, lastName, emailId, phoneNumber, addr1, addr2, city, state, zipCode,
                country);
    }

    @Given("^I add profile information$")
    public void i_add_profile_information() {

        shopper.addProfileInformation("english", "DOGS", true, true);
    }

    @Given("^I save my information$")
    public void i_save_my_information() {

        shopper.clickSaveAccountInformation();
    }

    @When("^I login with my credentials$")
    public void i_login_with_my_credentials() {

        String userName = Serenity.sessionVariableCalled("userName");
        String password = Serenity.sessionVariableCalled("password");

        shopper.doLogin(userName, password);
    }

    @Then("^I must be able to view the welcome greeting with my name$")
    public void i_must_be_able_to_view_the_welcome_greeting_with_my_name() {

        String result = shopper.getGreetingMessage();

        assertEquals("Welcome " + Serenity.sessionVariableCalled("firstName").toString() + "!", result);

    }
}
