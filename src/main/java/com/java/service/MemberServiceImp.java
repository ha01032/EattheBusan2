package com.java.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.java.aop.JejuAspect;
//import com.java.coupon.dto.CouponDto;
//import com.java.food.dao.FoodDao;
//import com.java.food.dto.FoodDto;
//import com.java.food.dto.FoodReviewDto;
import com.java.repository.MemberRepository;
import com.java.dto.MemberDto;
import com.java.entity.Member;
//import com.java.member.dto.MemberFavoriteDto;
//import com.java.purchase.dao.PurchaseDao;
//import com.java.purchase.dto.PurchaseListDto;
//import com.java.review.dto.ReviewDto;

@Component
public class MemberServiceImp implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	//@Autowired
	//private PurchaseDao purchaseDao;
	

	
	// 이메일 로그인 성공 과정
	@Override
	public void memberMailLoginOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String memberPwd=request.getParameter("pwd");
		String memberMail=request.getParameter("mail");
		
		int check = memberRepository.countByMemberMailAndMemberPwd(memberMail,memberPwd);
		Member member = null;
		MemberDto memberDto = null;
		if (check == 1) {
			member = memberRepository.findByMemberMail(memberMail);
			memberDto = MemberDto.of(member);
			//JejuAspect.logger.info(JejuAspect.logMsg + memberDto.toString());
		}
		
		//JejuAspect.logger.info(JejuAspect.logMsg + check);
		mav.addObject("check", check);
		mav.addObject("memberDto", memberDto);
		mav.setViewName("member/mailLoginOk.tiles");
	}
	
	
	
	
}



