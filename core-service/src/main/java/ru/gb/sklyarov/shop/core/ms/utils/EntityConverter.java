package ru.gb.sklyarov.shop.core.ms.utils;

import org.springframework.stereotype.Component;
import ru.gb.sklyarov.shop.common.dtos.CommentDto;
import ru.gb.sklyarov.shop.common.dtos.ProductDto;
import ru.gb.sklyarov.shop.common.dtos.StatisticDto;
import ru.gb.sklyarov.shop.common.dtos.UserDto;
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

    public CommentDto commentToDto(Comment comment) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return new CommentDto(comment.getId(), dateFormat.format(comment.getCreatedAt()), comment.getUser().getUsername(), comment.getComment(), comment.getProduct().getId(), comment.getUser().getId());
    }

    public UserDto userToDto(User user) {
        StringBuilder role_list = new StringBuilder();
        for (Role role : user.getRoles()) {
            role_list.append(role.getName()).append(',');
        }
        for (Role role : user.getRoles()) {
            role.getAuthorities().forEach(authority -> role_list.append(authority.getName()).append(','));
        }
        role_list.setLength(role_list.length() - 1);

        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getPassword(), user.getEmail(), role_list.toString());
    }
}
