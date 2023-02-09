package com.java.entity;

import java.util.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "food")
@ToString
@Entity
@Getter
@Setter
public class Food {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="food_Code", nullable = false)
	private String foodCode;	// 음식점코드
	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "member_id") private Member member;
	 */
	
	@Column(name = "food_name", nullable = false)
	private String foodName;	// 음식점 명
	
	@Column(name = "food_addr", nullable = false)
	private String foodAddr;	// 음식점 주소
	
	private String foodArea;	// 음식점 지역
	
	@Column(name = "food_phone", nullable = false)
	private String foodPhone;	// 음식점 전화번호
	
	@Column(name = "food_kind", nullable = false)
	private String foodKind;	// 음식 분류  ex) 한식, 중식, 
	
	@Column(name = "food_menu")
	private String foodMenu;	// 음식 대표메뉴
	
	@Column(name = "food_time")
	private String foodTime;	// 음식점 영업시간
	
	@Column(name = "food_break")
	private String foodBreak;	// 음식점 휴일
	
	@Lob
	@Column(name = "food_intro")
	private String foodIntro;	// 음식점 소개
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "food_date", nullable = false)
	private Date foodDate;	// 음식점 등록일
	
	@Column(name = "food_read", nullable = false)
	private int foodRead;	// 음식점 조회 카운트
	
	//@Enumerated(EnumType.STRING)
	@Column(name = "food_status")
	private String foodStatus;	//	음식점 상태  ex) y : 완료, n : 검토중	
	// private FoodStatus foodStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_code")
	private Member member;	// 등록자 
	



}
