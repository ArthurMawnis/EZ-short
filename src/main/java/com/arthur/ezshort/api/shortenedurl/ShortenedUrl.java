package com.arthur.ezshort.api.shortenedurl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sh_url")
@Getter
@Setter
public class ShortenedUrl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "or_url")
	private String originalUrl;
	@Column(name = "token")
	private String token;
	@Column(name = "st_dt")
	private Date startDate;
	@Column(name = "en_dt")
	private Date endDate;
}
