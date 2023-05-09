package com.example.chanzyhebat.Model;

import java.util.List;

public class ProductResponse {
    private List<ProductRequest> productRequests;

    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }
}
