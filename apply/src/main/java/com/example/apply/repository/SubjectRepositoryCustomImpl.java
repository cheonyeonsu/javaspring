package com.example.apply.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.example.apply.dto.QSubjectDto;
import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.entity.QSubject;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class SubjectRepositoryCustomImpl implements SubjectRepositoryCustom {

	// 공식이니라
	private JPAQueryFactory queryFactory;

	public SubjectRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	// 현재 날짜로부터 이전날짜를 구해주는 메소드 <날짜 검색>
	private BooleanExpression regDtsAfter(String searchDateType) {

		LocalDateTime dateTime = LocalDateTime.now(); // 현재 날짜, 시간

		if (StringUtils.equals("all", searchDateType) || searchDateType == null)
			return null;
		else if (StringUtils.equals("1d", searchDateType))
			dateTime = dateTime.minusDays(1); // 현재 날짜로부터 1일 전
		else if (StringUtils.equals("1w", searchDateType))
			dateTime = dateTime.minusWeeks(1); // 현재 날짜로부터 1주일 전
		else if (StringUtils.equals("1m", searchDateType))
			dateTime = dateTime.minusMonths(1); // 현재 날짜로부터 1달 전
		else if (StringUtils.equals("6m", searchDateType))
			dateTime = dateTime.minusMonths(6); // 현재 날짜로부터 6개월 전

		return QSubject.subject.subjectStartDate.after(dateTime); // Q객체 리턴

	}

	private BooleanExpression subjectNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null : QSubject.subject.subjectName.like("%" + searchQuery + "%");
	}

	@Override
	public Page<SubjectDto> getApplyPage(SubjectSearchDto subjectSearchDto, Pageable pageable) {
		
		//Q클래스 안나올 때는 Debug as clean/install
		
		//select * from subject where subject_start_date >= ? and subject_name like '%?%' order by id desc;
		
		QSubject subject = QSubject.subject;

	  List<SubjectDto> content = queryFactory.select(
				  new QSubjectDto(
						  subject.subjectId, 
						  subject.subjectName,
						  subject.subjectStartDate, 
						  subject.subjectEndDate, 
						  subject.subjectDetail, 
						  subject.subjectTo))
				  .from(subject)
				  .where(regDtsAfter(subjectSearchDto.getSearchDateType()),
					  subjectNmLike(subjectSearchDto.getSearchQuery()))
				  .orderBy(subject.subjectId.desc())
				  //의미는 없는데 같이 써줘야 하는 아이들. 
				  .offset(pageable.getOffset())
				  .limit(pageable.getPageSize())
				  .fetch(); 
		  
		
		  
		  long total = queryFactory.select(Wildcard.count)
				  .from(subject)
				  .where(regDtsAfter(subjectSearchDto.getSearchDateType()),
						  subjectNmLike(subjectSearchDto.getSearchQuery()))
			  .fetchOne();

		  return new PageImpl<>(content,pageable,total); 
		
	}

}
