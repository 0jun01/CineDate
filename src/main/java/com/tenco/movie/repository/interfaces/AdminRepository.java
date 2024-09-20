package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.CountProfileDTO;
import com.tenco.movie.dto.OnlyCountDTO;
import com.tenco.movie.dto.genresBookingsDTO;
import com.tenco.movie.repository.model.CancelHistory;
import com.tenco.movie.repository.model.CancelHistoryTimeLine;
import com.tenco.movie.repository.model.ConItems;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.Event;
import com.tenco.movie.repository.model.EventWrite;
import com.tenco.movie.repository.model.History;
import com.tenco.movie.repository.model.HistoryTimeLine;
import com.tenco.movie.repository.model.Notice;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.repository.model.UserWrite;

@Mapper
public interface AdminRepository {
	
	public int reviewAdminCount();
	public int sellAdminCount();
	public List<ConItems> findAdminItemAll();
	public int itemAdminCount();
	public int bookingAdminCount();
	public int sellAdminSum();
	
	
	
	//-------------------------------------------------
	
	
	public List<Notice> findAll(); // 공지사항 리스트 조회
	public int insertAdmin(Notice notice); // 공지사항 작성
	public int updateAdminById(Notice notice); // 공지사항 수정
	public int deleteAdminById(int id); // 공지사항 삭제
	public Notice findAdminById(int id); // 게시글 id로 찾기
	public List<Notice> findAdminByName(@Param("search")String search, @Param("limit") int limit, @Param("offset") int offset);
	
	public List<Notice> pageCountAdmin(@Param("limit") int limit, @Param("offset") int offset);
	
	
	
	
	public int countAdminNoticeAll();
	public int countAdminNotice(String search);
	
	//--------------------------------------------------
	public List<Event> findEventAll(@Param("limit") int limit, @Param("offset") int offset); // 이벤트 리스트 조회
	public int insertEvent(EventWrite event);
	public int updateEventById(EventWrite event);
	public int deleteByEventById(int id);
	
	public Event findEventById(int id);
	public List<Event> findAdminEventByName(@Param("search")String search, @Param("limit") int limit, @Param("offset") int offset);
	
	public int countEventAll();
	public int countAdminEvent(String search);
	
	
	//--------------------------------------------------
	
	public List<User> findAdminMemberExceptPW(@Param("limit") int limit, @Param("offset") int offset); // 비번제외 회원띄우기
	
	public List<User> findAdminMemberByName(@Param("search")String search, @Param("limit") int limit, @Param("offset") int offset);
	public User findAdminMemberById(int id);
	public int updateMemberById(UserWrite user);
	
	public int countAdminMemberAll();
	public int countAdminMember(String search);
	
	// ------------------------------------------------
	
	public List<HistoryTimeLine> countAdminHistory();
	public List<CancelHistoryTimeLine> countAdminCancelHistory();
	
	public List<History> findHistoryAll();
	public List<CancelHistory> findCancelAll();

	public List<History> readAdminHistory();
	
	// ------------------------------------------- profile -------------
	public List<DateProfile> readProfileList(@Param("search")String search, @Param("limit") int limit, @Param("offset") int offset);
	public int countAdminProfileList(@Param("search")String search);
	
	public List<DateProfile> pageDateCountAdmin(@Param("limit") int limit, @Param("offset") int offset);
	
	public int countAdminProfileAll();
	public List<DateProfile> readAdminProfile();
	
	public DateProfile searchProfileById(int id);
	public int lifeStatusUpdate(@Param("status")int status,@Param("id")int id);
	public List<CountProfileDTO> CountProfile();
	public OnlyCountDTO totalProfileCount();
	public OnlyCountDTO totalMatchings();
	public List<genresBookingsDTO> genresBookings();
	
	
}

