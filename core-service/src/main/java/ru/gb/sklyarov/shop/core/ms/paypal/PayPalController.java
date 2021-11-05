package ru.gb.sklyarov.shop.core.ms.paypal;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/paypal")
public class PayPalController {
    private final PayPalHttpClient payPalClient;
    private final WebClient orderServiceWebClient;
    private final PayPalService payPalService;

    @PostMapping("/create/{orderId}")
    public ResponseEntity<?> createOrder(@PathVariable Long orderId) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(payPalService.createOrderRequest(orderId));
        HttpResponse<Order> response = payPalClient.execute(request);

        return new ResponseEntity<>(response.result().id(), HttpStatus.valueOf(response.statusCode()));
    }

    @PostMapping("/capture/{payPalId}")
    public ResponseEntity<?> captureOrder(@PathVariable String payPalId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(payPalId);
        request.requestBody(new OrderRequest());

        HttpResponse<Order> response = payPalClient.execute(request);
        Order payPalOrder = response.result();
        if ("COMPLETED".equals(payPalOrder.status())) {
//            long orderId = Long.parseLong(payPalOrder.purchaseUnits().get(0).referenceId());
//            Optional<OrderDto> orderDto = Optional.ofNullable(orderServiceWebClient.get()
//                    .uri("/api/v1/orders/" + orderId).retrieve()
//                    .bodyToMono(OrderDto.class).block());
            // TODO: добавить изменение флага, что заказ оплачен и не выводить его еще раз к оплате
            //  перевести на другую страницу


            return new ResponseEntity<>("Order completed!", HttpStatus.valueOf(response.statusCode()));
        }
        return new ResponseEntity<>(payPalOrder, HttpStatus.valueOf(response.statusCode()));
    }
}
