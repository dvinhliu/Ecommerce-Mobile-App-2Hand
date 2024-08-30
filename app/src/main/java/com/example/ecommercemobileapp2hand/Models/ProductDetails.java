package com.example.ecommercemobileapp2hand.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ProductDetails implements Parcelable {
    @JsonProperty("product_details_id")
    private int product_details_id;
    @JsonProperty("product_id")
    private int product_id;
    private ProductColor productColor;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sale_price")
    private BigDecimal sale_price;
    @JsonProperty("details_created_at")
    private Timestamp createdAt;
    @JsonProperty("product_details_img_array")
    private ArrayList<ProductDetailsImg> imgDetailsArrayList;

    public ProductDetails() {
    }

    public ProductDetails(int product_details_id, int product_id, ProductColor productColor, String description, BigDecimal sale_price, Timestamp createdAt, ArrayList<ProductDetailsImg> imgDetailsArrayList) {
        this.product_details_id = product_details_id;
        this.product_id = product_id;
        this.productColor = productColor;
        this.description = description;
        this.sale_price = sale_price;
        this.createdAt = createdAt;
        this.imgDetailsArrayList = imgDetailsArrayList;
    }

    @JsonProperty("product_color_id")
    public void setProductColorId(int productColorId) {
        if (this.productColor == null) {
            this.productColor = new ProductColor();
        }
        this.productColor.setProduct_color_id(productColorId);
    }

    @JsonProperty("product_color_name")
    public void setProductColorName(String productColorName) {
        if (this.productColor == null) {
            this.productColor = new ProductColor();
        }
        this.productColor.setProduct_color_name(productColorName);
    }
    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

    public ArrayList<ProductDetailsImg> getImgDetailsArrayList() {
        return imgDetailsArrayList;
    }

    public void setImgDetailsArrayList(ArrayList<ProductDetailsImg> imgDetailsArrayList) {
        this.imgDetailsArrayList = imgDetailsArrayList;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getProduct_details_id() {
        return product_details_id;
    }

    public void setProduct_details_id(int product_details_id) {
        this.product_details_id = product_details_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSale_price() {
        return sale_price;
    }

    public void setSale_price(BigDecimal sale_price) {
        this.sale_price = sale_price;
    }

    protected ProductDetails(Parcel in) {
        product_details_id = in.readInt();
        product_id = in.readInt();
        productColor = in.readParcelable(ProductColor.class.getClassLoader());
        description = in.readString();
        sale_price = new BigDecimal(in.readString());
        createdAt = new Timestamp(in.readLong());
        imgDetailsArrayList = in.createTypedArrayList(ProductDetailsImg.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(product_details_id);
        dest.writeInt(product_id);
        dest.writeParcelable(productColor, flags);
        dest.writeString(description);
        dest.writeString(sale_price.toString());
        dest.writeLong(createdAt.getTime());
        dest.writeTypedList(imgDetailsArrayList);
    }
    public static final Creator<ProductDetails> CREATOR = new Creator<ProductDetails>() {
        @Override
        public ProductDetails createFromParcel(Parcel in) {
            return new ProductDetails(in);
        }

        @Override
        public ProductDetails[] newArray(int size) {
            return new ProductDetails[size];
        }
    };
}
