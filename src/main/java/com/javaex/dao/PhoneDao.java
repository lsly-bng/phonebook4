package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	public List<PersonVo> getPersonList() {
		System.out.println("PhoneDao>getPersonList()");

		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		System.out.println(personList);

		return personList;
	}

	// 사람추가
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao>personInsert()");

		int count = sqlSession.insert("phonebook.personInsert", personVo);

		return count;
	}

	// 사람 삭제
	public int personDelete(int no) {

		System.out.println("PhoneDao>personDelete()");

		int count = sqlSession.delete("phonebook.personDelete", no);

		return count;
	}

	// 1명 정보 가져오기
	public PersonVo getPerson(int no) {
		System.out.println("PhoneDao>getPerson()");

		PersonVo pVo = sqlSession.selectOne("phonebook.selectOne", no);

		return pVo;
	}

	// 사람 수정
	public int personUpdate(PersonVo pVo) {
		System.out.println("PhoneDao>personUpdate()");

		int count = sqlSession.update("phonebook.personUpdate", pVo);

		return count;
	}

}