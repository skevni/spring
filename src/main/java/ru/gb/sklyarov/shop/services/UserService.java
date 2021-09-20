package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.sklyarov.shop.dtos.UserDto;
import ru.gb.sklyarov.shop.entities.Authority;
import ru.gb.sklyarov.shop.entities.Role;
import ru.gb.sklyarov.shop.entities.User;
import ru.gb.sklyarov.shop.repositories.UserRepository;

import javax.security.auth.message.AuthException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        Set<String> rights = new HashSet<>();
        Set<Authority> authorities = new HashSet<>();

        for (Role role : roles) {
            rights.add(role.getName());
            authorities.addAll(role.getAuthorities());
        }

        authorities.forEach(authority -> rights.add(authority.getName()));

        return rights.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Page<User> findAllUsers(int pageIndex, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    @Transactional
    public UserDto registration(UserDto userDto) throws AuthException {
        if (userDto.getPassword().equals(userDto.getConfirmation())) {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setUsername(userDto.getUsername());

            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            Collection<Role> default_role = userRepository.findDefaultRole();

            if (default_role.isEmpty()) {
                throw new AuthException("The role \"ROLE_USER\" was not found!");
            }
            user.setRoles(default_role);
            save(user);

            return new UserDto(user);
        }
        throw new AuthException("Passwords don't match!");
    }
}
