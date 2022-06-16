package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// field
	@Autowired // 제어의 역전을 당했다
	private PhoneService phoneService; // = new PhoneDao(); X --> 주입해줘

	// constructor
	// method - g/s
	// method - general

	// list (리스트)
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) { // Model은 미리 만들어져 있는 상자라고 생각하면 됨

		System.out.println("PhoneController>list()");

		// service 통해 list 불러오기
		List<PersonVo> personList = phoneService.getPersonList();

		// DispatcherServlet에 데이터를 보내야함 --> request attribute에 데이터를 넣는다
		model.addAttribute("personList", personList);

		return "list";
	}

	// writeForm (등록폼)
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

		System.out.println("PhoneController>writeForm()");

		return "writeForm";
	}

	// write (등록)
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {

		System.out.println("PhoneController>write()");

		// Service 통해 dao 메소드 불러오기
		phoneService.personInsert(personVo);

		// redirect // return은 때려죽여도 forward 임 / redirect: 먼저 써주면 됨
		return "redirect:/list";
	}

	// delete
	@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int no) { // {no}를 파라미터로 사용하는 메소드 @PathVariable

		System.out.println("PhoneController>delete()");

		// Service 통해서 dao 메소드 불러오기
		phoneService.personDelete(no);

		return "redirect:/list";
	}

	// updateForm (수정폼)
	@RequestMapping(value = "/updateForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(@PathVariable("no") int no, Model model) {

		System.out.println("PhoneController>updateForm()");

		// Service 통해 dao 메소드 불러오고 vo에 담기
		PersonVo personVo = phoneService.getPerson(no);

		// Attribute
		model.addAttribute("personVo", personVo);

		return "updateForm";
	}

	// update (수정)

	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {

		System.out.println("PhoneController>update()");

		// dao로 저장하기
		phoneService.personUpdate(personVo);

		return "redirect:/list";
	}

	/*
	 * // write (등록)
	 * 
	 * @RequestMapping(value = "/write2", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String write2(@RequestParam("name") String
	 * name, @RequestParam("hp") String hp, @RequestParam("company") String company) {
	 * 
	 * System.out.println("PhoneController>write2()");
	 * 
	 * // 파라미터 꺼내기 
	 * System.out.println(name); 
	 * System.out.println(hp);
	 * System.out.println(company);
	 *
	 * // vo로 묶기 PersonVo personVo = new PersonVo(name, hp, company);
	 * System.out.println(personVo);
	 * 
	 * // dao로 저장하기 int count = phoneDao.personInsert(personVo);
	 * System.out.println(count);
	 * 
	 * // redirect // return은 때려죽여도 forward 임 / redirect: 먼저 써주면 됨 return
	 * "redirect:/list"; }
	 * 
	 * @RequestMapping(value = "/delete2", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String delete2(@RequestParam("no") int personId)
	 * {
	 * 
	 * System.out.println("PhoneController>delete2()");
	 * 
	 * // DAO 통해서 삭제 메소드 가져오기 int count = phoneDao.personDelete(personId);
	 * System.out.println(count);
	 * 
	 * return "redirect:/list"; }
	 * 
	 * // test (테스트)
	 * 
	 * @RequestMapping(value = "/test", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String test() {
	 * 
	 * System.out.println("PhoneController>test()");
	 * 
	 * return "test"; }
	 */
}
