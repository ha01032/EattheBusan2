package com.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "review")
@ToString
@Entity
@Getter
@Setter

public class Review {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_code", nullable = false)
	private String reviewCode;	// 리뷰 코드
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_code")
	private Food food;	// 음식점 코드
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membe_code")
	private Member member;	// 멤버 코드
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "review_date" ,nullable = false)
	private Date reviewDate;	// 리뷰 등록일
	
	@Lob
	@Column(name = "review_cont" ,nullable = false)
	private String reviewCont;	// 리뷰 내용
	
	@Column(name = "review_score" ,nullable = false)
	private int reviewScore;	// 리뷰 점수	

}
