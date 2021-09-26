package ru.gb.sklyarov.shop.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.services.ProductService;
import ru.gb.sklyarov.shop.ws.products.GetAllProductsRequest;
import ru.gb.sklyarov.shop.ws.products.GetAllProductsResponse;
import ru.gb.sklyarov.shop.ws.products.GetProductByIdRequest;
import ru.gb.sklyarov.shop.ws.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.gb.ru/sklyarov/shop/ws/products";
    private final ProductService productService;

     /*
        Пример запроса: POST http://localhost:8189/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.gb.ru/sklyarov/shop/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                    <f:id>1</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>

        или

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.gb.ru/sklyarov/shop/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>

     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductByIdResponse(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.findByIdSoap(request.getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found!")));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsResponse(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAllSoap().forEach(response.getProducts()::add);
        return response;
    }
}