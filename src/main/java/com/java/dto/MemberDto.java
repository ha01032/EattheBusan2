package com.java.dto;

import java.util.Date;

import com.java.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private String memberCode;
	private Date memberDate;
	private String memberMail;
	private String memberPwd;
	private String memberName;
	private String memberPhone;
	private String memberStatus;
	


	
	private static ModelMapper modelMapper = new ModelMapper();
    public static MemberDto of(Member member){
        return modelMapper.map(member,MemberDto.class);
    }
	
	
	

}
