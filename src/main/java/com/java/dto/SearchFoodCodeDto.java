package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @작성자 : 전지원
 * @작업일 : 2019. 12. 14.
 * @작업 내용 : coupon insert시 필요한 foodcode 가져오는 SearchFoodCodeDto 생성
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFoodCodeDto {
	private String foodCode;
	private String foodName;
	private String foodAddr;

	
}
