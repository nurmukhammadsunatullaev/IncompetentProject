package org.undp.incompetent.models;

import com.google.common.collect.ImmutableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user_table")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_login",unique = true)
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "role_name")
    private String role;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private CourtEntity courtEntity;

    @Column(name = "blocked_date")
    private Date blockedDate;

    public Date getBlockedDate() {
        return blockedDate;
    }

    public void setBlockedDate(Date blockedDate) {
        this.blockedDate = blockedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }



    @Override
    public boolean isEnabled() {
        return blockedDate == null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAuthorities() {
        return ImmutableList.of((GrantedAuthority) () -> role);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CourtEntity getCourtEntity() {
        return courtEntity;
    }

    public void setCourtEntity(CourtEntity courtEntity) {
        this.courtEntity = courtEntity;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
