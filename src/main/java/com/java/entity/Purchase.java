package com.java.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.time.LocalDateTime;



import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase")
@Getter 
@Setter
public class Purchase {

    @Id 
    @Column(name = "purchase_code", length = 10)
	private String purchaseCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_code")
    private Coupon coupon;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member member;
    
    @Column(name = "purchase_phone", nullable = false, length = 20)
    private String purchasePhone;
    
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;
    
    @Column(name = "purchase_cost", nullable = false, length = 20)
    private int purchaseCost;
    
    @Column(name = "purchase_status", nullable = false, length = 20)
    private String purchaseStatus;


}