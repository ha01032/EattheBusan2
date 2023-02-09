package com.java.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@SequenceGenerator(
        name="imageSEQ", //시퀀스 제너레이터 이름
        sequenceName="IMAGE_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
@Table(name = "image")
@Getter 
@Setter
@ToString
public class Image {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "image_code" , nullable = false)
    private String imageCode;

	
    @Column(name="refer_code" , nullable = false)
	private String referCode;
    
    
    @Column(name="image_name" , nullable = false)
	private String imageName;
    
    
    @Column(name="image_size" , nullable = false)
	private long imageSize;
    
    
    @Column(name="image_path" , nullable = false)
	private String imagePath;


    }

