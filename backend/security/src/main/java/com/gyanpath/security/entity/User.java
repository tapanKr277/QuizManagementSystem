package com.gyanpath.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Short userId;
	
	@Column(name="username", unique = true, nullable = false, length = 20)
	private String username;

	@Column(name = "first_name", length = 30)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(name="password", nullable = false, length = 255)
	private String password;

	@Column(name="email", nullable = false, unique = true, length = 50)
	private String email;

	@Column(name = "phone_number", nullable = true, unique = true, length = 10)
	private String phoneNumber;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Role> roles = new HashSet<>();

	@Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
	private Boolean isActive = true;

	@Column(name = "is_verified", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
	private Boolean isVerified = false;

	@Column(name = "is_otp_verified", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
	private Boolean isOtpVerified = false;

	@Column(name = "otp_last_update", nullable = true)
	private LocalDateTime otpLastUpdate;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "last_update", nullable = false)
	private LocalDateTime lastUpdate;


}
