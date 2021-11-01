package ru.gb.sklyarov.shop.core.ms.utils;

import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.common.dtos.*;
import ru.gb.sklyarov.shop.core.ms.entities.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class EntityConverter {
    public StatisticDto statisticToDto(Statistic statistic) {
        return new StatisticDto(statistic.getService_name(), statistic.getDuration());
    }

    public ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public CommentDto commentToDto(Comment comment, String username) {
        if (username == null) {
            // TODO: взять имя пользователя по ID из auth-service
            username = "";
        }
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return new CommentDto(comment.getId(), dateFormat.format(comment.getCreatedAt()), username, comment.getComment(), comment.getProduct().getId(), comment.getUserId());
    }

}
