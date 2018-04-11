package com.kryptoblocks.commercex.pojo;

/**
 * Created by Admin on 20-02-2018.
 */

public class Related_Products {

    String product_name;
    int product_image;

    public Related_Products()
    {}

   public Related_Products(String prod_name, int prod_image)
    {
        this.product_name = prod_name;
        this.product_image = prod_image;
    }

    public String getProduct_name()
    {
        return product_name;
    }
    public void setProduct_name(String name)
    {
        this.product_name = name;
    }

    public int getProduct_image()
    {
        return product_image;
    }
    public void setProduct_image(int image)
    {
        this.product_image = image;
    }
}
