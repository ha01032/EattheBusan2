package com.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.java.dto.MemberDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="member")
@Getter @Setter
@ToString
@DynamicInsert
public class Member {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_code")
	private String memberCode;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "member_date", nullable = false)
	private Date memberDate;
    
    @Column(unique = true)
	private String memberMail;
    
    	
	private String memberPwd;
    
    @Column(name="member_name")
	private String memberName;
    
	private String memberPhone;
    
    //@ColumnDefault("Y")
	private String memberStatus;
    
    private String memberKakao;
    


}
