package com.jpetstore.cucumber.steps;

import com.jpetstore.cucumber.steps.serenity.PetStoreSteps;
import com.jpetstore.utils.PetCategories;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class LoginSteps extends ScenarioSteps {

    @Steps
    PetStoreSteps shopper;

    @Managed
    WebDriver driver;

    /*
     * Login Step definations
     */
    @Given("I Login to the application with valid (.*) and (.*)")
    public void I_login_to_the_application(String userName, String password) throws InterruptedException {
        shopper.navigateToLoginPage();
        shopper.doLogin(userName, password);
    }

    @When("^i search for a (.*) it must show up in the search results$")
    public void i_search_for_a_Bulldog_it_must_show_up_in_the_search_results(String petType) {

        shopper.searchForProduct(petType);
        shopper.selectProductFromSearchTable(petType);
    }

    @When("^I view details about the pet (>*) and add it to cart$")
    public void i_view_details_about_the_pet_Male_Adult_Bulldog_and_add_it_to_cart(String pet) {

        shopper.addToCartSpecificProduct(pet);
    }

    @When("^I proceed to checkout$")
    public void i_proceed_to_checkout() {

        shopper.clickOnProceedToCheckout();
    }

    @When("^I click on Continue$")
    public void i_click_on_Continue() {

        shopper.clickOnContinueBtn();
    }

    @When("^I submit the order, the store must provide me a confirmation message on the placed order$")
    public void i_submit_the_order_the_store_must_provide_me_a_confirmation_message_on_the_placed_order() {

        shopper.clickOnConfirmBtn();
        shopper.verifyIfOrderSubmitted();
    }


    @And("^I enter my payment details (.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*)$")
    public void i_enter_my_payment_details_(String cardType, String cardNumber, String expiryDate, String firstname,
                                            String lastname, String addr1, String addr2, String city, String state, String zip, String country)
            throws Throwable {

        shopper.enterPaymentAndBillingDetails(cardType, cardNumber, expiryDate, firstname,
                lastname, addr1, addr2, city, state, zip, country);
    }

    @And("^I view details about the pet (.+) and add it to cart$")
    public void i_view_details_about_the_pet_and_add_it_to_cart(String pet) throws Throwable {

        shopper.addToCartSpecificProduct(pet);
    }


    @When("^I click on a category, then a category is displayed$")
    public void i_click_on_a_category_then_a_category_is_displayed(DataTable arg1) throws InterruptedException {


        List<String> searchVal = arg1.asList(String.class);

        for (String s : searchVal) {
            shopper.navigateToProductCategory(PetCategories.valueOf(s));
        }
    }

}
