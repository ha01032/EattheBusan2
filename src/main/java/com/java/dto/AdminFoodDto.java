package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminFoodDto {
	private int totalFoodCnt; // 전체 음식점 수
	private int	korFood;	//한식
	private int chnFood;	//중국식
	private int jpFood;		//일식
	private int wtFood;		//양식	
	private int cafe;		//카페
	private int etc;		//기타	
	

}
