package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	int countByMemberMailAndMemberPwd(String memberMail, String memberPwd);
	
	Member findByMemberMail(String memberMail);
}
