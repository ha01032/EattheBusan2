package com.java.repository;


import com.java.entity.Image;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

//Query에 name속성에 해당하는 쿼리/Dto매핑을 해당 Entity에서 @SqlResultSetMapping로 수행

public interface ImageRepository extends JpaRepository<Image, Long>,
        QuerydslPredicateExecutor<Image> {
	
	@Modifying
	@Transactional
	@Query(value =
			"INSERT INTO image(image_code, refer_Code, image_name, image_size, image_path) " +					
			"VALUES ('image'||LPAD(SEQimage.nextval,4,0), :referCode, :imageName, :imageSize, :imagePath)",					
			nativeQuery = true)
	void couponImageInsert
	(@Param("referCode") String referCode, @Param("imageName") String imageName, @Param("imageSize") Long imageSize, @Param("imagePath") String imagePath);

			
}












