package com.faizan.hospitalManagementSystem.entity;

import com.faizan.hospitalManagementSystem.entity.type.AuthProviderType;
import com.faizan.hospitalManagementSystem.entity.type.RoleType;
import com.faizan.hospitalManagementSystem.security.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.AuthProvider;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name="users", indexes = {
        @Index(name = "idx_provider_id_provider_type", columnList = "providerId, providerType" )
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true, nullable = false)
    private String username;

    private String password;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType providerType;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<RoleType> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
//                .collect(Collectors.toSet());

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permission = RolePermissionMapping.getAuthoritiesForRole(role);
                    authorities.addAll(permission);
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                }
        );

        return authorities;
    }
}
