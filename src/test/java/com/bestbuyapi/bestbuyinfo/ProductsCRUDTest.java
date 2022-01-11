package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.testdata.TestBase;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {

    public static String name = "Reading books"+ TestUtils.getRandomValue();
    public static String type = "ABC"+TestUtils.getRandomValue();
    public static double price = 5.49;
    public static String upc = "041333244019";
    public static int shipping = 0;
    public static String description = "Adventure Books"+TestUtils.getRandomValue();
    public static String manufacturer = "Disney";
    public static String model = "MN2400B4Z";
    public static String url = "htt://www.bestbuy.com/site/Amazon-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    public static String image = "htt://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    public static int productID;

@Steps
ProductsSteps productsSteps;


    @Title("Create new product")
    @Test
    public void test009(){

        productsSteps.createProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image).log().all().statusCode(201);

    }

    @Title("Verify new product has been created")
    @Test
    public void test010(){

        HashMap<String,Object> product = productsSteps.verifyProductInformation(name);

        productID = (int) product.get("id");

        Assert.assertThat(product,hasValue(name));

    }

    @Title("Change product name and verify change in the application")
    @Test
    public void test011(){
        name = name+TestUtils.getRandomValue();

        productsSteps.changeProduct(productID,name, type, price, upc, shipping, description, manufacturer, model, url, image).log().all();

    }

    @Title("Delete product and verify product has been deleted from application")
    @Test
    public void test012(){

        productsSteps.deleteProduct(productID).log().all().statusCode(200);
        productsSteps.verifyProductDeleted(productID).log().all().statusCode(404);

    }




}
