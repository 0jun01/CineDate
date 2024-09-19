package com.tenco.movie.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tenco.movie.dto.CountProfileDTO;
import com.tenco.movie.dto.EventWriterDTO;
import com.tenco.movie.dto.NoticeWriterDTO;

import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.dto.UserWriterDTO;

import com.tenco.movie.dto.OnlyCountDTO;
import com.tenco.movie.dto.genresBookingsDTO;

import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.handler.exception.RedirectException;
import com.tenco.movie.repository.interfaces.AdminRepository;
import com.tenco.movie.repository.model.ConItems;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.Event;
import com.tenco.movie.repository.model.History;
import com.tenco.movie.repository.model.HistoryTimeLine;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPageService {

	@Autowired
	private AdminRepository adminRepository;

	private Event event;

	@Value("${file.upload-dir}")
	private String uploadDir;

	// -----------------------------------------------
	// 메인 시작

	public int countReview() {
		return adminRepository.reviewAdminCount();
	}

	public int countSell() {
		return adminRepository.sellAdminCount();
	}

	public int countMember() {
		return adminRepository.countAdminMemberAll();
	}
	
	public int countProfileAll() {
		return adminRepository.countAdminMemberAll();
	}
	
	public int countItem() {
		return adminRepository.itemAdminCount();
	}
	
	public int countBookings() {
		return adminRepository.bookingAdminCount();
	}
	
	public int sumSelling() {
		return adminRepository.sellAdminSum();
	}
	
	@Transactional
	public List<DateProfile> readMainProfile(){
		List<DateProfile> list = new ArrayList<>();
		
		list = adminRepository.readAdminProfile();
		
		return list;
	}
	
	@Transactional
	public List<History> readMainHistory(){
		List<History> list = new ArrayList<>();
		
		list = adminRepository.readAdminHistory();
		
		return list;
	}
	
	@Transactional
	public List<ConItems> readMainConItems(){
		List<ConItems> list = new ArrayList<>();
		
		list = adminRepository.findAdminItemAll();
		
		return list;
	}

	// 메인 끝
	// ------------------------------------------------
	// 공지시작
	@Transactional
	public void createNotice(NoticeWriterDTO dto) { // 공지 글쓰기
		int result = 0;

		try {
			result = adminRepository.insertAdmin(dto.toWrite());
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public void reCreateNotice(NoticeWriterDTO dto, int id) { // 공지 수정

		int result = 0;

		try {
			result = adminRepository.updateAdminById(dto.reWrite(id));
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Notice findById(Integer id) { // 공지 detail에서 id값 검색
		Notice notice = adminRepository.findAdminById(id);

		if (notice == null) {
			throw new DataDeliveryException("존재하지 않는 게시글 입니다", HttpStatus.BAD_REQUEST);
		}
		return notice;
	}

	@Transactional // 공지 삭제
	public void deleteNotice(int id) {

		int result = 0;

		try {
			result = adminRepository.deleteAdminById(id);
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional // 공지 페이징 처리
	public List<Notice> readNoticePage(int page, int size) {
		List<Notice> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = adminRepository.pageCountAdmin(limit, offset);
		return list;
	}

	@Transactional // 공지 검색 처리
	public List<Notice> searchNoticePage(String search, int page, int size) {
		List<Notice> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = adminRepository.findAdminByName(search, limit, offset);
		return list;
	}

	public int countNoticeAll() { // 공지 전체 카운팅
		return adminRepository.countAdminNoticeAll();
	}

	public int countNotice(String search) { // 공지 검색 카운팅
		return adminRepository.countAdminNotice(search);
	}

	// 공지
	// ----------------------------------------------
	// 이벤트

	@Transactional
	public List<Event> readEventPage(int page, int size) { // 이벤트 목록 불러오기 및 페이징처리
		List<Event> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = adminRepository.findEventAll(limit, offset);
		return list;
	}

	public Event findEventById(Integer id) { // 이벤트 detail 불러오기 위한 함수
		Event event = adminRepository.findEventById(id);

		if (event == null) {
			throw new DataDeliveryException("존재하지 않는 게시글 입니다", HttpStatus.BAD_REQUEST);
		}
		return event;
	}

	@Transactional
	public void createEvent(EventWriterDTO dto) { // 이벤트 작성
		int result = 0;

		if (dto.getMFileOne() != null && !dto.getMFileOne().isEmpty()) {
			String[] fileNames = uploadFile(dto.getMFileOne());
			dto.setOriginFileName(fileNames[0]);
			dto.setUploadFileName(fileNames[1]);

		}

		result = adminRepository.insertEvent(dto.toWrite());

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Transactional
	public void reCreateEvent(EventWriterDTO dto, int id) { // 공지 수정

		int result = 0;
		
		if (dto.getMFileOne() != null && !dto.getMFileOne().isEmpty()) {
			String[] fileNames = uploadFile(dto.getMFileOne());
			dto.setOriginFileName(fileNames[0]);
			dto.setUploadFileName(fileNames[1]);

		}


		try {
			result = adminRepository.updateEventById(dto.reWrite(id));
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	private String[] uploadFile(MultipartFile mFile) {

		if (mFile.getSize() > Define.MAX_FILE_SIZE) {
			throw new DataDeliveryException(Define.FILE_SIZE_EXCEEDED, HttpStatus.BAD_REQUEST);
		}
		String saveDriectory = uploadDir;

		String uploadFileName = UUID.randomUUID() + "_" + mFile.getOriginalFilename();
		String uploadPath = saveDriectory + File.separator + uploadFileName;

		File destination = new File(uploadPath);

		Path uploadPath1 = Paths.get(uploadDir);
		if (!Files.exists(uploadPath1)) {
			try {
				Files.createDirectories(uploadPath1);
			} catch (Exception e) {
				throw new DataDeliveryException(Define.UPLOAD_DIR_CREATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		try {
			mFile.transferTo(destination);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			throw new DataDeliveryException(Define.FILE_UPLOAD_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new String[] { mFile.getOriginalFilename(), uploadFileName };
	}

	@Transactional // 이벤트 삭제
	public void deleteEvent(int id) {

		int result = 0;

		try {
			result = adminRepository.deleteByEventById(id);
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional // 이벤트 검색 처리
	public List<Event> searchEventPage(String search, int page, int size) {
		List<Event> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = adminRepository.findAdminEventByName(search, limit, offset);
		return list;
	}

	public int countEventAll() { // 이벤트 목록 불러오기 위한 카운팅
		return adminRepository.countEventAll();
	}

	public int countEvent(String search) { // 공지 검색 카운팅
		return adminRepository.countAdminEvent(search);
	}

	public Timestamp DatetoStringandTimeStamp(Date releaseDate2) {

		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat changeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		changeDate.setTimeZone(tz);

		String str = changeDate.format(releaseDate2);

		try {
			java.util.Date changeTimeStamp =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			return new Timestamp(changeTimeStamp.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public Timestamp DatetoStringandTimeStamp2(Date releaseDate) {

	
		DateFormat changeDate = new SimpleDateFormat("yyyy-MM-dd");

		String str = changeDate.format(releaseDate);

		try {
			java.util.Date changeTimeStamp =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			return new Timestamp(changeTimeStamp.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	// ----------------------------------------------
	// 회원정보

	@Transactional // 멤버리스트 불러오기
	public List<User> readMemberList(int page, int size) {
		List<User> userEntity = new ArrayList<>();

		int limit = size;
		int offset = (page - 1) * size;
		userEntity = adminRepository.findAdminMemberExceptPW(limit, offset);

		return userEntity;

	}
	
	@Transactional
	public User readMemberById(Integer id) {
		User user = adminRepository.findAdminMemberById(id);
		
		if (user == null) {
			throw new DataDeliveryException("존재하지 않는 회원 입니다", HttpStatus.BAD_REQUEST);
		}
		return user;
	}

	
	@Transactional
	public void reCreateMember(UserWriterDTO dto) {

		int result = 0;

		try {
			result = adminRepository.updateMemberById(dto.toUser());
		} catch (DataAccessException e) {
			throw new DataDeliveryException("잘못된 요청입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RedirectException("알 수 없는 오류 입니다", HttpStatus.SERVICE_UNAVAILABLE);
		}

		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public int countMemberAll() { // 멤버 목록 불러오기 위한 카운팅
		return adminRepository.countAdminMemberAll();
	}

	public int countMember(String search) { // 공지 검색 카운팅
		return adminRepository.countAdminMember(search);
	}

	@Transactional // 이벤트 검색 처리
	public List<User> searchMemberPage(String search, int page, int size) {
		List<User> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = adminRepository.findAdminMemberByName(search, limit, offset);
		return list;
	}

//----------------------------------------------------------
	// 결제내역

	public List<HistoryTimeLine> countHistory() {
		return adminRepository.countAdminHistory();
	}

	public List<History> readAllHistory() {
		return adminRepository.findHistoryAll();
	}


//=========================== profile ===============================
	public List<DateProfile> readProfileList(String search, int page, int size) {
		int limit = size;
		int offset = (page - 1) * size;
		return adminRepository.readProfileList(search, limit, offset);
	}

	public int countAdminProfileList(String search) {
		return adminRepository.countAdminProfileList(search);
	};

	public DateProfile searchProfileById(int id) {
		return adminRepository.searchProfileById(id);
	}

	@Transactional
	public int lifeStatusUpdate(int id) {
		DateProfile user = adminRepository.searchProfileById(id);

		if (user.getLifeStatus() == 1) {
			user.setLifeStatus(0);
		} else if (user.getLifeStatus() == 0) {
			user.setLifeStatus(1);
		}
		return adminRepository.lifeStatusUpdate(user.getLifeStatus(), id);
	}
	
	// ===================비동기 영역 ===============
	public List<CountProfileDTO>  CountProfile() {
		return adminRepository.CountProfile();		
	}
	
	public OnlyCountDTO totalProfileCount() {
		return adminRepository.totalProfileCount();
	}

	public OnlyCountDTO totalMatchings() {
		return adminRepository.totalMatchings();
	}

	public List<genresBookingsDTO> genresBookings() {
		return adminRepository.genresBookings();
	}

}
