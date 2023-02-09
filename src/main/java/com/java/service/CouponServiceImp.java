package com.java.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

//import com.java.aop.JejuAspect;
import com.java.repository.CouponRepository;
import com.java.repository.ImageRepository;
import com.java.dto.CouponAdminDto;
import com.java.dto.CouponDto;
import com.java.dto.CouponReadDto;
import com.java.dto.SearchFoodCodeDto;
import com.java.entity.Coupon;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

//import com.java.dao.ImageDao;
import com.java.dto.ImageDto;

/**
 * @작성자 : 전지원
 * @작업일 : 2019. 12. 14.
 * @작업 내용 : insert & 식당코드 검색 작성
 */
@Component
@RequiredArgsConstructor
public class CouponServiceImp implements CouponService {

	@Autowired
	private final CouponRepository couponRepository;
	private final ImageRepository imageRepository;

	//@Autowired
	//private ImageDao imageDao;
	
	
	// 쿠폰리스트
	@Override
	public void couponList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber == null)
			pageNumber = "1";
		//JejuAspect.logger.info(JejuAspect.logMsg + "pageNumber: " + pageNumber);
		int currentPage = Integer.parseInt(pageNumber);

		// 쿠폰 리스트 카운트
		Long count = couponRepository.countBy();
		//JejuAspect.logger.info(JejuAspect.logMsg + "count: " + count);

		int scrollSize = 15;
		int startRow = (currentPage - 1) * scrollSize + 1;
		int endRow = currentPage * scrollSize;

		List<CouponDto> couponList = null;

		// 현재 날짜 출력
		Date today = new Date();
		//JejuAspect.logger.info(JejuAspect.logMsg + "date: " + today);

		if (count > 0) {
			// 쿠폰 리스트 가져오기

			couponList = couponRepository.couponList(startRow, endRow, today);
			

			//JejuAspect.logger.info(JejuAspect.logMsg + "couponList 사이즈: " + couponList.size());
			//JejuAspect.logger.info(JejuAspect.logMsg + "couponList : " + couponList.toString());
			mav.addObject("couponList", couponList);
		}
		mav.addObject("count", count);
		mav.addObject("pageNumber", pageNumber);
	}
	
	
	
	
	
	// 쿠폰 리스트(Ajax 새로고침)
		@Override
		@ResponseBody
		public String couponListAjax(ModelAndView mav) {
			Map<String, Object> map = mav.getModelMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");

			String pageNumber = request.getParameter("pageNumber");
			//JejuAspect.logger.info(JejuAspect.logMsg + "pageNumberAjax: " + pageNumber);

			int currentPage = Integer.parseInt(pageNumber);

			// 쿠폰 리스트 카운트
			Long count = couponRepository.countBy();
			//JejuAspect.logger.info(JejuAspect.logMsg + "count: " + count);

			int scrollSize = 15;
			int startRow = (currentPage - 1) * scrollSize + 1;
			int endRow = currentPage * scrollSize;
			//JejuAspect.logger.info(JejuAspect.logMsg + "startRow: " + startRow + " endRow:" + endRow);
			List<CouponDto> couponList = null;

			Date today = new Date();
			//JejuAspect.logger.info(JejuAspect.logMsg + "date: " + today);

			if (count > 0) {
				// 쿠폰 리스트 가져오기
				couponList = couponRepository.couponList(startRow, endRow, today);
				//JejuAspect.logger.info(JejuAspect.logMsg + "couponList 사이즈: " + couponList.size());
			}

			JSONArray arr = new JSONArray();
			for (CouponDto couponDto : couponList) {
				HashMap<String, Object> CommonMap = new HashMap<String, Object>();
				CommonMap.put("couponCode", couponDto.getCouponCode());
				CommonMap.put("foodCode", couponDto.getFoodCode());
				CommonMap.put("couponName", couponDto.getCouponName());
				CommonMap.put("couponStartdate", couponDto.getCouponStartdate());
				CommonMap.put("couponEnddate", couponDto.getCouponEnddate());
				CommonMap.put("couponCostori", couponDto.getCouponCostori());
				CommonMap.put("couponCostsale", couponDto.getCouponCostsale());
				CommonMap.put("imageName", couponDto.getImageName());
				CommonMap.put("couponSalerate", couponDto.getCouponSalerate());
				arr.add(CommonMap);
				//JejuAspect.logger.info(JejuAspect.logMsg + CommonMap.toString());
			}
			String jsonText = JSONValue.toJSONString(arr);
			//JejuAspect.logger.info(JejuAspect.logMsg + "JSONtext : " + jsonText);

			return jsonText;
		}
		
		// 쿠폰상세페이지
		@Override
		public void couponRead(ModelAndView mav) {
			Map<String, Object> map = mav.getModelMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");

			//int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			String couponCode = request.getParameter("couponCode");
			//JejuAspect.logger.info(JejuAspect.logMsg + "couponCode : " + couponCode);

			CouponReadDto couponReadDto = couponRepository.couponRead(couponCode);
			String couponIntro = couponReadDto.getCouponIntro();
			if(couponIntro != null) {
				couponReadDto.setCouponIntro(couponReadDto.getCouponIntro().replace("\r\n", "<br/>"));
			}
			//JejuAspect.logger.info(JejuAspect.logMsg + "couponDto : " + couponDto.toString());

			mav.addObject("couponDto", couponReadDto);
			mav.setViewName("coupon/couponDetail.tiles");
		}

		// 쿠폰리스트[관리자]
		@Override
		public void couponListAdmin(ModelAndView mav) {
			
			// 쿠폰 리스트 가져오기
			List<CouponAdminDto> couponList = couponRepository.couponListAdmin();
			//JejuAspect.logger.info(JejuAspect.logMsg + "couponList 사이즈: " + couponList.size());
			mav.addObject("couponList", couponList);
		}
		
		// 식당 코드 검색
		@Override
		public void searchFoodCode(ModelAndView mav) {
			Map<String, Object> map = mav.getModelMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");

			String foodName = request.getParameter("foodName");
			//JejuAspect.logger.info(JejuAspect.logMsg + "검색 내용: " + foodName);

			String cInsert = request.getParameter("cInsert");
			request.setAttribute("cInsert", cInsert);

			if (foodName != null) {
				// 검색어로 식당코드 찾기
				List<SearchFoodCodeDto> searchFoodCodeList = couponRepository.searchFoodCode(foodName);

				//JejuAspect.logger.info(JejuAspect.logMsg + "searchFoodCodeList 사이즈: " + searchFoodCodeList.size());
				mav.addObject("foodCodeList", searchFoodCodeList);
			}
		}
		
		
		// 쿠폰상품 등록
		@Override
		public void couponInsertOk(ModelAndView mav) {
			Map<String, Object> map = mav.getModelMap();
			CouponDto couponDto = (CouponDto) map.get("couponDto");
			String foodCode = couponDto.getFoodCode();
			String couponName = couponDto.getCouponName();
			String couponStartdate = couponDto.getCouponStartdate();
			String couponEnddate = couponDto.getCouponEnddate();
			int couponCostori = couponDto.getCouponCostori();
			int couponSalerate = couponDto.getCouponSalerate();
			int couponCostsale = couponDto.getCouponCostsale();
			String couponIntro = couponDto.getCouponIntro();
			
			int couponInsert = couponRepository.couponInsert(foodCode, couponName, couponStartdate, couponEnddate, couponCostori, couponSalerate, couponCostsale, couponIntro);
			Coupon coupon = couponRepository.findByCouponName(couponName);
			String referCode = coupon.getCouponCode();
			if (referCode != null) {
				MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
				MultipartFile upImage = request.getFile("imageFile");

				long imageSize = upImage.getSize();

				//String rootPath = request.getSession().getServletContext().getRealPath("/");
				//String attachPath = "resources/ftp/";
				String imageName = Long.toString(System.currentTimeMillis()) + "_" + upImage.getOriginalFilename();

				File path = new File("C:\\spring\\bootver\\eatthebusan_boot-chc.zip_expanded\\eatthebusan_boot-chc\\src\\main\\webapp\\resources\\ftp\\");
				path.mkdir();

				//JejuAspect.logger.info(JejuAspect.logMsg + "imageSize: " + imageSize);
				//JejuAspect.logger.info(JejuAspect.logMsg + "imageName: " + imageName);
				//JejuAspect.logger.info(JejuAspect.logMsg + "이미지 실제 저장경로: " + imagePath);

				if (path.exists() && path.isDirectory()) {
					File file = new File(path, imageName);
					System.out.println("CHECK");
					try {
						upImage.transferTo(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					String imagePath = path.getAbsolutePath() + imageName;
					ImageDto imageDto = new ImageDto();
					System.out.println("======" + referCode+ imageName+ imageSize+ imagePath);
					
					imageRepository.couponImageInsert(referCode, imageName, imageSize, imagePath);
					
					//JejuAspect.logger.info(JejuAspect.logMsg + "imageDto: " + imageDto.toString());
					//JejuAspect.logger.info(JejuAspect.logMsg + "check: " + check);
					mav.addObject("check", 1);
				}
			}
			mav.setViewName("coupon/couponInsertOk.tiles");
		}
		
		
				


	

}
