package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminFoodReadDto {
	private String foodCode; // 음식점 코드
	private String foodName; // 음식점 수
	private int	foodRead;	// 조회수
	private int rank;	// 순위		
	

}
