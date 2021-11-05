package ru.gb.sklyarov.shop.core.ms.paypal;

import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.sklyarov.shop.common.dtos.OrderDto;
import ru.gb.sklyarov.shop.common.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayPalService {
    private final WebClient orderServiceWebClient;

    @Transactional
    public OrderRequest createOrderRequest(Long orderId) {
        OrderDto orderDto = orderServiceWebClient.get()
                .uri("/api/v1/orders/" + orderId)
//               .header("username", "user")
                .retrieve()
                .bodyToMono(OrderDto.class)
                .block();
        if (orderDto != null) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.checkoutPaymentIntent("CAPTURE");

            ApplicationContext applicationContext = new ApplicationContext()
                    .brandName("Spring Web Store")
                    .landingPage("BILLING")
                    .shippingPreference("SET_PROVIDED_ADDRESS");
            orderRequest.applicationContext(applicationContext);

            List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
            PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                    .referenceId(orderId.toString())
                    .description("Spring Web Store Order")
                    .amountWithBreakdown(new AmountWithBreakdown().currencyCode("RUB").value(String.valueOf(orderDto.getTotalPrice()))
                            .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("RUB").value(String.valueOf(orderDto.getTotalPrice())))))
                    .items(orderDto.getCartItems().stream().map(orderItemDto -> new Item()
                                    .name(orderItemDto.getTitle())
                                    .unitAmount(new Money().currencyCode("RUB").value(String.valueOf(orderItemDto.getTotalPrice())))
                                    .quantity(String.valueOf(orderItemDto.getQuantity())))
                            .collect(Collectors.toList()))
                    .shippingDetail(new ShippingDetail()
                            .name(new Name().fullName(orderDto.getUsername()))
                            .addressPortable(new AddressPortable().addressLine1("8 Lenina St").addressLine2("Floor 7")
                                    .adminArea1("Moscow").adminArea2("Moscow")
                                    .postalCode("344000").countryCode("RU")));

            purchaseUnitRequests.add(purchaseUnitRequest);
            orderRequest.purchaseUnits(purchaseUnitRequests);

            return orderRequest;
        }

        throw new ResourceNotFoundException("Order #" + orderId + " not found!");
    }
}
