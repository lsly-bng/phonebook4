package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Service
public class PhoneService {

	// field
	@Autowired
	private PhoneDao phoneDao;

	// constructor
	// method - g/s
	// method - general

	// list (리스트)
	public List<PersonVo> getPersonList() {

		List<PersonVo> pList = phoneDao.getPersonList();

		return pList;
	}

	// write (등록)
	public int personInsert(PersonVo pVo) {

		int count = phoneDao.personInsert(pVo);

		return count;
	}

	// delete (삭제)
	public int personDelete(int no) {

		int count = phoneDao.personDelete(no);

		return count;
	}

	// updateForm (수정폼 : 수정 데이터 불러오기)
	public PersonVo getPerson(int no) {

		PersonVo pVo = phoneDao.getPerson(no);

		return pVo;
	}

	// update (수정)
	public int personUpdate(PersonVo pVo) {

		int count = phoneDao.personUpdate(pVo);

		return count;
	}
}
