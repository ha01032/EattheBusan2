package com.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.service.AdminService;
import com.java.service.CouponService;


@Controller
@RequestMapping("/admin/*")
public class AdminController {
	//@Autowired
	//private FoodService foodService;
	
	//@Autowired
	//private ReviewService reviewService;
	
	@Autowired
	private CouponService couponService;
	
	//@Autowired
	//private PurchaseService purchaseService;
	
	@Autowired
	private AdminService adminService;
		
	// 관리자 로그인 페이지
	@GetMapping("login.go")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/login.empty");		
		return mav;		
	}
		
	// 관리자 메인페이지 이동
	@GetMapping("main.go")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		adminService.getMainData(mav);		
		return mav;		
	}
	
	// 관리자 로그인 후 메인페이지 이동
	@RequestMapping(value = "loginOk.go", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView loginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		adminService.adminLogin(mav);
		
		return mav;		
	}
		
	// 관리자 로그아웃
	@GetMapping("logout.go")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		return "admin/logout.empty";		
	}
	
	// 관리자 쿠폰페이지로 이동 및 리스트 불러오기
	@GetMapping("coupon.go")
	public ModelAndView coupon(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		couponService.couponListAdmin(mav);
		//mav 안에 couponList 다.ㅇ
		
		mav.setViewName("admin/coupon.admin");		
		return mav;		
	}
	
	
	




}