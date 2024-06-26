package data.mapper;

import data.dto.MemberDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapperInter {

    @Select("select count(*) from login where id=#{searchid}")
    public int getIdCheckCount(String searchid);

    @Insert("""
                insert into login(id, pw, name, nickname, profile, email, writeday) 
                values(#{id}, #{pw}, #{name}, #{nickname}, #{profile}, #{email}, now())
                """)
    public void insertMember(MemberDto dto);


    @Select("SELECT COUNT(*) FROM login")
    public int getTotalCount();

    @Select("select * from login order by num desc")
    public List<MemberDto> getAllMembers();

    @Select("select * from login where num=#{num}")
    public MemberDto getData(int num);

    @Select("select * from login where id=#{id}")
    public MemberDto getDataById(String id);

    @Update("update login set profile=#{photo} where num=#{num}")
    public void updatePhoto(Map<String, Object> map);

    @Update("update login set pw=#{pw}, nickname=#{nickname}, profile=#{profile}, email=#{email} where num=#{num}")
    public void updateMember(MemberDto dto);

/*
    @Delete("delete from login where num=#{num}")
    public void deleteMember(int num);
*/

    @Select("select count(*) from login where num=#{num} and pw=#{pw}")
    public int isEqualPassCheck(Map<String, Object> map);

    @Select("""
            select count(*) from login where id=#{myid} and pw=#{pass}
            """)
    public int isLoginCheck(String myid, String pass);

    @Select("select num from login where id = #{id}")
    public int getNum(String id);

}
